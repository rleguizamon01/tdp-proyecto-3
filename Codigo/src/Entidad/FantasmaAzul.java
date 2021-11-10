package Entidad;

import Grilla.Grilla;
import IAs.ChaseIAAzul;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;
import EstadosFantasmas.*;


public class FantasmaAzul extends Fantasma {
	private static final int VELOCIDAD_AZUL = 1;
	
	public FantasmaAzul(int fila, int columna, Grilla g) {
		super(fila, columna, g);
		velocidad = VELOCIDAD_AZUL;
		esquinaBuscada = new Position(31, 27);
		
		chase = new EstadoChase(this, miGrilla, new ChaseIAAzul(miGrilla));
		scatter = new EstadoScatter(this, miGrilla);
		run = new EstadoRun(this, miGrilla);
		dead = new EstadoDead(this, miGrilla);
		
		miEstado = scatter;
	}
	
	@Override
	public String getCaminoImagenColor() {
		return ResourceHandler.getFantasmaAzulCI(direccion);
	}

}
