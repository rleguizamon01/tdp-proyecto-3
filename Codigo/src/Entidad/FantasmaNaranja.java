package Entidad;

import Grilla.Grilla;
import IAs.ChaseNaranja;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class FantasmaNaranja extends Fantasma {

	public FantasmaNaranja(int f, int c, Grilla g) {
		super(f, c, g);
		velocidad = 300;
		esquina = new Position(35, 27);
		chase = new EstadoChase(new ChaseNaranja(), this);
		scatter = new EstadoScatter(esquina, this);
	}

	@Override
	public String imagenFantasmaColor() {
		return ResourceHandler.getFantasmaNaranjaCI(direccion);
	}

}
