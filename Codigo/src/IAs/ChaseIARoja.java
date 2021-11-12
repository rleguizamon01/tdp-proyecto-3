package IAs;

import Utilidad.Position;
import Grilla.Grilla;


public class ChaseIARoja implements ChaseIA {
	protected Grilla miGrilla;
	
	public ChaseIARoja(Grilla g) {
		miGrilla = g;
	}
	
	public Position getTargetBlock() {
		return miGrilla.getPacman().getPosicionAbsoluta();
	}

}
