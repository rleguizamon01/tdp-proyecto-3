package Entidad;

import Grilla.Grilla;
import Utilidad.Position;
import IAs.ChaseRojo;
import ResourceHandler.ResourceHandler;

public class FantasmaRojo extends Fantasma {
	public FantasmaRojo(int f, int c, Grilla g) {
		super(f, c, g);
		velocidad = 150;
		esquina = new Position(0, 25);
		chase = new EstadoChase(new ChaseRojo(), this);
		scatter = new EstadoScatter(esquina, this);
		ponerEnScatter();
	}

	@Override
	public String imagenFantasmaColor() {
		return ResourceHandler.getFantasmaRojoCI(direccion);
	}
}
