package IAs;

import Entidad.FantasmaNaranja;
import Grilla.Grilla;
import Utilidad.Position;

public class ChaseIANaranja implements ChaseIA {
	protected final int pixelesCelda = 20;
	protected final int rangoMiedo = 5;
	
	protected Grilla miGrilla;
	protected FantasmaNaranja miFantasma;
	
	public ChaseIANaranja(Grilla g, FantasmaNaranja f) {
		miGrilla = g;
		miFantasma = f;
	}

	@Override
	public Position getTargetBlock() {
		Position tb = new Position();
		
		Position aux = miFantasma.getEsquinaBuscada().clone();
		aux.setFila(aux.getFila()*pixelesCelda);
		aux.setColumna(aux.getColumna()*pixelesCelda);
		
		Position pacman = miGrilla.posicionActualPacman();
		
		if(Position.distancia(miFantasma.getPosicionAbsoluta(), pacman) <= rangoMiedo*pixelesCelda) {
			tb = aux;
		} else {
			tb = pacman;
		}
		
		return tb;
	}

}
