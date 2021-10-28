package Entidad;

import Grilla.Grilla;
import Utilidad.Position;
import IAs.ChaseRojo;
import ResourceHandler.ResourceHandler;

public class FantasmaRojo extends Fantasma {
	public FantasmaRojo(int f, int c, Grilla g) {
		super(f, c, g);
		velocidad = 150;
		esquina = new Position(26, 0);
		chase = new EstadoChase(new ChaseRojo(), this);
		scatter = new EstadoScatter(esquina, this, miGrilla);
		ponerEnScatter();
	}

	@Override
	public String imagenFantasmaColor() {
		return ResourceHandler.getFantasmaRojoCI(direccion);
	}
}
