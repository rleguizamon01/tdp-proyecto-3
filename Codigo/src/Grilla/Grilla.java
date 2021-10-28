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
	
	public Grilla() {
		matrizGrilla = new Bloque[ANCHO][ALTO];
	}
	
	public void inicializar(Juego j, EstrategiaNivel nivel) {
		nivelActual = nivel;
		miJuego = j;
		
		nivelActual.strategyInitialize(matrizGrilla);
		
		pacman = nivelActual.getPacman();
		
		rojo = nivelActual.getRojo();
		rosa = nivelActual.getRosa();
		azul = nivelActual.getAzul();
		naranja = nivelActual.getNaranja();
		misFantasmas = new Fantasma[]{rojo, rosa, azul, naranja};
		
		monedasRestantes = nivelActual.getTotalDeMonedasEnNivel();
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
		
		if(f >= 0 && f < ANCHO && c >= 0 && c < ALTO) //Nos aseguramos que este in bounds
			res = !matrizGrilla[f][c].esPared();	  //Nos aseguramos que no sea pared.
		
		return res;
	}
	
	public void actualizarDireccionPacman(char d) {
		System.out.println("Grilla le pide a pacman que la direccion sea: " + d);
		pacman.setDireccion(d);
	}
	
	public synchronized void moverPacman() {
		//System.out.print("Pacman se mueve de: " + pacman.getPosition() + " a ");
		
		Bloque b = moverEntidad(pacman);
		
		//System.out.println(b);
		
		b.ejecutarEfectos();
	}
	
	public synchronized void moverFantasma(EntidadMovil em) {
		Position posVieja = em.getPosition();
		Bloque b = moverEntidad(em);
		Position posNueva = b.getPosition();
		
		int fv = posVieja.getFila();
		int fn = posNueva.getFila();
		int cv = posVieja.getColumna();
		int cn = posNueva.getColumna();
		
		System.out.println("Se mueve de: " + posVieja + " a " + posNueva);
		
		int diferenciaF = fv - fn;
		int diferenciaC = cv - cn;
		
		System.out.println("Diferencia F es: " + diferenciaF);
		System.out.println("Diferencia C es: " + diferenciaC);
		
		/**if(diferenciaF == 0) {
			if(diferenciaC > 0)
				em.setDireccion('N');
			else
				em.setDireccion('S');
		} else if(diferenciaC == 0) {
			if(diferenciaF > 0)
				em.setDireccion('O');
			else
				em.setDireccion('E');
		}**/
		
		if(diferenciaF != 0 || diferenciaC != 0) { //Si ambas son cero, la EM no se movio, por lo que no actualizamos su direccion.
			if(diferenciaF == 0) {
				if(diferenciaC > 0)
					em.setDireccion('N');
				else
					em.setDireccion('S');
			}
			
			if(diferenciaC == 0) {
				if(diferenciaF > 0)
					em.setDireccion('O');
				else
					em.setDireccion('E');
			}
		}
		
		System.out.println("El fantasma se movio hacia el: " + em.getDireccion());
	}
	
	private synchronized Bloque moverEntidad(EntidadMovil em) {
		Position posNueva = em.getSiguientePosicion();
		Position posActual = em.getPosition();
		
		Bloque bloqueNuevo = matrizGrilla[posNueva.getFila()][posNueva.getColumna()];
		Bloque bloqueActual = matrizGrilla[posActual.getFila()][posActual.getColumna()];
		
		if(bloqueNuevo != bloqueActual) {
			em.setPosition(posNueva);
			
			bloqueActual.eliminarEntidad(em);
			bloqueNuevo.agregarEntidad(em);
			
			String a = bloqueActual.getCaminoImagen();
			miJuego.pedirActualizar(posActual, a);
			//System.out.println("Actual: " + a);
			
			String n = bloqueNuevo.getCaminoImagen();
			miJuego.pedirActualizar(posNueva, n);
			//System.out.println("Nuevo: " + n);
		}
		
		return bloqueNuevo;
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
		Position pos = e.getPosition();
		Bloque b = matrizGrilla[pos.getFila()][pos.getColumna()];
		b.eliminarEntidad(e);
	}
}
