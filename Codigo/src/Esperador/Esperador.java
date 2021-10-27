package Esperador;

import Grilla.Grilla;

public abstract class Esperador extends Thread {
	protected Grilla miGrilla;
	protected int tiempoEsperado;
	
	public Esperador(int t, Grilla g) {
		tiempoEsperado = t;
		miGrilla = g;
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
