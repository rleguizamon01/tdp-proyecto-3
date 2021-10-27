package Entidad;

import Utilidad.Position;
import Grilla.Grilla;

public abstract class Entidad {
	protected Grilla miGrilla;
	protected Position pos;
	protected int prioridad;
	
	public Entidad(int f, int c, int p, Grilla g) {
		pos = new Position(f, c);
		prioridad = p;
		miGrilla = g;
	}
	
	public abstract void afectar();
	public abstract String getCaminoImagen();
	
	public int getPrioridad() {
		return prioridad;
	}
	
	public void setPrioridad(int p) {
		prioridad = p;
	}
}
