package Entidad;

import EstadosFantasmas.*;
import Grilla.Grilla;
import IAs.ChaseIARosa;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;

public class FantasmaRosa extends Fantasma {
	private static final int VELOCIDAD_ROSA = 2;
	
	public FantasmaRosa(int fila, int columna, Grilla g) {
		super(fila, columna, g);
		velocidad = VELOCIDAD_ROSA;
		esquinaBuscada = new Position(2, 0);
		
		chase = new EstadoChase(this, miGrilla, new ChaseIARosa(miGrilla));
		scatter = new EstadoScatter(this, miGrilla);
		run = new EstadoRun(this, miGrilla);
		dead = new EstadoDead(this, miGrilla);
		
		miEstado = scatter;
	}

	@Override
	public String getCaminoImagenColor() {
		return ResourceHandler.getFantasmaRosaCI(direccion);
	}

}
