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
		
		switch (direccion) {
		case 'O':
			posicionNueva.setFila(posicionNueva.getFila() - velocidad);
			break;
		case 'E':
			posicionNueva.setFila(posicionNueva.getFila() + velocidad);
			break;
		case 'S':
			posicionNueva.setColumna(posicionNueva.getColumna() + velocidad);
			break;
		case 'N':
			posicionNueva.setColumna(posicionNueva.getColumna() - velocidad);
			break;
		}
		
		Position PosicionNoSobreponerse = new Position();
		//No les voy a mentir chicos, no se muy bien por que funciona esto con estos valores,
		//Solo se que funciona.
		//TODO: Borrar el comentario anterior!
		PosicionNoSobreponerse.setFila(posicionNueva.getFila() + ancho);
		PosicionNoSobreponerse.setColumna(posicionNueva.getColumna() + alto);
		
		if(!miGrilla.zonaLibre(posicionNueva) || !miGrilla.zonaLibre(PosicionNoSobreponerse)) {
			posicionNueva = posicionAbsoluta;
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
