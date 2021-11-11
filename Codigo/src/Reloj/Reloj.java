package Reloj;

import Logica.Juego;

public abstract class Reloj extends Thread {
	protected Juego miJuego;
	protected boolean sePuedeJugar;
	protected int intervalo;
	
	public Reloj(boolean spj, int in, Juego j) {
		sePuedeJugar = spj;
		intervalo = in;
		miJuego = j;
	}
	
	public void run() {
		try {
			while(sePuedeJugar) {
				Thread.sleep(intervalo);
				notificar();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected abstract void notificar();

	public boolean isSePuedeJugar() {
		return sePuedeJugar;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setSePuedeJugar(boolean sePuedeJugar) {
		this.sePuedeJugar = sePuedeJugar;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}
	
	
}
