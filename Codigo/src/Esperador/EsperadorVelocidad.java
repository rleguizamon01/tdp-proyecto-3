package Esperador;

import Grilla.Grilla;

public class EsperadorVelocidad extends Esperador {
	protected int velocidadOriginal;
	
	public EsperadorVelocidad(int t, Grilla g, int v) {
		super(t, g);
		velocidadOriginal = v;
	}

	@Override
	protected void notificar() {
		miGrilla.pedirActualizarVelocidad(velocidadOriginal);
	}

}
