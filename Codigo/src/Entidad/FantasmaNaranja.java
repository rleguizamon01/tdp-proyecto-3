package Entidad;

import Grilla.Grilla;
import IAs.ChaseNaranja;
import Utilidad.Position;

public class FantasmaNaranja extends Fantasma {

	public FantasmaNaranja(int f, int c, int p, Grilla g) {
		super(f, c, p, g);
		esquina = new Position(35, 27);
		chase = new EstadoChase(new ChaseNaranja());
		scatter = new EstadoScatter(esquina);
	}

	@Override
	public String getCaminoImagen() {
		// TODO Auto-generated method stub
		return null;
	}

}
