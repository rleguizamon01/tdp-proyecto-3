package Entidad;

import Grilla.Grilla;
import IAs.ChaseRosa;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(int f, int c, Grilla g) {
		super(f, c, g);
		velocidad = 100;
		esquina = new Position(0, 2);
		chase = new EstadoChase(new ChaseRosa(), this);
		scatter = new EstadoScatter(esquina, this);
	}

	@Override
	public String imagenFantasmaColor() {
		return ResourceHandler.getFantasmaRosaCI(direccion);
	}

}
