package Launcher;

import Logica.*;
import GUI.*;
import Grilla.*;
import ResourceHandler.*;

public class Launcher {
	
	
	public static void main(String[] args) {
		//Es vital que hasta que implementemos menu esta linea este aca.
		ResourceHandler.setStrategy(ResourceHandler.SKIN_AMONG_US);
		
		Juego j = new Juego();
		Grilla g = new Grilla();
		JuegoGUI jGUI = new JuegoGUI();
		
		j.inicializar(jGUI, g);
		
		EstrategiaNivel nivel = new NivelDos(j, g);
		
		g.inicializar(j, nivel);
		
		jGUI.setJuego(j);
		
		jGUI.hacerVisible();
		j.iniciarPartida();
	}
}
