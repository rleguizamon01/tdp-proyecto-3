package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class PocionVelocidad extends Pocion {
	private static final int ANCHO_P_VELOCIDAD = 20;
	private static final int ALTO_P_VELOCIDAD = 14;

	public PocionVelocidad(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_POCION;
	}
	
	public PocionVelocidad(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_P_VELOCIDAD)/2, 20*columna+(20-ALTO_P_VELOCIDAD)/2, fila, columna, ANCHO_P_VELOCIDAD, ALTO_P_VELOCIDAD, g);
		puntos = PUNTOS_POCION;
	}
	
	@Override
	public void afectar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPocionVelocidadCI();
	}

}
