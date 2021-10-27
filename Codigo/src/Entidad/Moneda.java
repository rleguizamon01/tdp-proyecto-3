package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Moneda extends EntidadFija {

	public Moneda(int f, int c, Grilla g) {
		super(f, c, 1, g, 200);
	}

	@Override
	public void afectar() {
		miGrilla.pedirSumarPuntos(puntos);
		miGrilla.decrementarMonedas();
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getMonedaCI();
	}

}
