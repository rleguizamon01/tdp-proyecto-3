package EstadosFantasmas;

import Entidad.Fantasma;
import Grilla.Grilla;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class EstadoRun extends Estado {
	protected static final int PUNTOS_CUANDO_MUERE = 5000;
	
	public EstadoRun(Fantasma f, Grilla g) {
		super(f, g);
	}

	@Override
	public Position siguientePosicion() {
		return masLejanoA(miGrilla.getPacman().getPosicionAbsoluta());
	}

	@Override
	public void afectar() {
		miGrilla.sumarPuntos(PUNTOS_CUANDO_MUERE);
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
