package Reloj;

import Logica.Juego;

public class RelojPacman extends Reloj {

	public RelojPacman(boolean spj, int in, Juego j) {
		super(spj, in, j);
	}

	@Override
	protected void notificar() {
		miJuego.pedirMoverPacman();
	}

}
