package Logica;

import Entidad.Entidad;
import Entidad.EntidadGrafica;
import GUI.JuegoGUI;
import Grilla.Grilla;
import Utilidad.*;

public class Juego {
	protected JuegoGUI miGUI;
	protected Grilla miGrilla;
	
	public Juego(JuegoGUI gui) {
		miGUI = gui;
	}
	
	public void setGrilla(Grilla g) {
		miGrilla = g;
	}
	
	public void actualizar(Position p, String path) {
		miGUI.actualizarPiso(p, path);
	}
	
	public void agregarLabel(EntidadGrafica eg) {
		miGUI.agregarLabel(eg.getLabel());
	}
	
	public void pedirActualizarDireccion(char d) {
		miGrilla.actualizarDireccionPacman(d);
	}
	
	public void pedirMoverPacman() {
		miGrilla.moverPacman();
	}
}
