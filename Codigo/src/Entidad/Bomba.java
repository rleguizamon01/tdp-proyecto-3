package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Bomba extends EntidadFija {

	public Bomba(int f, int c, Grilla g) {
		super(f, c, 5, g, 0);
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
