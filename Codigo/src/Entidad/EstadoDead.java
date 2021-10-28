package Entidad;

import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class EstadoDead implements EstadoFantasma {
	private Fantasma miFantasma;

	public EstadoDead(Fantasma f) {
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
		return ResourceHandler.getFantasmaMuertoCI(miFantasma.getDireccion());
	}

}
