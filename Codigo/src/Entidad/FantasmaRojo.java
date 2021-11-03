package Entidad;

import EstadosFantasmas.*;
import Grilla.Grilla;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;

public class FantasmaRojo extends Fantasma {
	private static final int VELOCIDAD_ROJO = 2;
	
	public FantasmaRojo(int fila, int columna, Grilla g) {
		super(fila, columna, g);
		velocidad = VELOCIDAD_ROJO;
		esquinaBuscada = new Position(26, 0);
		
		chase = null;
		scatter = new EstadoScatter(this, miGrilla);
		run = null;
		dead = null;
		
		miEstado = scatter;
	}

	@Override
	public String getCaminoImagenColor() {
		return ResourceHandler.getFantasmaRojoCI(direccion);
	}

}
