package Entidad;

import IAs.ChaseIA;
import Utilidad.Position;

public class EstadoChase implements EstadoFantasma {
	private ChaseIA ia;
	private Fantasma miFantasma;
	
	public EstadoChase(ChaseIA ia, Fantasma f) {
		this.ia = ia;
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
