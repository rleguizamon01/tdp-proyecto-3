package Entidad;

import IAs.ChaseIA;
import Utilidad.Position;

public class EstadoChase implements EstadoFantasma {
	private ChaseIA ia;
	
	public EstadoChase(ChaseIA ia) {
		this.ia = ia;
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
