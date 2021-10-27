package Entidad;

import Utilidad.Position;
import Grilla.Grilla;

public abstract class Entidad {
	protected Grilla miGrilla;
	protected Position pos;
	protected int prioridad;
	
	public Entidad(int f, int c, int prioridad, Grilla miGrilla) {
		pos = new Position(f, c);
		this.prioridad = prioridad;
		this.miGrilla = miGrilla;
	}
	
	public abstract void afectar();
	public abstract String getCaminoImagen();
	
	public Position getPosition() {
		return pos;
	}
	
	public void setPosition(Position p) {
		pos = p;
	}
	
	public void setPosition(int f, int c) {
		pos = new Position(f, c);
	}
	
	public int getPrioridad() {
		return prioridad;
	}
	
	public void setPrioridad(int p) {
		prioridad = p;
	}
}
