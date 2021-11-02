package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Fruta extends EntidadFija {
	private static final int PUNTOS_FRUTA = 2000;
	private static final int ANCHO_FRUTA = 15;
	private static final int ALTO_FRUTA = 9;

	public Fruta(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_FRUTA;
	}
	
	public Fruta(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_FRUTA)/2, 20*columna+(20-ALTO_FRUTA)/2, fila, columna, ANCHO_FRUTA, ALTO_FRUTA, g);
	}

	@Override
	public void afectar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getFrutaCI();
	}

}
