package Reloj;

import Logica.Juego;

public class RelojPacman extends Reloj {
	protected int velocidad;
	
	public RelojPacman(Juego j, int vp) {
		super(j);
		velocidad = vp;
	}
	
	public void setVelocidad(int v) {
		velocidad = v;
	}
	
	public int getVelocidad() {
		return velocidad;
	}

	public void run() {
		try {
			while(sePuedeJugar) {
				Thread.sleep(velocidad);
				miJuego.pedirMoverPacman();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
