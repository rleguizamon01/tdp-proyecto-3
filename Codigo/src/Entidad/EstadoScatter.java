package Entidad;

import Utilidad.Position;

public class EstadoScatter implements EstadoFantasma {
	private Position esquinaBuscada;
	
	public EstadoScatter(Position eb) {
		esquinaBuscada = eb;
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
		//La imagen en chase depende del fantasma.
		//Este metodo nunca se ejecutara.
		return "";
	}

}
