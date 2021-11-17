package Logica;

import Entidad.EntidadGrafica;
import Esperador.EsperadorChase;
import GUI.JuegoGUI;
import Grilla.Grilla;
import Reloj.*;
import Utilidad.*;
import Highscore.*;
import Audio.MusicHandler;
import Audio.MusicPlayer;
import Datos.DataHandler;

public class Juego {
	protected JuegoGUI miGUI;
	protected Grilla miGrilla;
	
	protected Reloj relojPacman;
	protected Reloj relojFantasmas;
	
	protected Jugador jugador;
	protected Highscores highscores;
	
	protected MusicPlayer miMP;
	
	protected int puntaje;
	protected int cantPocionesBomba;
	
	protected boolean arrancoMusica;
	protected boolean partidaEnCurso;
	
	public Juego(JuegoGUI gui, Jugador j) {
		miGUI = gui;
		
		puntaje = 0;
		cantPocionesBomba = 0;

		jugador = j;
		puntaje = jugador.getPuntaje();
		
		highscores = DataHandler.getHighscore();
		
		miMP = new MusicPlayer();
		MusicHandler.inicializarReproductor(miMP);
		
		arrancoMusica = false;
		partidaEnCurso = false;
	}
	
	public void setGrilla(Grilla g) {
		miGrilla = g;
	}
	
	public void sumarPuntos(int p) {
		puntaje += p;
		pedirActualizarPuntos();
	}
	
	public void pedirActualizarPuntos() {
		miGUI.actualizarPuntaje(puntaje + "");
	}

	public void iniciarPartida() {
		if(!partidaEnCurso) {
			pedirActualizarPuntos();
			
			relojPacman = new RelojPacman(true, 1000, this);
			relojFantasmas = new RelojFantasmas(true, 1000, this);
			
			relojPacman.setIntervalo(1000 / miGrilla.getPacman().getPaso());
			relojFantasmas.setIntervalo(1000 / miGrilla.getRojo().getPaso());
			relojPacman.start();
			relojFantasmas.start();
			(new EsperadorChase(miGrilla.getMilisegundosEnScatter(), miGrilla)).start();
			
			partidaEnCurso = true;
		}
	}
	
	protected void cargarlePuntosAlJugador() {
		jugador.setPuntaje(puntaje);
	}
	
	protected void frenarTodosLosRelojes() {
		if(partidaEnCurso) {
			relojFantasmas.stop();
			relojPacman.stop();
		}
		
		if(arrancoMusica) {
			miMP.stop();
		}
	}
	
	public void finalizarPartida() {
		cargarlePuntosAlJugador();
		frenarTodosLosRelojes();
		partidaEnCurso = false;
	}
	
	public void perdio() {
		miGUI.mostrarBotonesPerdio();
	}
	
	public void gano() {
		miGUI.mostrarBotonesGano();
	}
	
	public void iniciarMusica() {
		if(arrancoMusica) {
			miMP.skip();
			miMP = miMP.clone();
		} else {
			arrancoMusica = true;
		}
			
		miMP.start();
	}
	
	public void pausarMusica() {
		miMP.stop();
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
	
	public void pedirAlmacenarBomba() {
		cantPocionesBomba++;
		miGUI.establecerVisible(miGUI.getLabelPocionBomba(), true);
		miGUI.establecerVisible(miGUI.getLabelPocionBombaCant(), true);
		miGUI.getLabelPocionBombaCant().setText(cantPocionesBomba + "");
	}
	
	public void consumirPocionBomba() {
		switch(cantPocionesBomba) {
			case 0:
				return;
			case 1: {
				miGUI.establecerVisible(miGUI.getLabelPocionBomba(), false);
				miGUI.establecerVisible(miGUI.getLabelPocionBombaCant(), false);
			}
			default:
				miGUI.getLabelPocionBombaCant().setText(cantPocionesBomba - 1 + "");
		}

		System.out.println("Consumió exitosamente la bomba");
		cantPocionesBomba--;			
		pedirEstablecerVisibleBomba(true);
		miGrilla.posicionarPocionBomba();
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
