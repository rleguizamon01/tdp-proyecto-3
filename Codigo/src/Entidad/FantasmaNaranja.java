package Entidad;

import EstadosFantasmas.*;
import Grilla.Grilla;
import IAs.ChaseIANaranja;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class FantasmaNaranja extends Fantasma {
	protected static final int VELOCIDAD_NARANJA = 4;
	
	public FantasmaNaranja(int fila, int columna, Grilla g) {
		super(fila, columna, g);
		velocidad = VELOCIDAD_NARANJA;
		esquinaBuscada = new Position(3, 31);
		
		chase = new EstadoChase(this, miGrilla, new ChaseIANaranja(miGrilla, this));
		scatter = new EstadoScatter(this, miGrilla);
		run = new EstadoRun(this, miGrilla);
		dead = new EstadoDead(this, miGrilla);
		
		miEstado = scatter;
	}

	@Override
	public String getCaminoImagenColor() {
		return ResourceHandler.getFantasmaNaranjaCI(direccion);
	}

}
