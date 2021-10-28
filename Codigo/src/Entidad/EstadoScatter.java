package Entidad;

import Utilidad.Position;

public class EstadoScatter implements EstadoFantasma {
	private Position esquinaBuscada;
	private Fantasma miFantasma;
	
	public EstadoScatter(Position eb, Fantasma f) {
		esquinaBuscada = eb;
		miFantasma = f;
	}
	
	@Override
	public Position siguientePosicion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afectar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String caminoImagen() {
		return miFantasma.imagenFantasmaColor();
	}

}
