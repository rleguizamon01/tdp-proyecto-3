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
	
	protected EstrategiaNivel nivelActual;
	
	public Grilla(Juego j) {
		matrizGrilla = new Bloque[ANCHO][ALTO];
		contadorMonedas = 0;
		miJuego = j;
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
	
	public void removerTodasEntidades() {
		ArrayList<Entidad> todas = new ArrayList<Entidad>();
		for(Bloque[] b : matrizGrilla) {
			for(Bloque bb : b) {
				for(Entidad e : bb) {
					todas.add(e);
				}
			}
		}
		for(Entidad e : todas) {
			removerEntidad(e);
		}
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
		System.out.println("GANASTE!");
	}
	
	public void perdio() {
		System.out.println("F");
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

	
}
