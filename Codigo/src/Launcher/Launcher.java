package Launcher;

import Datos.DataHandler;
import GUI.*;
import Grilla.*;
import Highscore.Jugador;
import ResourceHandler.ResourceHandler;
import ResourceHandler.SkinStrategy;
import Logica.*;

public class Launcher {
	private static JuegoGUI JGUI;
	private static Juego juego;
	private static Grilla grilla;
	private static Jugador jugador;
	private static int index;
	private static EstrategiaNivel[] niveles;
	
	public static void main(String[] args) {
		DataHandler.cargarFuentes();
		new MenuGUI();
		//lanzarJuego(ResourceHandler.SKIN_AMONG_US);
	}
	
	protected static void lanzar() {
		JGUI = new JuegoGUI();
		
		juego = new Juego(JGUI, jugador);
		
		JGUI.setJuego(juego);
		JGUI.initialize();
		JGUI.initializeMatrix();
		
		grilla = new Grilla(juego);
		
		niveles = new EstrategiaNivel[]{new NivelUno(juego, grilla), new NivelDos(juego, grilla), new NivelTres(juego, grilla)};
		
		grilla.setNivelActual(niveles[index]);
		grilla.inicializar();
		
		juego.setGrilla(grilla);
		
		JGUI.abrir();
	}
	
	public static void lanzarJuego(SkinStrategy skinElegida) {
		ResourceHandler.setStrategy(skinElegida);
		index = 0;
		jugador = new Jugador("pepe supremo");
		
		lanzar();
	}
	
	public static void siguienteNivel() {
		index++;
		
		if(index == niveles.length)
			index = 0;
		
		juego.cargarlePuntosAlJugador();
		juego.frenarTodosLosRelojes();
		JGUI.cerrar();
		
		lanzar();
	}

}
