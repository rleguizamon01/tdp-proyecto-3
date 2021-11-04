package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class PowerPellet extends EntidadFija {
	private static final int PUNTOS_POWER_PELLET = 500;
	private static final int ANCHO_POWER_PELLET = 19;
	private static final int ALTO_POWER_PELLET = 19;

	public PowerPellet(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_POWER_PELLET;
	}
	
	public PowerPellet(int fila, int columna, Grilla g) {
		super(20*fila + (20-ANCHO_POWER_PELLET)/2, 20*columna+(20-ALTO_POWER_PELLET)/2, fila, columna, ANCHO_POWER_PELLET, ALTO_POWER_PELLET, g);
	}

	@Override
	public void afectar() {
		//TODO: Ver tema timer para que salgan de run
		miGrilla.ponerFantasmasEnRun();
		miGrilla.removerEntidad(this);
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPowerPelletCI();
	}

}
