package Entidad;

import Grilla.Grilla;
import IAs.ChaseNaranja;
import Utilidad.Position;

public class FantasmaNaranja extends Fantasma {

	public FantasmaNaranja(int f, int c, Grilla g) {
		super(f, c, g);
		velocidad = 300;
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
