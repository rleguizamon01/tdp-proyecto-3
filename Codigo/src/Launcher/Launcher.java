package Launcher;

import Datos.DataHandler;
import GUI.*;
import Grilla.*;
import ResourceHandler.ResourceHandler;
import Logica.*;

public class Launcher {

	public static void main(String[] args) {
		DataHandler.cargarFuentes();
		ResourceHandler.setStrategy(ResourceHandler.SKIN_AMONG_US);
		
		JuegoGUI JGUI = new JuegoGUI();
		
		Juego juego = new Juego(JGUI);
		
		JGUI.setJuego(juego);
		JGUI.initialize();
		JGUI.initializeMatrix();
		
		Grilla grilla = new Grilla(juego);
		grilla.setNivelActual(new NivelUno(juego, grilla));
		grilla.inicializar();
		
		juego.setGrilla(grilla);
		
		JGUI.abrir();
	}

}
