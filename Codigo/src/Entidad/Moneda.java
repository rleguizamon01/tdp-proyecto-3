package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Moneda extends EntidadFija {
	private static final int PUNTOS_MONEDA = 200;
	private static final int ANCHO_MONEDA = 9;
	private static final int ALTO_MONEDA = 9;
	private static final int OFFSET = 0;
	
	public Moneda(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_MONEDA;
	}
	
	public Moneda(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_MONEDA)/2 + OFFSET, 20*columna+(20-ALTO_MONEDA)/2 + OFFSET, fila, columna, ANCHO_MONEDA, ALTO_MONEDA, g);
		puntos = PUNTOS_MONEDA;
	}

	@Override
	public void afectar() {
		miGrilla.sumarPuntos(puntos);
		miGrilla.removerEntidad(this);
		miGrilla.decrementarMonedas();
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getMonedaCI();
	}

}
