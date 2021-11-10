package EstadosFantasmas;

import Entidad.Fantasma;
import Grilla.Grilla;
import Utilidad.Position;
import IAs.*;

public class EstadoChase extends Estado {
	protected ChaseIA miIA;
	
	public EstadoChase(Fantasma f, Grilla g, ChaseIA ia) {
		super(f, g);
		miIA = ia;
	}

	public Position siguientePosicion() {
		return masCercanoA(miIA.getTargetBlock());
	}

	public void afectar() {
		miGrilla.perdio();
	}

	public String caminoImagen() {
		return miFantasma.getCaminoImagenColor();
	}
	
	public boolean estaMuerto() {
		return false;
	}

}
