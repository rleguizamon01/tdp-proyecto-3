package Entidad;

import Grilla.Grilla;

public abstract class EntidadFija extends Entidad {
	protected int puntos;
	
	public EntidadFija(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
	}

}
