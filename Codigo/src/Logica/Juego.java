package Logica;

import Audio.AudioPlayer;
import GUI.JuegoGUI;
import Grilla.Grilla;
import Reloj.*;

public class Juego {
	protected JuegoGUI miGUI;
	protected Grilla miGrilla;
	
	protected RelojPacman relojP;
	protected RelojFantasmas relojF;
	
	protected AudioPlayer miSoundPlayer;
	protected AudioPlayer miMusicPlayer;
	
	protected int puntaje;
	
	public Juego(JuegoGUI gui) {
		miGUI = gui;
		miGrilla = new Grilla(this, null);
		
		//TODO: SONIDO
		miSoundPlayer = new AudioPlayer();
		miMusicPlayer = new AudioPlayer();
		
		relojP = new RelojPacman(this, miGrilla.getVelocidadPacman());
		relojF = new RelojFantasmas(this, miGrilla.getRojo().getVelocidad(), miGrilla.getRosa().getVelocidad(),
									miGrilla.getAzul().getVelocidad(), miGrilla.getNaranja().getVelocidad());
		
		puntaje = 0;
	}
	
	public void iniciarPartida() {
		relojP.setJugable(true);
		relojF.setJugable(true);
		relojP.run();
		relojF.run();
	}
	
	public void finalizarPartida() {
		relojP.setJugable(false);
		relojF.setJugable(false);
		relojP.stop();
		relojF.stop();
	}
	
	public void sumarPuntos(int p) {
		puntaje += p;
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

	public void pedirActualizarDireccion(char d) {
		miGrilla.actualizarDireccionPacman(d);
	}
	
	public void actualizarVelocidad(int v) {
		relojP.setVelocidad(v);
	}
}
