package Launcher;

import Datos.DataHandler;
import GUI.*;
import Grilla.*;
import ResourceHandler.ResourceHandler;
import ResourceHandler.SkinStrategy;
import Logica.*;

public class Launcher {
	private static JuegoGUI JGUI;
	private static Juego juego;
	private static Grilla grilla;
	private static int index;
	private static EstrategiaNivel[] niveles;
	
	public static void main(String[] args) {
		DataHandler.cargarFuentes();
		new MenuGUI();
		//lanzarJuego(ResourceHandler.SKIN_AMONG_US);
	}
	
	public static void lanzarJuego(SkinStrategy skinElegida) {
		ResourceHandler.setStrategy(skinElegida);
		index = 0;
		
		JGUI = new JuegoGUI();
		
		juego = new Juego(JGUI);
		
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

}
