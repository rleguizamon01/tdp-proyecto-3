package Entidad;

import Esperador.*;
import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class PowerPellet extends EntidadFija {
	private static final int PUNTOS_POWER_PELLET = 500;
	private static final int ANCHO_POWER_PELLET = 19;
	private static final int ALTO_POWER_PELLET = 19;
	private static final int DURACION_POWER_PELLET = 8000;
	
	public PowerPellet(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_POWER_PELLET;
	}
	
	public PowerPellet(int fila, int columna, Grilla g) {
		super(20*fila + (20-ANCHO_POWER_PELLET)/2, 20*columna+(20-ALTO_POWER_PELLET)/2, fila, columna, ANCHO_POWER_PELLET, ALTO_POWER_PELLET, g);
		puntos = PUNTOS_POWER_PELLET;
	}

	@Override
	public void afectar() {
		miGrilla.sumarPuntos(puntos);
		miGrilla.ponerFantasmasEnRun();
		EsperadorPowerPellet es = EsperadorPowerPellet.getEsperadorPowerPellet(DURACION_POWER_PELLET, miGrilla);
		
		if(es.estaCorriendo()) { //Si el esperador ya esta corriendo...
			es.agregarTiempo((int) Math.floor(DURACION_POWER_PELLET*0.75f)); //Agregamos tiempo, pero un poquito menos.
		} else {
			es.start(); //Sino, lo iniciamos.
		}
		miGrilla.removerEntidad(this);
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPowerPelletCI();
	}

}
