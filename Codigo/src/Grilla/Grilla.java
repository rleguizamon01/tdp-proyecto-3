package Grilla;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Entidad.*;
import Utilidad.Position;
import Utilidad.PositionAdapter;
import Logica.Juego;

public class Grilla {
	private static final int ALTO = 36; //Cantidad de filas en la grilla.
	private static final int ANCHO = 28;//Cantidad de columnas en la grilla
	private static final int PIXELES = 20;//Tamaño en px de cada celda.
	
	protected Bloque matrizGrilla[][];
	protected Juego miJuego;
	
	protected int contadorMonedas;
	protected int tiempoEnScatter;
	
	protected Pacman pacman;
	protected Fantasma rojo;
	protected Fantasma rosa;
	protected Fantasma azul;
	protected Fantasma naranja;
	protected Fantasma[] misFantasmas;
	protected ArrayList<PocionBomba> pocionesBombaActivas;
	
	protected EstrategiaNivel nivelActual;
	
	public Grilla(Juego j) {
		matrizGrilla = new Bloque[ANCHO][ALTO];
		contadorMonedas = 0;
		miJuego = j;
		pocionesBombaActivas = new ArrayList<PocionBomba>();
	}
	
	public void setNivelActual(EstrategiaNivel e) {
		nivelActual = e;
	}
	
	public void inicializar() {
		nivelActual.strategyInitialize(matrizGrilla);
		
		pacman = nivelActual.getPacman();
		
		rojo = nivelActual.getRojo();
		rosa = nivelActual.getRosa();
		azul = nivelActual.getAzul();
		naranja = nivelActual.getNaranja();
		
		misFantasmas = new Fantasma[] {rojo, rosa, azul, naranja};
		
		contadorMonedas = nivelActual.getTotalDeMonedasEnNivel();
		tiempoEnScatter = nivelActual.getMilisegundosEnScatter();
	}
	
	public Fantasma getRojo() {
		return rojo;
	}
	
	public Fantasma getRosa() {
		return rosa;
	}
	
	public Fantasma getAzul() {
		return azul;
	}
	
	public Fantasma getNaranja() {
		return naranja;
	}
	
	public Pacman getPacman() {
		return pacman;
	}
	
	public int getMilisegundosEnScatter() {
		return tiempoEnScatter;
	}

	public void ponerFantasmasEnChase() {
		for(Fantasma f : misFantasmas)
			if(f != null)
				if(!f.estaMuerto())
					f.ponerEnChase();
	}
	
	public void ponerFantasmasEnScatter() {
		for(Fantasma f : misFantasmas)
			if(f != null)
				if(!f.estaMuerto())
					f.ponerEnScatter();
	}
	
	public void ponerFantasmasEnRun() {
		for(Fantasma f : misFantasmas)
			if(f != null)
				if(!f.estaMuerto())
					f.ponerEnRun();
	}

	public void sumarPuntos(int p) {
		miJuego.sumarPuntos(p);
	}
	
	public void removerEntidad(Entidad e) {
		Position pos = e.getPosicionZona();
		Bloque b = matrizGrilla[pos.getFila()][pos.getColumna()];
		//e.getEntidadGrafica().desparecer(); //Genera menos lag, pero es menos efectivo porque la JLabel sigue existiendo
		miJuego.removerLabel(e.getEntidadGrafica()); //Genera mas lag, pero la JLabel deja de existir.
		b.eliminarEntidad(e);
	}
	
	public void agregarLabel(EntidadGrafica eg) {
		miJuego.agregarLabel(eg);
	}
	
	public void actualizarDireccionPacman(char d) {
		pacman.setDireccion(d);
		pacman.getEntidadGrafica().actualizarImagen();
	}
	
	public void moverPacman() {
		Bloque b = moverEntidad(pacman);
		
		for(Entidad e: entidadesQueColisionan())
			e.afectar();
	}
	
	public void teletransportarPacman(Portal destino) {
		Position posAbsolutaInicial = pacman.getPosicionAbsoluta();
		Position posAbsolutaFinal = destino.getPosicionAbsoluta();
		
		Bloque zonaInicial = matrizGrilla[posAbsolutaInicial.getFila() / 20][posAbsolutaInicial.getColumna() / 20];
		Bloque zonaFinal = matrizGrilla[posAbsolutaFinal.getFila() / 20][posAbsolutaFinal.getColumna() / 20];
		
		if(posAbsolutaInicial != posAbsolutaFinal) {
			pacman.setPosicionAbsoluta(posAbsolutaFinal);
			pacman.getEntidadGrafica().actualizar(posAbsolutaFinal.getFila(), posAbsolutaFinal.getColumna());
		}
		
		Position posZonaNueva = new Position(posAbsolutaFinal.getFila() / 20, posAbsolutaFinal.getColumna() / 20);
		pacman.setPosicionZona(posZonaNueva);
		
		if(zonaInicial != zonaFinal) {
			zonaInicial.eliminarEntidad(pacman); //Si las zonas son distintas, ponemos
			zonaFinal.agregarEntidad(pacman);    //a la entidad en el iterable correcto.
		}
		
		
		zonaFinal.agregarEntidad(pacman);    //a la entidad en el iterable correcto.
	}
	
