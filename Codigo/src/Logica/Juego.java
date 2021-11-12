package Logica;

import Entidad.EntidadGrafica;
import Esperador.EsperadorChase;
import GUI.JuegoGUI;
import Grilla.Grilla;
import Reloj.*;
import Utilidad.*;

public class Juego {
	protected JuegoGUI miGUI;
	protected Grilla miGrilla;
	protected Reloj relojPacman;
	protected Reloj relojFantasmas;
	
	public Juego(JuegoGUI gui) {
		miGUI = gui;
		relojPacman = new RelojPacman(true, 1000, this);
		relojFantasmas = new RelojFantasmas(true, 1000, this);
	}
	
	public void setGrilla(Grilla g) {
		miGrilla = g;
	}
	
	public void iniciarPartida() {
		relojPacman.setIntervalo(1000 / miGrilla.getPacman().getPaso());
		relojFantasmas.setIntervalo(1000 / miGrilla.getRojo().getPaso());
		relojPacman.start();
		relojFantasmas.start();
		(new EsperadorChase(miGrilla.getMilisegundosEnScatter(), miGrilla)).start();
	}
	
	public void finalizarPartida() {
		relojPacman.stop();
		relojFantasmas.stop();
	}
	
	public void actualizar(Position p, String path) {
		miGUI.actualizarPiso(p, path);
	}
	
	public void agregarLabel(EntidadGrafica eg) {
		miGUI.agregarLabel(eg.getLabel());
	}
	
	public void removerLabel(EntidadGrafica eg) {
		miGUI.removerLabel(eg.getLabel());
	}
	
	public void pedirEstablecerVisibleVelocidad(boolean b) {
		miGUI.establecerVisible(miGUI.getLabelEfectoVelocidad(), b);
	}
	
	public void pedirEstablecerVisiblePowerPellet(boolean b) {
		miGUI.establecerVisible(miGUI.getLabelEfectoPowerPellet(), b);
	}
	
	public void pedirEstablecerVisibleBomba(boolean b) {
		miGUI.establecerVisible(miGUI.getLabelEfectoBomba(), b);
	}
	
	public void pedirActualizarDireccion(char d) {
		miGrilla.actualizarDireccionPacman(d);
	}
	
	public void pedirMoverPacman() {
		miGrilla.moverPacman();
	}
	
	public void pedirMoverRojo() {
		miGrilla.moverFantasma(miGrilla.getRojo());
	}
	
	public void pedirMoverRosa() {
		miGrilla.moverFantasma(miGrilla.getRosa());
	}
	
	public void pedirMoverAzul() {
		miGrilla.moverFantasma(miGrilla.getAzul());
	}
	
	public void pedirMoverNaranja() {
		miGrilla.moverFantasma(miGrilla.getNaranja());
	}
}
