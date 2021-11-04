package Entidad;

import EstadosFantasmas.*;
import Grilla.Grilla;
import IAs.ChaseIARoja;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;

public class FantasmaRojo extends Fantasma {
	private static final int VELOCIDAD_ROJO = 2;
	
	public FantasmaRojo(int fila, int columna, Grilla g) {
		super(fila, columna, g);
		velocidad = VELOCIDAD_ROJO;
		esquinaBuscada = new Position(26, 0);
		
		chase = new EstadoChase(this, miGrilla, new ChaseIARoja(miGrilla));
		scatter = new EstadoScatter(this, miGrilla);
		run = new EstadoRun(this, miGrilla);
		dead = new EstadoDead(this, miGrilla);
		
		miEstado = scatter;
	}

	@Override
	public String getCaminoImagenColor() {
		return ResourceHandler.getFantasmaRojoCI(direccion);
	}

}
