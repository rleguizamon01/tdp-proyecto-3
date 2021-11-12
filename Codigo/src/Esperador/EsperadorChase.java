package Esperador;

import Grilla.Grilla;

public class EsperadorChase extends Esperador {
	public EsperadorChase(int tiempoEsperado, Grilla grillaNotificada) {
		super(tiempoEsperado, grillaNotificada);
	}

	protected void notificar() {
		grillaNotificada.ponerFantasmasEnChase();
	}

}
