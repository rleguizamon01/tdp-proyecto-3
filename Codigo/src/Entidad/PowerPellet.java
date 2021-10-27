package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class PowerPellet extends EntidadFija {

	public PowerPellet(int f, int c, Grilla g) {
		super(f, c, 2, g, 0);
	}

	@Override
	public void afectar() {
		miGrilla.ponerFantasmasEnRun();
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPowerPelletCI();
	}

}
