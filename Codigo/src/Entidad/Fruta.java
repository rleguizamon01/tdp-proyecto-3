package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Fruta extends EntidadFija {

	public Fruta(int f, int c, Grilla g) {
		super(f, c, 3, g, 2000); //Tentativamente, una fruta suma 2000 puntos.
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
