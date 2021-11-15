package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Logica.Juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Datos.DataHandler;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JuegoGUI {
	private static final boolean MUSICA_POR_DEFECTO = true; //Si la musica se vuelve pesada al corregir, cambiar esta constante a false!
	
	//Constantes del tamaño de la ventana:
	private static final int W = 900;
	private static final int H = 730;

	private static final int ALTO = 36; //Cantidad de filas en la grilla.
	private static final int ANCHO = 28;//Cantidad de columnas en la grilla
	private static final int PIXELES = 20;//Tamaño en px de cada celda.
	
	private static boolean TEMPORAL = false;
	
	private JFrame frame;
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
	private Juego miJuego;
	
	private static final String PATH_PLAY = "/RecursosMenu/play.png";
	private static final String PATH_PAUSE = "/RecursosMenu/pause.png";
	private boolean hayMusica;

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
		
		frame = new JFrame();
		frame.setBounds(22, 0, W, H);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(java.awt.Color.BLACK);
		
		txtrCaptadorDeEventos = new JTextArea();
		txtrCaptadorDeEventos.setBounds(0, 0, 1, 1);
		frame.getContentPane().add(txtrCaptadorDeEventos);
		txtrCaptadorDeEventos.grabFocus();
		
		txtrCaptadorDeEventos.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent k) {
				int keyCode = k.getKeyCode();
			    
				switch( keyCode ) {
				    case KeyEvent.VK_UP:
			        	System.out.println("ARRIBA");
			        	miJuego.pedirActualizarDireccion('N');
			        	break;
			        case KeyEvent.VK_DOWN:
			            System.out.println("ABAJO");
			            miJuego.pedirActualizarDireccion('S');
			            break;
			        case KeyEvent.VK_LEFT:
			        	System.out.println("IZQUIERDA");
			        	miJuego.pedirActualizarDireccion('O');
			            break;
			        case KeyEvent.VK_RIGHT:
			        	System.out.println("DERECHA");
			        	miJuego.pedirActualizarDireccion('E');
			            break;
			        case KeyEvent.VK_M:
			        	invertirMusica();
			        	break;
			        case KeyEvent.VK_Q:
			        	if(!TEMPORAL) {
				        	System.out.println("INICIAR");
			        		miJuego.iniciarPartida();
			        	} else {
			        		System.out.println("FINALIZAR");
			        		miJuego.finalizarPartida();
			        	}
			        	
			        	TEMPORAL = !TEMPORAL;
			        	
			            break;
			        case KeyEvent.VK_F:
			        	System.out.println("FANTASMA");
			        	miJuego.pedirMoverRojo();
			        	miJuego.pedirMoverRosa();
			        	miJuego.pedirMoverAzul();
			        	miJuego.pedirMoverNaranja();
			            break;
			        case KeyEvent.VK_SPACE:
			        	System.out.println("ESPACIO");
			        	miJuego.pedirMoverPacman();
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
		frame.getContentPane().add(panelContenedorDeGrilla);
		panelContenedorDeGrilla.setLayout(null);
		
		panelInformacion = new JPanel();
		panelInformacion.setBackground(java.awt.Color.BLACK);
		panelInformacion.setBounds(ANCHO*PIXELES+100, 11, 200, 125);
		panelInformacion.setLayout(null);
		panelInformacion.setBorder(new LineBorder(java.awt.Color.WHITE));
		frame.getContentPane().add(panelInformacion);
				
		puntajeTituloLabel = new JLabel("SCORE");
		puntajeTituloLabel.setForeground(java.awt.Color.WHITE);
		puntajeTituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		puntajeTituloLabel.setBounds(10, 11, 176, 38);
		puntajeTituloLabel.setFont(DataHandler.FUENTE_H3);
		panelInformacion.add(puntajeTituloLabel);
		
		puntajeLabel = new JLabel("0");
		puntajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		puntajeLabel.setFont(DataHandler.FUENTE_H3);
		puntajeLabel.setForeground(java.awt.Color.WHITE);
		puntajeLabel.setBounds(10, 60, 176, 54);
		panelInformacion.add(puntajeLabel);
		
		panelEfectos = new JPanel();
		panelEfectos.setBackground(java.awt.Color.BLACK);
		panelEfectos.setBorder(new LineBorder(java.awt.Color.WHITE));
		panelEfectos.setBounds(660, 147, 200, 200);
		panelEfectos.setLayout(null);
		frame.getContentPane().add(panelEfectos);
		
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
		lblTituloEfectos.setFont(DataHandler.FUENTE_H3);
		lblTituloEfectos.setForeground(java.awt.Color.WHITE);
		lblTituloEfectos.setBounds(10, 11, 180, 32);
		panelEfectos.add(lblTituloEfectos);
		
		panelMusica = new JPanel();
		panelMusica.setBackground(Color.BLACK);
		panelMusica.setBounds(660, 646, 214, 40);
		frame.getContentPane().add(panelMusica);
		panelMusica.setLayout(null);
		
		btnMusica = new JButton("");
		btnMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				invertirMusica();
				txtrCaptadorDeEventos.grabFocus();
			}
		});
		btnMusica.setBounds(174, 0, 40, 40);
		actualizarIconoBoton();
		panelMusica.add(btnMusica);
		
		matrizLabels = new JLabel[ANCHO][ALTO]; //28 x 36
	}
	
	protected void invertirMusica() {
		//Patron Command!
		if(hayMusica) {
			miJuego.iniciarMusica();
		} else {
			miJuego.pausarMusica();
		}
		
		hayMusica = !hayMusica;
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
		frame.setVisible(true);
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
}
