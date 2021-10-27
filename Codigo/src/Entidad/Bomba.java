package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Bomba extends EntidadFija {

	public Bomba(int f, int c, int pr, Grilla g) {
		super(f, c, pr, g, 0);
	}

	@Override
	public void afectar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getBombaCI();
	}

}
