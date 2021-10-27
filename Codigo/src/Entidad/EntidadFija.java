package Entidad;

import Grilla.Grilla;

public abstract class EntidadFija extends Entidad {
	protected int puntos;
	
	public EntidadFija(int f, int c, int pr, Grilla g, int pu) {
		super(f, c, pr, g);
		puntos = pu;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int p) {
		puntos = p;
	}
}
