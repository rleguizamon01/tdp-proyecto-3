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
		lanzarMenu();
		//lanzarJuego(ResourceHandler.SKIN_AMONG_US);
	}
	
	public static void lanzarMenu() {
		new MenuGUI();
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
		jugador = new Jugador("");
		
		lanzar();
	}
	
	public static void siguienteNivel() {
		index++;
		JGUI.cerrar();
		
		if(index == niveles.length) {
			lanzarPantallaGanaste();
		} else {
			lanzar();
		}
	}
	
	public static void lanzarPantallaGanaste() {
		lanzarPantallaFinal("¡GANASTE!", FinalPartidaGUI.COLOR_GANAR);
	}
	
	public static void lanzarPantallaPerdiste() {
		lanzarPantallaFinal("PERDISTE :(", FinalPartidaGUI.COLOR_PERDER);
	}
	
	protected static void lanzarPantallaFinal(String fin, java.awt.Color c) {
		JGUI.cerrar();
		new FinalPartidaGUI(fin, jugador, c);
	}

}
