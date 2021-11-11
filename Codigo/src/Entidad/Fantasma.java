package Entidad;

import Grilla.Grilla;
import Utilidad.Position;
import EstadosFantasmas.*;

public abstract class Fantasma extends EntidadMovil {
	protected static final int ANCHO_FANTASMAS = 20;
	protected static final int ALTO_FANTASMAS = 20;
	protected static final int PASO_FANTASMAS = 24;

	protected EstadoFantasma miEstado;
	
	protected Position esquinaBuscada;
	protected Position posicionDeSpawn; //Aqui es donde retornaran cuando entren en estado dead. Es una posicion en la grilla, no absoluta.
	
	protected EstadoFantasma chase;
	protected EstadoFantasma run;
	protected EstadoFantasma scatter;
	protected EstadoFantasma dead;
	
	protected int pasosEnElMismoLugar;
	
	public Fantasma(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
	}
	
	public Fantasma(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_FANTASMAS)/2, 20*columna+(20-ALTO_FANTASMAS)/2, fila, columna, ANCHO_FANTASMAS, ALTO_FANTASMAS, g);
		posicionDeSpawn = posicionZona.clone();
		paso = PASO_FANTASMAS;
		direccion = 'E';
		pasosEnElMismoLugar = 0;
	}
	
	public void ponerEnChase() {
		miEstado = chase;
		eg.actualizarImagen();
	}
	
	public void ponerEnScatter() {
		miEstado = scatter;
		eg.actualizarImagen();
	}
	
	public void ponerEnRun() {
		miEstado = run;
		invertirDireccion(); //Esto se hace porque pasa en el pacman de verdad.
		eg.actualizarImagen();
	}
	
	public void ponerEnDead() {
		miEstado = dead;
		invertirDireccion(); //Esto se hace porque pasa en el pacman de verdad.
		eg.actualizarImagen();
	}
	
	@Override
	public Position getSiguientePosicion() {
		Position sig = miEstado.siguientePosicion();
		
		pasosEnElMismoLugar = sig.equals(posicionAbsoluta) ? pasosEnElMismoLugar + 1 : 0;
		
		/**
		 * Para evitar que los fantasmas queden atascados permanentemente en ciertos tuneles
		 * Por ejemplo, en los tueneles de los teletransportadores
		 * Hacemos que inviertan su direccion si pasan 25 "pasos" en el mismo lugar.
		 */
		if(pasosEnElMismoLugar >= 25) { 
			invertirDireccion();
			pasosEnElMismoLugar = 0;
		}
		
		return sig;
	}
	
	public Position getPosicionSpawn() {
		return posicionDeSpawn;
	}

	@Override
	public void afectar() {
		miEstado.afectar();
	}

	@Override
	public String getCaminoImagen() {
		return miEstado.caminoImagen();
	}
	
	public Position getEsquinaBuscada() {
		return esquinaBuscada;
	}
	
	public boolean estaMuerto() {
		return miEstado.estaMuerto();
	}
	
	public abstract String getCaminoImagenColor();

}
