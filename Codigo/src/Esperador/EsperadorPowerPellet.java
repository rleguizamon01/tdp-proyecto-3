package Esperador;

import java.util.LinkedList;
import java.util.Queue;

import Grilla.Grilla;

public class EsperadorPowerPellet extends Esperador {
	private static EsperadorPowerPellet me;
	private static boolean estaCorriendo;
	private Queue<Integer> colaTiempos;
	
	private EsperadorPowerPellet(int tiempoEsperado, Grilla grillaNotificada) {
		super(tiempoEsperado, grillaNotificada);
		estaCorriendo = false;
		colaTiempos = new LinkedList<Integer>(); //Si bien instanciamos con LinkedList, no "compramos" la interfaz entera, solo lo de queue.
	}

	public static EsperadorPowerPellet getEsperadorPowerPellet(int t, Grilla g) {
		if(me == null)
			me = new EsperadorPowerPellet(t, g);
		
		return me;
	}
	
	protected void antesDeEsperar() {
		estaCorriendo = true;
	}
	
	@Override
	protected void notificar() {
		while(!colaTiempos.isEmpty())
			dormirTiempoExtra(colaTiempos.poll());
		
		grillaNotificada.pedirEstablecerEstadoPowerPellet(false);
		grillaNotificada.ponerFantasmasEnChase();
		me = null;
		estaCorriendo = false;
	}
	
	protected void dormirTiempoExtra(int t) {
		try {
			Thread.sleep(t);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean estaCorriendo() {
		return estaCorriendo;
	}
	
	public void agregarTiempo(int t) {
		colaTiempos.add(t);
	}

}
