package Esperador;

import Entidad.PocionBomba;
import Grilla.Grilla;

public class EsperadorBomba extends Esperador {
	protected PocionBomba pocionBomba;
	
	public EsperadorBomba(int tiempoEsperado, Grilla grillaNotificada, PocionBomba pocionBomba) {
		super(tiempoEsperado, grillaNotificada);
		this.pocionBomba = pocionBomba;
	}
	
	@Override
	protected void notificar() {
		grillaNotificada.pedirExplotarBomba(pocionBomba);
		grillaNotificada.pedirEstablecerVisibleExplocionBomba(true, pocionBomba);
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		grillaNotificada.pedirEstablecerVisibleExplocionBomba(false, pocionBomba);
	}

}
