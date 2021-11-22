package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Logica.Juego;
import Launcher.Launcher;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Datos.FontHandler;
import Highscore.Jugador;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class JuegoGUI {
	private static final boolean MUSICA_POR_DEFECTO = false; //Si la musica se vuelve pesada al corregir, cambiar esta constante a false!
	private static final boolean EFECTOS_POR_DEFECTO = true; //Si los sfx se vuelven pesados al corregir, cambiar esta constante a false!
	
	//Constantes del tamaño de la ventana:
	private static final int W = 900;
	private static final int H = 730;

	private static final int ALTO = 36; //Cantidad de filas en la grilla.
	private static final int ANCHO = 28;//Cantidad de columnas en la grilla
	private static final int PIXELES = 20;//Tamaño en px de cada celda.
	
	private JFrame frmPacman;
	private JLayeredPane panelContenedorDeGrilla; //Seran el piso y las paredes del laberinto
	private JLabel[][] matrizLabels;
	private JTextArea txtrCaptadorDeEventos;
	private JPanel panelInformacion;
	private JLabel puntajeTituloLabel;
	private JLabel puntajeLabel;
	private JPanel panelEfectos;
	private JLabel lblEfectoVelocidad;
	private JLabel lblEfectoPowerPellet;
	private JLabel lblEfectoBomba;
	private JLabel lblTituloEfectos;
	private JPanel panelMusica;
	private JButton btnMusica;
	private JLabel lblMusicIcon;
	private JPanel panelNiveles;
	private JLabel lblTituloNiveles;
	private JButton btnNiveles;
	private JLabel lblPocionBomba;
	private JLabel lblPocionBombaCant;
	
	private Jugador miJugador;
	private Juego miJuego;

	private static final String PATH_MUSIC = "/RecursosMenu/musicSymbol.png";
	private static final String PATH_PLAY = "/RecursosMenu/playButton.png";
	private static final String PATH_PAUSE = "/RecursosMenu/stopButton.png";
	private boolean hayMusica;
	private boolean haySFX;
	private JButton btnEfectos;
	private JLabel lblEffectsIcon;

	public JuegoGUI(Jugador j) {
		miJugador = j;
	}
	
	public void setJuego(Juego j) {
		miJuego = j;
	}
	
	/**
	 * Codigo autogenerado por WindowBuilder:
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		hayMusica = MUSICA_POR_DEFECTO;
		haySFX = EFECTOS_POR_DEFECTO;
		
		frmPacman = new JFrame();
		frmPacman.setIconImage(Toolkit.getDefaultToolkit().getImage(JuegoGUI.class.getResource("/RecursosMenu/mati.png")));
		frmPacman.setTitle("PACMAN!");
		frmPacman.setBounds(22, 0, W, H);
		frmPacman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPacman.getContentPane().setLayout(null);
		frmPacman.getContentPane().setBackground(java.awt.Color.BLACK);
		
		txtrCaptadorDeEventos = new JTextArea();
		txtrCaptadorDeEventos.setBounds(0, 0, 1, 1);
		frmPacman.getContentPane().add(txtrCaptadorDeEventos);
		txtrCaptadorDeEventos.grabFocus();
		
		txtrCaptadorDeEventos.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent k) {
				int keyCode = k.getKeyCode();
			    
				switch( keyCode ) {
				    case KeyEvent.VK_UP:
			        	miJuego.pedirActualizarDireccion('N');
			        	break;
			        case KeyEvent.VK_DOWN:
			            miJuego.pedirActualizarDireccion('S');
			            break;
			        case KeyEvent.VK_LEFT:
			        	miJuego.pedirActualizarDireccion('O');
			            break;
			        case KeyEvent.VK_RIGHT:
			        	miJuego.pedirActualizarDireccion('E');
			            break;
			        case KeyEvent.VK_M:
			        	invertirMusica();
			        	break;
			        case KeyEvent.VK_B:
			        	miJuego.consumirPocionBomba();
			        	break;
			    }
			}

			@Override
			public void keyReleased(KeyEvent k) {
			}

			@Override
			public void keyTyped(KeyEvent k) {
			}
		});
		
		panelContenedorDeGrilla = new JLayeredPane();
		panelContenedorDeGrilla.setBounds(12, 10, ANCHO*PIXELES, ALTO*PIXELES);
		panelContenedorDeGrilla.setBorder(new LineBorder(java.awt.Color.WHITE));
		frmPacman.getContentPane().add(panelContenedorDeGrilla);
		panelContenedorDeGrilla.setLayout(null);
		
		panelInformacion = new JPanel();
		panelInformacion.setBackground(java.awt.Color.BLACK);
		panelInformacion.setBounds(ANCHO*PIXELES+100, 11, 200, 125);
		panelInformacion.setLayout(null);
		panelInformacion.setBorder(new LineBorder(java.awt.Color.WHITE));
		frmPacman.getContentPane().add(panelInformacion);
				
		puntajeTituloLabel = new JLabel("SCORE");
		puntajeTituloLabel.setForeground(java.awt.Color.WHITE);
		puntajeTituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		puntajeTituloLabel.setBounds(10, 11, 176, 38);
		puntajeTituloLabel.setFont(FontHandler.FUENTE_H3);
		panelInformacion.add(puntajeTituloLabel);
		
		puntajeLabel = new JLabel(miJugador.getPuntaje() + "");
		puntajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		puntajeLabel.setFont(FontHandler.FUENTE_H3);
		puntajeLabel.setForeground(java.awt.Color.WHITE);
		puntajeLabel.setBounds(10, 60, 176, 54);
		panelInformacion.add(puntajeLabel);
		
		panelEfectos = new JPanel();
		panelEfectos.setBackground(java.awt.Color.BLACK);
		panelEfectos.setBorder(new LineBorder(java.awt.Color.WHITE));
		panelEfectos.setBounds(660, 147, 200, 200);
		panelEfectos.setLayout(null);
		frmPacman.getContentPane().add(panelEfectos);
		
		String aux = ResourceHandler.getEfectoVelocidadCI();
		
		lblEfectoVelocidad = new JLabel();
		lblEfectoVelocidad.setBounds(10, 47, 40, 40);
		lblEfectoVelocidad.setIcon(new ImageIcon(JuegoGUI.class.getResource(aux)));
		lblEfectoVelocidad.setVisible(false);
		panelEfectos.add(lblEfectoVelocidad);
		
		aux = ResourceHandler.getEfectoPowerPelletCI();
		
		lblEfectoPowerPellet = new JLabel();
		lblEfectoPowerPellet.setBounds(10, 98, 40, 40);
		lblEfectoPowerPellet.setIcon(new ImageIcon(JuegoGUI.class.getResource(aux)));
		lblEfectoPowerPellet.setVisible(false);
		panelEfectos.add(lblEfectoPowerPellet);
		
		aux = ResourceHandler.getEfectoBombaCI();
		
		lblEfectoBomba = new JLabel();
		lblEfectoBomba.setBounds(10, 149, 40, 40);
		lblEfectoBomba.setIcon(new ImageIcon(JuegoGUI.class.getResource(aux)));
		lblEfectoBomba.setVisible(false);
		panelEfectos.add(lblEfectoBomba);
		
		lblTituloEfectos = new JLabel("EFFECTS");
		lblTituloEfectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloEfectos.setFont(FontHandler.FUENTE_H3);
		lblTituloEfectos.setForeground(java.awt.Color.WHITE);
		lblTituloEfectos.setBounds(10, 11, 180, 32);
		panelEfectos.add(lblTituloEfectos);
		
		JPanel panelPociones = new JPanel();
		panelPociones.setBorder(new LineBorder(Color.WHITE));
		panelPociones.setBackground(Color.BLACK);
		panelPociones.setBounds(660, 367, 200, 107);
		frmPacman.getContentPane().add(panelPociones);
		panelPociones.setLayout(null);
		
		lblPocionBombaCant = new JLabel();
		lblPocionBombaCant.setForeground(new Color(0, 0, 0));
		lblPocionBombaCant.setHorizontalAlignment(SwingConstants.CENTER);
		lblPocionBombaCant.setBackground(Color.WHITE);
		lblPocionBombaCant.setOpaque(true);
		lblPocionBombaCant.setBounds(60, 53, 40, 39);
		lblPocionBombaCant.setVisible(false);
		lblPocionBombaCant.setFont(FontHandler.FUENTE_H3);
		panelPociones.add(lblPocionBombaCant);
		
		JLabel lblTituloPociones = new JLabel("POTIONS");
		lblTituloPociones.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloPociones.setForeground(Color.WHITE);
		lblTituloPociones.setFont(FontHandler.FUENTE_H3);
		lblTituloPociones.setBounds(10, 10, 180, 32);
		panelPociones.add(lblTituloPociones);
		
		aux = ResourceHandler.getBombaCI();
		
		lblPocionBomba = new JLabel();
		lblPocionBomba.setBounds(10, 52, 40, 40);
		lblPocionBomba.setIcon(new ImageIcon(JuegoGUI.class.getResource(aux)));
		lblPocionBomba.setVisible(false);
		panelPociones.add(lblPocionBomba);

		panelMusica = new JPanel();
		panelMusica.setBackground(Color.BLACK);
		panelMusica.setBounds(660, 640, 200, 40);
		frmPacman.getContentPane().add(panelMusica);
		panelMusica.setLayout(null);
		
		btnMusica = new JButton("");
		btnMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				invertirMusica();
				txtrCaptadorDeEventos.grabFocus();
			}
		});
		btnMusica.setBounds(160, 0, 40, 40);
		actualizarIconoBoton();
		panelMusica.add(btnMusica);
		
		lblMusicIcon = new JLabel("");
		lblMusicIcon.setBounds(110, 0, 40, 40);
		lblMusicIcon.setIcon(new ImageIcon(JuegoGUI.class.getResource(PATH_MUSIC)));
		panelMusica.add(lblMusicIcon);
		
		btnEfectos = new JButton("");
		btnEfectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				invertirSFX();
				txtrCaptadorDeEventos.grabFocus();
			}
		});
		btnEfectos.setBounds(60, 0, 40, 40);
		panelMusica.add(btnEfectos);
		
		lblEffectsIcon = new JLabel("");
		lblEffectsIcon.setBounds(0, 0, 40, 40);
		panelMusica.add(lblEffectsIcon);
		
		panelNiveles = new JPanel();
		panelNiveles.setBackground(Color.BLACK);
		panelNiveles.setForeground(Color.WHITE);
		panelNiveles.setBorder(new LineBorder(Color.WHITE));
		panelNiveles.setBounds(660, 485, 200, 147);
		frmPacman.getContentPane().add(panelNiveles);
		panelNiveles.setLayout(null);
		
		lblTituloNiveles = new JLabel("¿Listo?");
		lblTituloNiveles.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloNiveles.setForeground(Color.WHITE);
		lblTituloNiveles.setFont(FontHandler.FUENTE_H4);
		lblTituloNiveles.setBounds(10, 11, 180, 32);
		panelNiveles.add(lblTituloNiveles);
		
		btnNiveles = new JButton("Iniciar");
		btnNiveles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miJuego.iniciarPartida();
				lblTituloNiveles.setVisible(false);
				btnNiveles.setVisible(false);
				panelNiveles.setVisible(false);
				txtrCaptadorDeEventos.grabFocus();
			}
		});
		btnNiveles.setBackground(Color.DARK_GRAY);
		btnNiveles.setForeground(Color.WHITE);
		btnNiveles.setFont(FontHandler.FUENTE_H4);
		btnNiveles.setBounds(10, 54, 180, 82);
		panelNiveles.add(btnNiveles);
		
		if(hayMusica)
			miJuego.iniciarMusica();
		
		miJuego.setSfxHabilitados(haySFX);
		actualizarIconoBotonSFX();
		
		btnNiveles.grabFocus();
		frmPacman.getRootPane().setDefaultButton(btnNiveles);
		
		matrizLabels = new JLabel[ANCHO][ALTO]; //28 x 36
	}
	
	protected void invertirSFX() {
		haySFX = !haySFX;
		miJuego.setSfxHabilitados(haySFX);
		actualizarIconoBotonSFX();
	}
	
	protected void actualizarIconoBotonSFX() {
		if(haySFX) {
			btnEfectos.setIcon(new ImageIcon(JuegoGUI.class.getResource(PATH_PAUSE)));
		} else {
			btnEfectos.setIcon(new ImageIcon(JuegoGUI.class.getResource(PATH_PLAY)));
		}
	}
	
	protected void invertirMusica() {
		//Patron Command!
		if(hayMusica) {
			miJuego.pausarMusica();
		} else {
			miJuego.iniciarMusica();
		}
		
		hayMusica = !hayMusica;
		miJuego.setSfxHabilitados(hayMusica);
		
		actualizarIconoBoton();
	}
	
	protected void actualizarIconoBoton() {
		if(hayMusica) {
			btnMusica.setIcon(new ImageIcon(JuegoGUI.class.getResource(PATH_PAUSE)));
		} else {
			btnMusica.setIcon(new ImageIcon(JuegoGUI.class.getResource(PATH_PLAY)));
		}
	}
	
	public void initializeMatrix() {
		JLabel aux;
		ImageIcon img;
		int qFilas = ANCHO;
		int qColumnas = ALTO;
		
		int columna = 0;
		for(int fila = 0; fila < qFilas; fila++) {
			for(columna = 0; columna < qColumnas; columna++) {
				aux = new JLabel();
				aux.setBounds(PIXELES*fila, PIXELES*columna, PIXELES, PIXELES);
				panelContenedorDeGrilla.add(aux, JLayeredPane.DEFAULT_LAYER);
				matrizLabels[fila][columna] = aux;
			}
			columna = 0;
		}
	}
	
	public void abrir() {
		frmPacman.setVisible(true);
	}
	
	public void agregarLabel(JLabel j) {
		panelContenedorDeGrilla.add(j, JLayeredPane.PALETTE_LAYER);
		panelContenedorDeGrilla.moveToFront(j);
		j.setVisible(true);
	}
	
	public JLabel getLabelEfectoVelocidad() {
		return lblEfectoVelocidad;
	}
	
	public JLabel getLabelEfectoPowerPellet() {
		return lblEfectoPowerPellet;
	}
	
	public JLabel getLabelEfectoBomba() {
		return lblEfectoBomba;
	}
	
	public JLabel getLabelPocionBomba() {
		return lblPocionBomba;
	}
	
	public JLabel getLabelPocionBombaCant() {
		return lblPocionBombaCant;
	}
	
	public void establecerVisible(JLabel j, boolean b) {
		j.setVisible(b);
	}
	
	public void removerLabel(JLabel j) {
		panelContenedorDeGrilla.remove(j);
		panelContenedorDeGrilla.revalidate();
		panelContenedorDeGrilla.repaint();
	}
	
	public void actualizarPiso(Position p, String path) {
		int f = p.getFila();
		int c = p.getColumna();
		
		ImageIcon img = new ImageIcon(JuegoGUI.class.getResource(path));
		
		matrizLabels[f][c].setIcon(img);
	}
	
	public void actualizarPuntaje(String p) {
		puntajeLabel.setText(p);
	}
	
	public void cerrar() {
		frmPacman.setVisible(false);
		frmPacman.dispose(); //Cierra la ventana.
	}
	
	public void mostrarBotonesPerdio() {
		lblTituloNiveles.setText("Nivel fallido");
		btnNiveles.setText("Continuar...");
		btnNiveles.grabFocus();
		
		eliminarTodosActionListeners(btnNiveles);
		btnNiveles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Launcher.lanzarPantallaPerdiste();
			}
		});
		
		panelNiveles.setVisible(true);
		lblTituloNiveles.setVisible(true);
		btnNiveles.setVisible(true);
	}
	
	public void mostrarBotonesGano() {
		lblTituloNiveles.setText("Nivel superado");
		btnNiveles.setText("Continuar...");
		btnNiveles.grabFocus();
		
		eliminarTodosActionListeners(btnNiveles);
		btnNiveles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Launcher.siguienteNivel();
			}
		});
		
		panelNiveles.setVisible(true);
		lblTituloNiveles.setVisible(true);
		btnNiveles.setVisible(true);
	}
	
	private void eliminarTodosActionListeners(JButton b) {
		ActionListener[] aux = b.getActionListeners();
		
		for(ActionListener a : aux)
			b.removeActionListener(a);
	}
}
