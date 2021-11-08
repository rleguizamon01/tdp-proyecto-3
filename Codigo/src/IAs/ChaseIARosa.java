package IAs;

import Grilla.Grilla;
import Utilidad.Position;

public class ChaseIARosa implements ChaseIA {
	protected Grilla miGrilla;
	
	public ChaseIARosa(Grilla g) {
		miGrilla = g;
	}
	
	public Position getTargetBlock() {
		int pixelesDeCelda = 20;
		Position tb = new Position(); //tb: target block
		Position posicionPacman = miGrilla.posicionActualPacman();
		
		switch(miGrilla.direccionPacman()) {
			case 'N':
				tb.setFila(posicionPacman.getFila() - 2*pixelesDeCelda);
				tb.setColumna(posicionPacman.getColumna() - 2*pixelesDeCelda);
				break;
			case 'S':
				tb.setFila(posicionPacman.getFila() + 2*pixelesDeCelda);
				tb.setColumna(posicionPacman.getColumna());
				break;
			case 'E':
				tb.setFila(posicionPacman.getFila());
				tb.setColumna(posicionPacman.getColumna() + 2*pixelesDeCelda);
			case 'O':
				tb.setFila(posicionPacman.getFila());
				tb.setColumna(posicionPacman.getColumna() - 2*pixelesDeCelda);
		}
		
		//El fantasma rosa persigue a pacman 2 casilleros hacia adelante, excepto cuando el
		//va hacia el norte, caso en el cual lo persigue 2 hacia adelante y 2 hacia la izquierda.
		
		return tb;
	}

}
