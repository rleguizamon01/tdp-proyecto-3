package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Entidad.Entidad;
import Logica.Juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ResourceHandler.ResourceHandler;
import Utilidad.Position;
import javax.swing.JLayeredPane;

public class JuegoGUI {
	//Constantes del tamaño de la ventana:
	private static final int W = 1330;
	private static final int H = 730;

	private static final int ALTO = 36; //Cantidad de filas en la grilla.
	private static final int ANCHO = 28;//Cantidad de columnas en la grilla
	private static final int PIXELES = 20;//Tamaño en px de cada celda.
	
	private JFrame frame;
	private JLayeredPane panelContenedorDeGrilla; //Seran el piso y las paredes del laberinto
	private JLabel[][] matrizLabels;
	private JTextArea txtrCaptadorDeEventos;
	private JPanel panelInformacion;
	private JLabel puntajeTituloLabel;
	private JLabel puntajeLabel;
	private Juego miJuego;
	
	/**
	 * Create the application.
	 */
	/**public JuegoGUI() {
		initialize();
	}**/

	public void setJuego(Juego j) {
		miJuego = j;
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(22, 0, W, H);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
			        case KeyEvent.VK_Q:
			        	System.out.println("ESQUINAS");
			        	miJuego.pedirMostrarEntidades();
			            break;
			        case KeyEvent.VK_F:
			        	System.out.println("FANTASMA");
			        	miJuego.pedirMoverRojo();
			            break;
			        case KeyEvent.VK_SPACE:
			        	System.out.println("ESPACIO");
			        	miJuego.pedirMoverPacman();
			        	break;
			    }
			    
			    //txtrCaptadorDeEventos.grabFocus();
			}

			@Override
			public void keyReleased(KeyEvent k) {
			}

			@Override
			public void keyTyped(KeyEvent k) {
			}
		});
		
		panelContenedorDeGrilla = new JLayeredPane();
		panelContenedorDeGrilla.setBounds(10, 11, ANCHO*PIXELES, ALTO*PIXELES);
		panelContenedorDeGrilla.setBorder(new LineBorder(java.awt.Color.BLACK));
		frame.getContentPane().add(panelContenedorDeGrilla);
		panelContenedorDeGrilla.setLayout(null);
		
		panelInformacion = new JPanel();
		panelInformacion.setBounds(ANCHO*PIXELES+100, 11, 200, 125);
		panelInformacion.setLayout(null);
		panelInformacion.setBorder(new LineBorder(java.awt.Color.BLACK));
		frame.getContentPane().add(panelInformacion);
				
		puntajeTituloLabel = new JLabel("SCORE");
		puntajeTituloLabel.setBounds(72, 10, 56, 22);
		puntajeTituloLabel.setVerticalAlignment(SwingConstants.TOP);
		puntajeTituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelInformacion.add(puntajeTituloLabel);
		
		puntajeLabel = new JLabel("0");
		puntajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		puntajeLabel.setFont(new Font("Tahoma", Font.PLAIN, 38));
		puntajeLabel.setBounds(10, 52, 176, 69);
		panelInformacion.add(puntajeLabel);
		
		matrizLabels = new JLabel[ANCHO][ALTO]; //28 x 36
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
	
	public void removerLabel(JLabel j) {
		panelContenedorDeGrilla.remove(j);
		panelContenedorDeGrilla.revalidate();
		panelContenedorDeGrilla.repaint();
	}
	
	/**public void agregar(Entidad e) {
		JLabel j = new JLabel();
		
		int x = e.getPosicionAbsoluta().getFila();
		int y = e.getPosicionAbsoluta().getColumna();
		int w = e.getAncho();
		int h = e.getAlto();
		
		j.setBounds(x, y, w, h);
		
		ImageIcon img = new ImageIcon(JuegoGUI.class.getResource(e.getCaminoImagen()));
		
		j.setIcon(img);
		j.setVisible(true);
		
		panelContenedorDeGrilla.add(j);
		panelContenedorDeGrilla.moveToFront(j);
	}**/
	
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
