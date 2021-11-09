package Entidad;

import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class Portal extends EntidadFija {
	private static final int ANCHO_PORTAL = 20;
	private static final int ALTO_PORTAL = 20;
	private static final int PUNTOS_PORTAL = 0;
	private Portal PORTAL_DESTINO;
	private int recargado;
	
	public Portal(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_PORTAL;
		recargado=5;
	}
	
	public Portal(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_PORTAL)/2, 20*columna+(20-ALTO_PORTAL)/2, fila, columna, ANCHO_PORTAL, ALTO_PORTAL, g);
		puntos = PUNTOS_PORTAL;
		recargado=5;
	}

	@Override
	public void afectar() {
		if (recargado==5) {
			miGrilla.teletransportarPacman(PORTAL_DESTINO);
			descargarDestino();
		}else
			recargado++;
	}

	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPortalCI();
	}

	public void setPortalDestino(Portal portalDestino) {
		PORTAL_DESTINO=portalDestino;
	}
	
	public void setRecargado(int c) {
		recargado=c;
	}
	
	private void descargarDestino() {
		PORTAL_DESTINO.setRecargado(0);;
	}
}