package Entidad;

import Grilla.Grilla;
import Utilidad.Position;

public abstract class EntidadMovil extends Entidad {
	protected char direccion;
	protected int paso;
	protected int velocidad;
	
	
	public EntidadMovil(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
	}


	public char getDireccion() {
		return direccion;
	}


	public int getPaso() {
		return paso;
	}


	public int getVelocidad() {
		return velocidad;
	}


	public void setDireccion(char direccion) {
		this.direccion = direccion;
	}


	public void setPaso(int paso) {
		this.paso = paso;
	}


	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public void invertirDireccion() {
		if(direccion == 'N') {
			direccion = 'S';
		} else if(direccion == 'S') {
			direccion = 'N';
		} else if(direccion == 'E') {
			direccion = 'O';
		} else {
			direccion = 'E';
		}
	}
	
	public abstract Position getSiguientePosicion();

}
