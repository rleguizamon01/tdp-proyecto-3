package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Fruta extends EntidadFija {

	public Fruta(int f, int c, int pr, Grilla g, int pu) {
		super(f, c, pr, g, pu);
	}
	
	@Override
	public void afectar() {
		miGrilla.pedirSumarPuntos(puntos);
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getFrutaCI();
	}

}
