package Entidad;

import Grilla.Grilla;
import IAs.ChaseRosa;
import Utilidad.Position;

public class FantasmaRosa extends Fantasma {

	public FantasmaRosa(int f, int c, Grilla g) {
		super(f, c, g);
		esquina = new Position(0, 2);
		chase = new EstadoChase(new ChaseRosa());
		scatter = new EstadoScatter(esquina);
	}

	@Override
	public String getCaminoImagen() {
		// TODO Auto-generated method stub
		return null;
	}

}
