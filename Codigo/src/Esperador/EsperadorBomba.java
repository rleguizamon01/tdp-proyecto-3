package Esperador;

import Utilidad.Position;
import Grilla.Grilla;

public class EsperadorBomba extends Esperador {
	protected Position pos;
	
	public EsperadorBomba(int t, Grilla g, Position p) {
		super(t, g);
		pos = p;
	}
	
	@Override
	protected void notificar() {
		miGrilla.detonarBombaEn(pos);
	}

}
