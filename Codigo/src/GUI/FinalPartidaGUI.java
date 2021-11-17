package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Datos.DataHandler;
import Datos.FontHandler;
import Highscore.Highscores;
import Highscore.Jugador;
import Launcher.Launcher;

import javax.swing.JTextPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class FinalPartidaGUI {
	public static final Color COLOR_GANAR = Color.GREEN;
	public static final Color COLOR_PERDER = Color.RED;
	
	private String titulo;
	private Jugador jugador;
	private Highscores hs;
	private boolean seCargoJugador;
	private int longitudMaxima;
	private int longitudMinima;
	private Color colorTitulo;
	
	private JFrame frmFinal;
	private JTextArea textArea;
	private JPanel panelInformacion;
	private JLabel lblPuntaje;
	private JPanel panelTitulos;
	private JLabel lblTitulo;
	private JLabel lblIngresaNombre;
	private JButton btnCargar;
	private JTextPane textPane;
	private JLabel lblPuntajeFue;
	private JPanel panelHighscores;
	private JLabel lblTituloHighscores;
	private JButton btnJugarDeNuevo;
	
	/**
	 * Create the application.
	 */
	public FinalPartidaGUI(String t, Jugador j, Color c) {
		titulo = t;
		jugador = j;
		seCargoJugador = false;
		colorTitulo = c;
		
		hs = DataHandler.getHighscore();
		
		longitudMaxima = 25;
		longitudMinima = 3;
		
		initialize();
		frmFinal.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinal = new JFrame();
		frmFinal.setTitle("FINAL");
		frmFinal.setIconImage(Toolkit.getDefaultToolkit().getImage(JuegoGUI.class.getResource("/RecursosMenu/mati.png")));
		frmFinal.getContentPane().setBackground(Color.BLACK);
		frmFinal.getContentPane().setLayout(null);
		frmFinal.setBounds(100, 100, 900, 700);
		frmFinal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelInformacion = new JPanel();
		panelInformacion.setBackground(Color.BLACK);
		panelInformacion.setBounds(10, 111, 400, 539);
		frmFinal.getContentPane().add(panelInformacion);
		panelInformacion.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBorder(new LineBorder(Color.WHITE));
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setFont(FontHandler.FUENTE_H3);
		textPane.setBounds(10, 254, 380, 37);
		
		//Para centrar el texto:
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		//Codigo tomado de stackoverflow
		
		panelInformacion.add(textPane);
		
		lblPuntajeFue = new JLabel("Tu puntaje fue");
		lblPuntajeFue.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntajeFue.setForeground(Color.WHITE);
		lblPuntajeFue.setFont(FontHandler.FUENTE_H3);
		lblPuntajeFue.setBounds(10, 11, 380, 82);
		panelInformacion.add(lblPuntajeFue);
		
		lblPuntaje = new JLabel(jugador.getPuntaje() + "");
		lblPuntaje.setBorder(new LineBorder(Color.WHITE));
		lblPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntaje.setForeground(Color.WHITE);
		lblPuntaje.setFont(FontHandler.FUENTE_H3);
		lblPuntaje.setBounds(10, 104, 380, 52);
		panelInformacion.add(lblPuntaje);
		
		lblIngresaNombre = new JLabel("Tu nombre");
		lblIngresaNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresaNombre.setForeground(Color.WHITE);
		lblIngresaNombre.setFont(FontHandler.FUENTE_H3);
		lblIngresaNombre.setBounds(10, 161, 380, 82);
		panelInformacion.add(lblIngresaNombre);
		
		btnCargar = new JButton("Cargar mi puntaje");
		btnCargar.setBounds(10, 302, 380, 37);
		btnCargar.setBackground(Color.DARK_GRAY);
		btnCargar.setForeground(Color.WHITE);
		btnCargar.setFont(FontHandler.FUENTE_H4);
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarHighscore();
			}
		});
		panelInformacion.add(btnCargar);		
		
		panelTitulos = new JPanel();
		panelTitulos.setBackground(Color.BLACK);
		panelTitulos.setBounds(10, 11, 864, 89);
		frmFinal.getContentPane().add(panelTitulos);
		panelTitulos.setLayout(null);
		
		lblTitulo = new JLabel(titulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(colorTitulo);
		lblTitulo.setFont(FontHandler.FUENTE_H1);
		lblTitulo.setBounds(10, 11, 844, 67);
		panelTitulos.add(lblTitulo);
		
		panelHighscores = new JPanel();
		panelHighscores.setBackground(Color.BLACK);
		panelHighscores.setBounds(420, 111, 454, 539);
		frmFinal.getContentPane().add(panelHighscores);
		panelHighscores.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setFont(FontHandler.FUENTE_H4);
		textArea.setBounds(10, 104, 434, 380);
		panelHighscores.add(textArea);
		
		lblTituloHighscores = new JLabel("HIGHSCORES");
		lblTituloHighscores.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloHighscores.setForeground(Color.WHITE);
		lblTituloHighscores.setFont(FontHandler.FUENTE_H2);
		lblTituloHighscores.setBounds(10, 11, 434, 82);
		panelHighscores.add(lblTituloHighscores);
		
		JButton btnReiniciar = new JButton("Borrar todos los puntajes");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataHandler.reiniciarHighscores();
				hs = DataHandler.getHighscore();
				mostrarHighscores();
			}
		});
		btnReiniciar.setForeground(Color.WHITE);
		btnReiniciar.setFont(FontHandler.FUENTE_H4);
		btnReiniciar.setBackground(Color.DARK_GRAY);
		btnReiniciar.setBounds(10, 491, 434, 37);
		panelHighscores.add(btnReiniciar);
		
		frmFinal.getRootPane().setDefaultButton(btnCargar);
		
		btnJugarDeNuevo = new JButton("Jugar de nuevo");
		btnJugarDeNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
				Launcher.relanzarJuego();
			}
		});
		btnJugarDeNuevo.setForeground(Color.WHITE);
		btnJugarDeNuevo.setFont(FontHandler.FUENTE_H3);
		btnJugarDeNuevo.setBackground(Color.DARK_GRAY);
		btnJugarDeNuevo.setBounds(10, 446, 380, 82);
		panelInformacion.add(btnJugarDeNuevo);
		
		
		mostrarHighscores();
	}
	
	private void cargarHighscore() {
		if(!seCargoJugador) {
			String nombre = textPane.getText();
			
			if(nombre.length() > longitudMaxima || nombre.length() < longitudMinima) {
				mostrarPopUpInformacion("Un nombre debe estar comprendido por entre 3 y 25 caracteres");
			} else {
				jugador.setNombre(nombre);
				hs.agregarJugador(jugador);
				DataHandler.guardar(hs, Highscore.Highscores.SCORE_PATH);
				seCargoJugador = true;	
				textArea.setText("");
				mostrarHighscores();
			}
		} else {
			mostrarPopUpInformacion("Su puntaje ya ha sido cargado.");
		}
	}
	
	private void mostrarPopUpInformacion(String mensaje) {
		JOptionPane.showMessageDialog(frmFinal, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void mostrarHighscores() {
		String scores = hs.toString();
		textArea.setText(scores);
	}
	
	private void cerrar() {
		frmFinal.setVisible(false);
		frmFinal.dispose(); //Cierra la ventana.
	}
}
