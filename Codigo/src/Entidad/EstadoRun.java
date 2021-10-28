package Entidad;

import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class EstadoRun implements EstadoFantasma {
	protected Fantasma miFantasma;
	
	public EstadoRun(Fantasma f) {
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
		return ResourceHandler.getFantasmaVulnerableCI(miFantasma.getDireccion());
	}

}
