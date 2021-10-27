package Entidad;

import Grilla.Grilla;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class Pacman extends EntidadMovil {

	public Pacman(int f, int c, Grilla g) {
		super(f, c, 6, g);
	}

	@Override
	public Position getSiguientePosicion() {
		Position posicionNueva = pos.clone();
		
		switch (direccion) {
			case 'N':
				posicionNueva.setFila(posicionNueva.getFila() - 1);
				break;
			case 'S':
				posicionNueva.setFila(posicionNueva.getFila() + 1);
				break;
			case 'E':
				posicionNueva.setColumna(posicionNueva.getColumna() + 1);
				break;
			case 'O':
				posicionNueva.setColumna(posicionNueva.getColumna() - 1);
				break;
		}
		
		//Si no nos podemos mover es porque nos chocamos contra una pared
		//Y por tanto nos quedamos en el lugar.
		if(!miGrilla.bloqueLibre(posicionNueva))
			posicionNueva = pos;
		
		return posicionNueva;
	}

	@Override
	public void afectar() {} //Pacman no hace nada.

	@Override
	public String getCaminoImagen() {
		String ci = "";
		
		switch (direccion) {
			case 'N':
				ci = ResourceHandler.getPacmanNCI();
				break;
			case 'S':
				ci = ResourceHandler.getPacmanSCI();
				break;
			case 'E':
				ci = ResourceHandler.getPacmanECI();
				break;
			case 'O':
				ci = ResourceHandler.getPacmanOCI();
				break;
		}
		
		return ci;
	}

}
