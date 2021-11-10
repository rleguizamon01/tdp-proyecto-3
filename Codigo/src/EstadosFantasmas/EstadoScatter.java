package EstadosFantasmas;

import Entidad.Fantasma;
import Grilla.Grilla;
import Utilidad.Position;

public class EstadoScatter extends Estado {
	protected static Position posSalirDeGhostPen = new Position(17, 0);
	protected Position esquinaBuscada;
	protected int pixelesCelda = 20;
	protected boolean salioDeGhostPen;
	
	public EstadoScatter(Fantasma f, Grilla g) {
		super(f, g);
		esquinaBuscada = new Position(miFantasma.getEsquinaBuscada().getFila() * 20, miFantasma.getEsquinaBuscada().getColumna() * 20);
		salioDeGhostPen = false;
	}

	@Override
	public Position siguientePosicion() {
		if(!salioDeGhostPen) {
			salioDeGhostPen = !estaEnGhostPen();
			return masCercanoA(posSalirDeGhostPen);
		} else {
			return masCercanoA(esquinaBuscada);
		}		
	}

	@Override
	public void afectar() {
		miGrilla.perdio();
	}

	@Override
	public String caminoImagen() {
		return miFantasma.getCaminoImagenColor();
	}
	
	protected boolean estaEnGhostPen() {
		Position aux = miFantasma.getPosicionSpawn().clone();
		aux.setFila(aux.getFila()*pixelesCelda);
		aux.setColumna(aux.getColumna()*pixelesCelda);
		
		return Position.distancia(aux, miFantasma.getPosicionAbsoluta()) <= 2.2*pixelesCelda;
	}
	
	public boolean estaMuerto() {
		return false;
	}

}
