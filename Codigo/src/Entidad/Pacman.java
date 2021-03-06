package Entidad;

import Grilla.Grilla;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class Pacman extends EntidadMovil {
	private static final int ANCHO_PACMAN = 15;
	private static final int ALTO_PACMAN = 18;
	private static final int VELOCIDAD_PACMAN = 4;
	private static final int PASO_PACMAN = 24;
	
	protected Pocion estadoAlterado;
	
	public Pacman(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g, int v, int p, char d) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		velocidad = v;
		paso = p;
		direccion = d;
		estadoAlterado = null;
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
		
		int offset = 2; //Una ayuda para que sea levemente m?s f?cil doblar
		
		Position posicionNuevaEsquinaInferiorDerecha = new Position(); //Es la esquina inferior derecha del pacman ya movido.
		posicionNuevaEsquinaInferiorDerecha.setFila(posicionNueva.getFila() + ancho - 1);
		posicionNuevaEsquinaInferiorDerecha.setColumna(posicionNueva.getColumna() + alto - 1);
		
		Position posicionNuevaEsquinaSuperiorDerecha = new Position(); //Es la esquina superior derecha del pacman ya movido.
		posicionNuevaEsquinaSuperiorDerecha.setFila(posicionNueva.getFila() + ancho - offset);
		posicionNuevaEsquinaSuperiorDerecha.setColumna(posicionNueva.getColumna());
		
		Position posicionNuevaEsquinaInferiorIzquierda = new Position(); //Es la esquina inferior izquierda del pacman ya movido.
		posicionNuevaEsquinaInferiorIzquierda.setFila(posicionNueva.getFila());
		posicionNuevaEsquinaInferiorIzquierda.setColumna(posicionNueva.getColumna() + alto - offset);
		
		if(!miGrilla.zonaLibre(posicionNueva) || !miGrilla.zonaLibre(posicionNuevaEsquinaInferiorDerecha) ) {
			int f = posicionNueva.getFila() / 20;
			int c = posicionNueva.getColumna() / 20;
			Position posicionCentrado = new Position(20*f+(20-ancho)/2, 20*c+(20-alto)/2);
				
			posicionNueva = miGrilla.zonaLibre(posicionCentrado) ? posicionCentrado : posicionAbsoluta;

		} else if (!miGrilla.zonaLibre(posicionNuevaEsquinaSuperiorDerecha) || !miGrilla.zonaLibre(posicionNuevaEsquinaInferiorIzquierda)) {
			posicionNueva = posicionAbsoluta.clone();
		}
		
		return posicionNueva;
	}

	@Override
	public void afectar() {
		//Pacman no hace nada.
	}
	
	public boolean estaMuerto() {
		return false; //Si pacman esta muerto, el jugador ya perdio, por lo que esta consulta no se haria.
	}

	@Override
	public String getCaminoImagen() {
		return estadoAlterado == null ? ResourceHandler.getPacmanCI(direccion) : estadoAlterado.getPacmanAlteradoCI();
	}

	public void alterarEstado(Pocion p) {
		estadoAlterado = p;
		eg.actualizarImagen();
	}
	
}
