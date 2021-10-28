package Entidad;

import Esperador.Esperador;
import Esperador.EsperadorBomba;
import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class PocionBomba extends Pocion {
	private final int tiempoMecha = 3000; //milisegundos hasta que la bomba explote.
	
	public PocionBomba(int f, int c, Grilla g) {
		super(f, c, g);
	}

	@Override
	public void afectar() {
		miGrilla.ponerBombaEn(pos);
		Esperador es = new EsperadorBomba(tiempoMecha, miGrilla, pos);
		es.run();
		miGrilla.removerEntidad(this);
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPocionBombaCI();
	}

}
