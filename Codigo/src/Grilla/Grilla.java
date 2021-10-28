package Grilla;

import Entidad.Bomba;
import Entidad.Entidad;
import Entidad.EntidadMovil;
import Entidad.Pacman;
import Logica.Juego;
import Entidad.Fantasma;
import Entidad.FantasmaRojo;
import Entidad.FantasmaRosa;
import Entidad.FantasmaAzul;
import Entidad.FantasmaNaranja;
import Utilidad.Position;

public class Grilla {
	private static final int ALTO = 36; //Cantidad de filas en la grilla.
	private static final int ANCHO = 28;//Cantidad de columnas en la grilla
	
	protected EstrategiaNivel nivelActual;
	
	protected int monedasRestantes;
	
	protected Bloque matrizGrilla[][];
	
	protected EntidadMovil pacman;
	protected Fantasma rojo;
	protected Fantasma rosa;
	protected Fantasma azul;
	protected Fantasma naranja;
	protected Fantasma misFantasmas[]; //Para facilidad a la hora de operar con todos los fantasmas.
	
	protected Juego miJuego;
	
	public Grilla(Juego j, EstrategiaNivel nivel) {
		matrizGrilla = new Bloque[ANCHO][ALTO];
		
		nivelActual = nivel;
		miJuego = j;
		
		pacman = new Pacman(0, 0, this);
		rojo = new FantasmaRojo(0, 0, this);
		rosa = new FantasmaRosa(0, 0, this);
		azul = new FantasmaAzul(0, 0, this);
		naranja = new FantasmaNaranja(0, 0, this);
		
		misFantasmas = new Fantasma[]{rojo, rosa, azul, naranja};
		
		inicializar();
		
	}
	
	public EntidadMovil getRojo() {
		return rojo;
	}
	
	public EntidadMovil getRosa() {
		return rosa;
	}
	
	public EntidadMovil getAzul() {
		return azul;
	}
	
	public EntidadMovil getNaranja() {
		return naranja;
	}
	
	public int getVelocidadPacman() {
		return pacman.getVelocidad();
	}
	
	public boolean bloqueLibre(Position p) {
		boolean res = false;
		int f = p.getFila();
		int c = p.getColumna();
		
		if(f >= 0 && f < ALTO && c >= 0 && c < ANCHO)
			res = matrizGrilla[f][c].esPared();
		
		return res;
	}
	
	public void actualizarDireccionPacman(char d) {
		pacman.setDireccion(d);
	}
	
	public void moverPacman() {
		Bloque b = moverEntidad(pacman);
		b.ejecutarEfectos();
	}
	
	public void moverFantasma(EntidadMovil em) {
		moverEntidad(em);
	}
	
	private Bloque moverEntidad(EntidadMovil em) {
		Position posNueva = em.getSiguientePosicion();
		Position posActual = em.getPosition();
		
		Bloque bloqueNuevo = matrizGrilla[posNueva.getFila()][posNueva.getColumna()];
		Bloque bloqueActual = matrizGrilla[posActual.getFila()][posActual.getColumna()];
		
		bloqueActual.eliminarEntidad(em);
		bloqueNuevo.agregarEntidad(em);
		
		//TODO pedidos de actualizacion cuando implementemos juego.
		
		return bloqueNuevo;
	}
	
	protected void inicializar() {
		nivelActual.strategyInitialize(matrizGrilla);
	}
	
	public void pedirSumarPuntos(int p) {
		miJuego.sumarPuntos(p);
	}
	
	public void decrementarMonedas() {
		monedasRestantes--;
	}
	
	public void ponerFantasmasEnRun() {
		for(Fantasma f : misFantasmas)
			f.ponerEnRun();
	}
	
	public void ponerFantasmasEnScatter() {
		for(Fantasma f : misFantasmas)
			f.ponerEnScatter();
	}
	
	public void ponerFantasmasEnChase() {
		for(Fantasma f : misFantasmas)
			f.ponerEnChase();
	}

	public void ponerBombaEn(Position p) {
		int f = p.getFila();
		int c = p.getColumna();
		matrizGrilla[f][c].agregarEntidad(new Bomba(f, c, this));
	}
	
	public void detonarBombaEn(Position p) {
		
	}

	public void pedirActualizarVelocidad(int v) {
		
	}

	public void removerEntidad(Entidad e) {
		//pos = posicion de e
		//bloque = bloque[pos]
		//bloque.remover(e)
	}
}
