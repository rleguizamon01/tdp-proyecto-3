package Entidad;

import Grilla.Grilla;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class Pacman extends EntidadMovil {
	private static final int ANCHO_PACMAN = 15;
	private static final int ALTO_PACMAN = 18;
	private static final int VELOCIDAD_PACMAN = 4;
	private static final int PASO_PACMAN = 8;
	
	public Pacman(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g, int v, int p, char d) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		velocidad = v;
		paso = p;
		direccion = d;
	}
	
	public Pacman(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_PACMAN)/2, 20*columna+(20-ALTO_PACMAN)/2, fila, columna, ANCHO_PACMAN, ALTO_PACMAN, g);
		velocidad = VELOCIDAD_PACMAN;
		paso = PASO_PACMAN;
		direccion = 'E'; //Las entidades moviles arrancan por defecto apuntando al este.
	}

	@Override
	public Position getSiguientePosicion() {
		Position posicionNueva = posicionAbsoluta.clone();
		Position posicionPegadoPared = posicionAbsoluta.clone();
		
		switch (direccion) {
		case 'O':
			posicionNueva.setFila(posicionNueva.getFila() - velocidad);
			posicionPegadoPared.setFila((posicionPegadoPared.getFila() / 20) * 20);
			break;
		case 'E':
			posicionNueva.setFila(posicionNueva.getFila() + velocidad);
			posicionPegadoPared.setFila((posicionPegadoPared.getFila() / 20) * 20);
			posicionPegadoPared.setFila(posicionPegadoPared.getFila() + 20 - ancho);
			break;
		case 'S':
			posicionNueva.setColumna(posicionNueva.getColumna() + velocidad);
			break;
		case 'N':
			posicionNueva.setColumna(posicionNueva.getColumna() - velocidad);
			break;
		}
		
		Position PosicionNoSobreponerse = new Position(); //Es la esquina inferior izquierda del pacman ya movido.
		PosicionNoSobreponerse.setFila(posicionNueva.getFila() + ancho - 1);
		PosicionNoSobreponerse.setColumna(posicionNueva.getColumna() + alto - 1);
		
		if(!miGrilla.zonaLibre(posicionNueva) || !miGrilla.zonaLibre(PosicionNoSobreponerse)) {
			posicionNueva = posicionPegadoPared;
		}
		
		return posicionNueva;
	}

	@Override
	public void afectar() {
		//Pacman no hace nada.
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPacmanCI(direccion);
	}

}
