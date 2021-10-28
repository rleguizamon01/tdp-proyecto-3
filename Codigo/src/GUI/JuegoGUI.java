package GUI;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class JuegoGUI extends JFrame {
	//Constantes del tamaño de la ventana:
	private static final int W = 1330;
	private static final int H = 730;
	
	//Constantes del tamaño de la grilla.
	private static final int ALTO = 36; //Cantidad de filas en la grilla.
	private static final int ANCHO = 28;//Cantidad de columnas en la grilla
	private static final int PIXELES = 20;//Tamaño en px de cada celda.
	
	private JPanel contentPane;
	private JPanel grilla;
	private JPanel informacion;
	
	private JLabel[][] matrizLabels;
	
	private JTextArea txtrCaptadorDeEventos;

	/**
	 * Create the frame.
	 */
	public JuegoGUI() {
		//Propiedades propias de la ventana.
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(0, 0, W, H));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//Content pane
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//Panel contenedor de la grilla.
		grilla = new JPanel();
		grilla.setBounds(10, 11, ANCHO*PIXELES, ALTO*PIXELES);
		grilla.setBorder(new LineBorder(java.awt.Color.BLACK));
		grilla.setBackground(java.awt.Color.BLACK);
		grilla.setLayout(null);
		contentPane.add(grilla);
		
		//Creacion de la grilla como tal.
		matrizLabels = new JLabel[ANCHO][ALTO]; //28 x 36
		
		System.out.println("GUI: " + matrizLabels.length + " " + matrizLabels[0].length);
		System.out.println("GUI: " + ANCHO + " " + ALTO);
		
		JLabel aux;
		ImageIcon img;
		
		int qFilas = ANCHO;
		int qColumnas = ALTO;
		
		int columna = 0;
		for(int fila = 0; fila < qFilas; fila++) {
			for(columna = 0; columna < qColumnas; columna++) {
				aux = new JLabel();
				aux.setBounds(PIXELES*fila, PIXELES*columna, PIXELES, PIXELES);
				img = new ImageIcon(JuegoGUI.class.getResource("/RecursosCompartidos/vacio.png"));
				aux.setIcon(img);

				grilla.add(aux);
				System.out.println("(" + fila + ", " + columna + ")");
				matrizLabels[fila][columna]= aux;
			}
			columna = 0;
		}
		
		//Panel contenedor de la informacion: puntaje, etc.
		informacion = new JPanel();
		informacion.setBounds(ANCHO*PIXELES+100, 11, W-(ANCHO*PIXELES+100), ALTO*PIXELES);
		informacion.setBorder(new LineBorder(java.awt.Color.BLACK));
		contentPane.add(informacion);
		
		txtrCaptadorDeEventos = new JTextArea();
		txtrCaptadorDeEventos.setOpaque(false);
		txtrCaptadorDeEventos.setBounds(1, 1, 1, 1);
		
		txtrCaptadorDeEventos.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent k) {
				int keyCode = k.getKeyCode();
			    
				switch( keyCode ) {
				    case KeyEvent.VK_UP:
			        	System.out.println("ARRIBA");
			        	break;
			        case KeyEvent.VK_DOWN:
			            System.out.println("ABAJO");
			            break;
			        case KeyEvent.VK_LEFT:
			        	System.out.println("IZQUIERDA");
			            break;
			        case KeyEvent.VK_RIGHT:
			        	System.out.println("DERECHA");
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
		
		informacion.add(txtrCaptadorDeEventos);
		
		//ULTIMAS INSTRUCCIONES SI O SI
		txtrCaptadorDeEventos.grabFocus();
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void actualizar(Position position, String caminoImagen) {
		ImageIcon img = new ImageIcon(JuegoGUI.class.getResource(caminoImagen));
		int f = position.getFila();
		int c = position.getColumna();
		matrizLabels[f][c].setIcon(img);
		
	}
}
