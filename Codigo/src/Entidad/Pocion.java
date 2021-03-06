package Entidad;

import Grilla.Grilla;

public abstract class Pocion extends EntidadFija{
	protected static final int PUNTOS_POCION = 150;
	
	protected int duracion;
	public Pocion(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
	}
	
	protected abstract String getPacmanAlteradoCI();

}
