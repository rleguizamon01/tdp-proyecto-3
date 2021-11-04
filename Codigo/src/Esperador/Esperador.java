package Esperador;

import Grilla.Grilla;

public abstract class Esperador extends Thread {
	protected int tiempoEsperado;
	protected Grilla grillaNotificada;
	
	public Esperador(int tiempoEsperado, Grilla grillaNotificada) {
		this.tiempoEsperado = tiempoEsperado;
		this.grillaNotificada = grillaNotificada;
	}
	
	public void run() {
		try {
			Thread.sleep(tiempoEsperado);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notificar();
	}
	
	protected abstract void notificar();
}
