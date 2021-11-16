package Entidad;

import Esperador.EsperadorBomba;
import Esperador.EsperadorVelocidad;
import Grilla.Grilla;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;

public class PocionBomba extends Pocion { 
	private static final int ANCHO_P_BOMBA = 20;
	private static final int ALTO_P_BOMBA = 20;
	private static final int DURACION_P_VELOCIDAD = 2000;
	private static final int RADIO_P_EXPLOSION = 5;
	private boolean estaActivada;
	private int radioExplosion;
	
	public PocionBomba(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g, boolean estaActivada, int radioExplosion) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_POCION;
		duracion = DURACION_P_VELOCIDAD;
		this.radioExplosion = radioExplosion;
		this.estaActivada = estaActivada;
	}
	
	public PocionBomba(int fila, int columna, Grilla g, boolean estaActivada) {
		super(20*fila+(20-ANCHO_P_BOMBA)/2, 20*columna+(20-ALTO_P_BOMBA)/2, fila, columna, ANCHO_P_BOMBA, ALTO_P_BOMBA, g);
		puntos = PUNTOS_POCION;
		duracion = DURACION_P_VELOCIDAD;
		radioExplosion = RADIO_P_EXPLOSION;
		this.estaActivada = estaActivada;
	}
	
	public PocionBomba(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_P_BOMBA)/2, 20*columna+(20-ALTO_P_BOMBA)/2, fila, columna, ANCHO_P_BOMBA, ALTO_P_BOMBA, g);
		puntos = PUNTOS_POCION;
		duracion = DURACION_P_VELOCIDAD;
		radioExplosion = RADIO_P_EXPLOSION;
		estaActivada = false;
	}

	@Override
	public void afectar() {
		if(!estaActivada) {
			miGrilla.removerEntidad(this);
			miGrilla.pedirAlmacenarBomba();
		}
	}
	
	public void consumir() {
		miGrilla.getPacman().alterarEstado(this);
		(new EsperadorBomba(duracion, miGrilla, this)).start();
	}
	
	public String getPacmanAlteradoCI() {
		return ResourceHandler.getPacmanBombaCI(miGrilla.getPacman().getDireccion());
	}

	@Override
	public String getCaminoImagen() {
		return estaActivada ? ResourceHandler.getBombaCI() : ResourceHandler.getPocionBombaCI();
	}
	
	public int getRadioExplosion() {
		return radioExplosion;
	}
}
