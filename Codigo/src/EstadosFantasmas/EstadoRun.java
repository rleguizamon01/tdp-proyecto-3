package EstadosFantasmas;

import Entidad.Fantasma;
import Grilla.Grilla;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class EstadoRun extends Estado {
	
	public EstadoRun(Fantasma f, Grilla g) {
		super(f, g);
	}

	@Override
	public Position siguientePosicion() {
		return masLejanoA(miGrilla.getPacman().getPosicionAbsoluta());
	}

	@Override
	public void afectar() {
		miGrilla.sumarPuntos(miFantasma.PUNTOS_MUERTE);
		miFantasma.ponerEnDead();
	}

	@Override
	public String caminoImagen() {
		return ResourceHandler.getFantasmaVulnerableCI(miFantasma.getDireccion());
	}
	
	public boolean estaMuerto() {
		return false;
	}

}
