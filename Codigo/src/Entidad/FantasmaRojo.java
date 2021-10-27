package Entidad;

import Grilla.Grilla;
import Utilidad.Position;
import IAs.ChaseRojo;
import ResourceHandler.ResourceHandler;

public class FantasmaRojo extends Fantasma {
	public FantasmaRojo(int f, int c, int p, Grilla g) {
		super(f, c, p, g);
		esquina = new Position(0, 25);
		chase = new EstadoChase(new ChaseRojo());
		scatter = new EstadoScatter(esquina);
	}

	@Override
	public String getCaminoImagen() {
		// TODO Arreglar esto
		if(miEstado == chase || miEstado == scatter)
			return ResourceHandler.getFantasmaRojoCI();
		else
			return miEstado.caminoImagen();
	}

}
