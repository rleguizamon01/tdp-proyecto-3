package Entidad;

import Grilla.Grilla;
import IAs.ChaseAzul;
import Utilidad.Position;

public class FantasmaAzul extends Fantasma {

	public FantasmaAzul(int f, int c, int p, Grilla g) {
		super(f, c, p, g);
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
