package Entidad;

import Grilla.Grilla;
import IAs.ChaseAzul;
import Utilidad.Position;

public class FantasmaAzul extends Fantasma {

	public FantasmaAzul(int f, int c, Grilla g) {
		super(f, c, g);
		velocidad = 250;
		esquina = new Position(35, 0);
		chase = new EstadoChase(new ChaseAzul());
		scatter = new EstadoScatter(esquina);
	}

	@Override
	public String getCaminoImagen() {
		// TODO Auto-generated method stub
		return null;
	}

}
