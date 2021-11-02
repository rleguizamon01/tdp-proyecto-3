package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class FantasmaRojo extends Fantasma {
	private static final int VELOCIDAD_ROJO = 2;
	
	public FantasmaRojo(int fila, int columna, Grilla g) {
		super(fila, columna, g);
		velocidad = VELOCIDAD_ROJO;
	}

	@Override
	public String getCaminoImagenColor() {
		return ResourceHandler.getFantasmaRojoCI(direccion);
	}

}
