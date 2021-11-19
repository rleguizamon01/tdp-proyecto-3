package Entidad;

import Esperador.EsperadorVelocidad;
import Grilla.Grilla;
import ResourceHandler.ResourceHandler;

public class PocionVelocidad extends Pocion {
	private static final int ANCHO_P_VELOCIDAD = 20;
	private static final int ALTO_P_VELOCIDAD = 20;
	private static final int DURACION_P_VELOCIDAD = 8000; //en ms
	private static final float COEFICIENTE = 1.75f;
	private static final String PATH_SFX = "/RecursosSoundEffects/pocionvelocidad.mp3";
	
	protected float coeficienteDeAceleracion;
	
	public PocionVelocidad(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		super(xAbs, yAbs, xZona, yZona, w, h, g);
		puntos = PUNTOS_POCION;
		duracion = DURACION_P_VELOCIDAD;
		coeficienteDeAceleracion = COEFICIENTE;
	}
	
	public PocionVelocidad(int fila, int columna, Grilla g) {
		super(20*fila+(20-ANCHO_P_VELOCIDAD)/2, 20*columna+(20-ALTO_P_VELOCIDAD)/2, fila, columna, ANCHO_P_VELOCIDAD, ALTO_P_VELOCIDAD, g);
		puntos = PUNTOS_POCION;
		duracion = DURACION_P_VELOCIDAD;
		coeficienteDeAceleracion = COEFICIENTE;
	}
	
	@Override
	public void afectar() {
		miGrilla.sumarPuntos(puntos);
		miGrilla.pedirReproducirSFX(PATH_SFX);
		miGrilla.pedirEstablecerEstadoVelocidad(true);
		int velocidadActual = miGrilla.getPacman().getVelocidad();
		miGrilla.getPacman().setVelocidad((int) Math.floor(coeficienteDeAceleracion*velocidadActual));
		miGrilla.getPacman().alterarEstado(this);
		(new EsperadorVelocidad(duracion, miGrilla, velocidadActual)).start();
		miGrilla.removerEntidad(this);
	}

	public String getPacmanAlteradoCI() {
		return ResourceHandler.getPacmanVelocidadCI(miGrilla.getPacman().getDireccion());
	}
	
	@Override
	public String getCaminoImagen() {
		return ResourceHandler.getPocionVelocidadCI();
	}

}
