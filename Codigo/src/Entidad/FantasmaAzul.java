package Entidad;

import Grilla.Grilla;
import IAs.ChaseAzul;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class FantasmaAzul extends Fantasma {

	public FantasmaAzul(int f, int c, Grilla g) {
		super(f, c, g);
		velocidad = 250;
		esquina = new Position(35, 0);
		chase = new EstadoChase(new ChaseAzul(), this);
		scatter = new EstadoScatter(esquina, this);
	}

	@Override
	public String imagenFantasmaColor() {
		return ResourceHandler.getFantasmaAzulCI(direccion);
	}

}