	public synchronized void moverFantasma(Fantasma f) {
		if(f != null) {
			//Movimiento como tal:
			Position posVieja = f.getPosicionAbsoluta();
			moverEntidad(f);
			Position posNueva = f.getPosicionAbsoluta();
			
			//Actualizacion de la direccion:
			int fv = posVieja.getFila();
			int fn = posNueva.getFila();
			int cv = posVieja.getColumna();
			int cn = posNueva.getColumna();
			
			int diferenciaF = fv - fn;
			int diferenciaC = cv - cn;
			
			if(diferenciaF != 0 || diferenciaC != 0) { //Si ambas son cero, la EM no se movio, por lo que no actualizamos su direccion.
				if(diferenciaF == 0) {
					if(diferenciaC > 0)
						f.setDireccion('N');
					else
						f.setDireccion('S');
				}
				
				if(diferenciaC == 0) {
					if(diferenciaF > 0)
						f.setDireccion('O');
					else
						f.setDireccion('E');
				}
			}
			
			//Respawn:
			if(f.getPosicionZona().equals(f.getPosicionSpawn()) && f.estaMuerto())
				f.ponerEnChase();
		}
	}
	
	private Bloque moverEntidad(EntidadMovil em) {
		Position posAbsolutaInicial = em.getPosicionAbsoluta();
		Position posAbsolutaFinal = em.getSiguientePosicion();
		
		Bloque zonaInicial = matrizGrilla[posAbsolutaInicial.getFila() / 20][posAbsolutaInicial.getColumna() / 20];
		Bloque zonaFinal = matrizGrilla[posAbsolutaFinal.getFila() / 20][posAbsolutaFinal.getColumna() / 20];
		
		if(posAbsolutaInicial != posAbsolutaFinal) {
			em.setPosicionAbsoluta(posAbsolutaFinal);
			em.getEntidadGrafica().actualizar(posAbsolutaFinal.getFila(), posAbsolutaFinal.getColumna());
		}
		
		Position posZonaNueva = new Position(posAbsolutaFinal.getFila() / 20, posAbsolutaFinal.getColumna() / 20);
		em.setPosicionZona(posZonaNueva);
		
		if(zonaInicial != zonaFinal) {
			zonaInicial.eliminarEntidad(em); //Si las zonas son distintas, ponemos
			zonaFinal.agregarEntidad(em);    //a la entidad en el iterable correcto.
		}
		
		return zonaFinal;
	}
	
	/**
	 * Devuelve true si una entidad se puede mover a esta zona.
	 * @param p posicon ABSOLUTA de la entidad.
	 * @return true si una entidad se puede mover a esta zona.
	 */
	public boolean zonaLibre(Position p) {
		boolean res = false;
		int f = p.getFila() / 20;
		int c = p.getColumna() / 20;

		if(f >= 0 && f < ANCHO && c >= 0 && c < ALTO) //Nos aseguramos que este in bounds
			res = !matrizGrilla[f][c].esPared();	  //Nos aseguramos que no sea pared.
		
		return res;
	}
	
	protected Iterable<Entidad> entidadesQueColisionan() {
		ArrayList<Entidad> it = new ArrayList<Entidad>();

		Set<Position> zonasPacman = zonasOcupadas(pacman);
		Bloque aux = null;
		
		Position eSIP = pacman.getEsquinaSuperiorIzquierda(); //eSIP = esquina superior izquierda pacman
		Position eIDP = pacman.getEsquinaInferiorDerecha();   //eIDP = esquina inferior derecha pacman
		Position eSIE = null;
		Position eIDE = null;
		
		for(Position p : zonasPacman) {
			aux = matrizGrilla[p.getFila()][p.getColumna()];
			
			for(Entidad e : aux) {
				eSIE = e.getEsquinaSuperiorIzquierda();
				eIDE = e.getEsquinaInferiorDerecha();
				
				if(PositionAdapter.seSobreponenAdaptado(eSIP, eIDP, eSIE, eIDE))
					it.add(e);
			}
			
		}
		
		return it;
	}
	
