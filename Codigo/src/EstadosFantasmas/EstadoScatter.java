package EstadosFantasmas;

import Entidad.Fantasma;
import Grilla.Grilla;
import Utilidad.Position;

public class EstadoScatter extends Estado {
	protected Position esquinaBuscada;
	
	
	public EstadoScatter(Fantasma f, Grilla g) {
		super(f, g);
		esquinaBuscada = new Position(miFantasma.getEsquinaBuscada().getFila() * 20, miFantasma.getEsquinaBuscada().getColumna() * 20);
	}

	@Override
	public Position siguientePosicion() {
		return masCercanoA(esquinaBuscada);
	}

	@Override
	public void afectar() {
		// TODO Auto-generated method stub
		System.out.println("Boo!");
	}

	@Override
	public String caminoImagen() {
		return miFantasma.getCaminoImagenColor();
	}

}
