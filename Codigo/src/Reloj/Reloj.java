package Reloj;

import Logica.Juego;

public abstract class Reloj extends Thread {
	protected Juego miJuego;
	protected boolean sePuedeJugar;
	
	public Reloj(Juego j) {
		miJuego = j;
		sePuedeJugar = false;
	}
	
	public void setJugable(boolean jugable) {
		sePuedeJugar = jugable;
	}
}
