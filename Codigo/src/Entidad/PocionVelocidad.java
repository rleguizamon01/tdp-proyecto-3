package Entidad;

import Esperador.Esperador;
import Esperador.EsperadorVelocidad;
import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class PocionVelocidad extends Pocion {
	protected final int velocidadAumentada = 12; //Pacman pasa de moverse 8 veces por segundo a velocidadAumentada veces por segundo.
	protected final int duracion = 10000; //El efecto de la pocion dura duracion ms
	
	protected int velocidadOriginal;
	
	public PocionVelocidad(int f, int c, int pr, Grilla g, int v) {
		super(f, c, pr, g);
		velocidadOriginal = v;
	}

	@Override
	public void afectar() {
		miGrilla.pedirActualizarVelocidad(velocidadAumentada);
		Esperador es = new EsperadorVelocidad(duracion, miGrilla, velocidadOriginal);
		es.run();
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPocionVelocidadCI();
	}

}
