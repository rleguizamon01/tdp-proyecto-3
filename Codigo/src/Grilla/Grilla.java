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
	
	protected EntidadMovil pacman;
	protected Fantasma rojo;
	protected Fantasma rosa;
	protected Fantasma azul;
	protected Fantasma naranja;
	
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
		
		contadorMonedas = nivelActual.getTotalDeMonedasEnNivel();
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
	
	public void sumarPuntos(int p) {
		System.out.println("Se suman " + p + " puntos! Buen trabajo!");
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
	
	public void actualizarVelocidadPacman(int v) {
		pacman.setVelocidad(v);
	}
	
	public int velocidadActualPacman() {
		return pacman.getVelocidad();
	}
	
	public void moverPacman() {
		Bloque b = moverEntidad(pacman);
		
		for(Entidad e: entidadesQueColisionan())
			e.afectar();
	}
	
	public void moverFantasma(Fantasma f) {
		Position posVieja = f.getPosicionAbsoluta();
		moverEntidad(f);
		Position posNueva = f.getPosicionAbsoluta();
		
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
	
	public void mostrarEntidades() {
		Iterable<Entidad> it = entidadesQueColisionan();
		
		System.out.println("COLISIONAN: ");
		
		for(Entidad e : it)
			System.out.println(e.getClass() + ": " + e.getPosicionZona());
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
}