	protected Set<Position> zonasOcupadas(Entidad e) {
		Set<Position> hs = new HashSet<Position>();
		Position aux = null;
		
		for(Position p : e.getEsquinas()) {
			aux = new Position();
			aux.setFila(p.getFila() / PIXELES);
			aux.setColumna(p.getColumna() / PIXELES);
			hs.add(aux);
		}
		
		return hs;
	}
	
	public void decrementarMonedas() {
		contadorMonedas--;
		if(contadorMonedas == 0)
			gano();
	}
	
	public void gano() {
		miJuego.gano();
	}
	
	public void perdio() {
		miJuego.perdio();
	}

	public void pedirEstablecerEstadoVelocidad(boolean b) {
		miJuego.pedirEstablecerVisibleVelocidad(b);
	}
	
	public void pedirEstablecerEstadoPowerPellet(boolean b) {
		miJuego.pedirEstablecerVisiblePowerPellet(b);
	}
	
	public void pedirEstablecerEstadoBomba(boolean b) {
		miJuego.pedirEstablecerVisibleBomba(b);
	}
	
	public void pedirAlmacenarBomba() {
		miJuego.pedirAlmacenarBomba();
	}
	
	public void posicionarPocionBomba() {
		miJuego.reproducirSFX(PocionBomba.PATH_SFX_PONER);
		Position posPacmanZona = pacman.getPosicionZona();
		Bloque bloquePacman = matrizGrilla[posPacmanZona.getFila()][posPacmanZona.getColumna()];

		PocionBomba pocionBomba = new PocionBomba(posPacmanZona.getFila(), posPacmanZona.getColumna(), this, true);
		pocionBomba.setEntidadGrafica(new EntidadGrafica(pocionBomba));
		bloquePacman.agregarEntidad(pocionBomba);
		agregarLabel(pocionBomba.getEntidadGrafica());
		
		pocionesBombaActivas.add(pocionBomba);
		pocionBomba.consumir();
	}
	
	public void pedirExplotarBomba(PocionBomba pocionBombaExplotar) {
		miJuego.reproducirSFX(PocionBomba.PATH_SFX_EXPLOTAR);
		pocionesBombaActivas.remove(pocionBombaExplotar);
		if(pocionesBombaActivas.isEmpty())
			pacman.alterarEstado(null);
		
		Position esquinaInicial = esquinaInicialExplosion(pocionBombaExplotar);
		Position esquinaFinal = esquinaFinalExplosion(pocionBombaExplotar);
		
		for(Fantasma fantasma: misFantasmas) {
			if(fantasma != null) {
				Position posicionFantasma = fantasma.getPosicionZona();
				if(posicionFantasma.estaEntrePosiciones(esquinaInicial, esquinaFinal))
					fantasma.ponerEnDead();
			}
		}
		
		miJuego.pedirEstablecerVisibleBomba(false);
		removerEntidad(pocionBombaExplotar);
	}
	
	private Position esquinaInicialExplosion(PocionBomba pocionBomba) {
		// Por defecto asumo que la esquina inicial es el comienzo de la grilla
		Position esquinaInicial = new Position(0, 0);
		
		int filaInicialExplosion = pocionBomba.getPosicionZona().getFila() - pocionBomba.getRadioExplosion();
		int columnaInicialExplosion = pocionBomba.getPosicionZona().getColumna() - pocionBomba.getRadioExplosion();
		
		// Si la esquina de la explosión no está por fuera de los bordes de la matriz, le asignamos ese valor
		if(filaInicialExplosion > 0 ) 
			esquinaInicial.setFila(filaInicialExplosion);
		
		if(columnaInicialExplosion > 0)
			esquinaInicial.setColumna(columnaInicialExplosion);

		return esquinaInicial;
	}
	
	private Position esquinaFinalExplosion(PocionBomba pocionBomba) {
		// Por defecto asumo que la esquina final es el final de la grilla
		Position esquinaFinal = new Position(matrizGrilla.length, matrizGrilla[0].length);
		
		int filaFinalExplosion = pocionBomba.getPosicionZona().getFila() + pocionBomba.getRadioExplosion();
		int columnaFinalExplosion = pocionBomba.getPosicionZona().getColumna() + pocionBomba.getRadioExplosion();
		
		// Si la esquina de la explosión es válida, le asignamos ese valor
		if(filaFinalExplosion < matrizGrilla.length ) 
			esquinaFinal.setFila(filaFinalExplosion);
		
		if(columnaFinalExplosion < matrizGrilla[0].length)
			esquinaFinal.setColumna(columnaFinalExplosion);
		
		return esquinaFinal;
	}

	public void pedirReproducirSFX(String path) {
		miJuego.reproducirSFX(path);
	}
}
