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
				//Thread.sleep(velocidad);
				Thread.sleep(1000); //Por debugging, hacemos que se mueva 1 vez por segundo
				miJuego.pedirMoverPacman();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
