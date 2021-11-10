package EstadosFantasmas;

import Entidad.Fantasma;
import Grilla.Grilla;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class EstadoDead extends Estado {
	public EstadoDead(Fantasma f, Grilla g) {
		super(f, g);
	}

	@Override
	public Position siguientePosicion() {
		Position zonaSpawn = miFantasma.getPosicionSpawn();
		Position puntoSpawn = new Position(zonaSpawn.getFila()*20, zonaSpawn.getColumna()*20);
		return masCercanoA(puntoSpawn);
	}

	@Override
	public void afectar() {
		//El fantasma muerto no interactua con nada de ninguna manera :)
	}

	@Override
	public String caminoImagen() {
		return ResourceHandler.getFantasmaMuertoCI(miFantasma.getDireccion());
	}

	public boolean estaMuerto() {
		return true;
	}
	
}
