package Entidad;

import Grilla.Grilla;
import Utilidad.Position;

public abstract class EntidadMovil extends Entidad {
	protected char direccion;
	
	public EntidadMovil(int f, int c, int p, Grilla g) {
		super(f, c, p, g);
		direccion = 'E'; //Por defecto todas las entidades comienzan apuntando hacia el Este.
	}
	
	public abstract Position getSiguientePosicion();
	
	public void setDireccion(char d) {
		if(d == 'N' || d == 'S' || d == 'E' || d == 'O')
			direccion = d;
	}
	
	public char getDireccion() {
		return direccion;
	}
}
