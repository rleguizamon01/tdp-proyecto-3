package Esperador;

import Grilla.Grilla;

public class EsperadorVelocidad extends Esperador {
	protected int velocidadOriginal;
	
	public EsperadorVelocidad(int tiempoEsperado, Grilla grillaNotificada, int veloOrig) {
		super(tiempoEsperado, grillaNotificada);
		velocidadOriginal = veloOrig;
	}
	
	@Override
	protected void notificar() {
		grillaNotificada.pedirEstablecerEstadoVelocidad(false);
		grillaNotificada.actualizarVelocidadPacman(velocidadOriginal);
		grillaNotificada.actualizarEstadoAfectadoPacman(null);
	}

}
