package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Pinchos extends EntidadFija {
	private static final int PUNTOS_PINCHOS = 0;
	private static final int ANCHO_PINCHOS = 20;
	private static final int ALTO_PINCHOS = 20;
	
	public Pinchos(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_PINCHOS;
	}
	
	public Pinchos(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_PINCHOS)/2, 20*columna+(20-ALTO_PINCHOS)/2, fila, columna, ANCHO_PINCHOS, ALTO_PINCHOS, g);
		puntos = PUNTOS_PINCHOS;
	}

	@Override
	public void afectar() {
		miGrilla.perdio(); //Si pacman pisa los pinchos, muere. Corta.
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPinchosCI();
	}

}
