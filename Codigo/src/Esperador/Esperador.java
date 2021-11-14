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
		antesDeEsperar();
		try {
			System.out.println("++ Empezó a esperar, tiempo: " + tiempoEsperado);
			Thread.sleep(tiempoEsperado);
			System.out.println("++ Terminó de esperar");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notificar();
	}
	
	protected void antesDeEsperar() {} //Actua como hook.
	
	protected abstract void notificar();
}
