package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class PocionBomba extends Pocion{
	private static final int ANCHO_P_BOMBA = 20;
	private static final int ALTO_P_BOMBA = 20;
	
	public PocionBomba(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_POCION;
	}
	
	public PocionBomba(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_P_BOMBA)/2, 20*columna+(20-ALTO_P_BOMBA)/2, fila, columna, ANCHO_P_BOMBA, ALTO_P_BOMBA, g);
		puntos = PUNTOS_POCION;
	}

	@Override
	public void afectar() {
		// TODO Auto-generated method stub
		
	}
	
	public String getPacmanAlteradoCI() {
		return ResourceHandler.getPacmanBombaCI(miGrilla.getPacman().getDireccion());
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPocionBombaCI();
	}

}
