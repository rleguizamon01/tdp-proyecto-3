package GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Datos.DataHandler;
import Highscore.Highscores;
import Highscore.Jugador;

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
	
	private JFrame frame;
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
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelInformacion = new JPanel();
		panelInformacion.setBackground(Color.BLACK);
		panelInformacion.setBounds(10, 111, 400, 539);
		frame.getContentPane().add(panelInformacion);
		panelInformacion.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBorder(new LineBorder(Color.WHITE));
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setFont(DataHandler.FUENTE_H3);
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
		lblPuntajeFue.setFont(DataHandler.FUENTE_H3);
		lblPuntajeFue.setBounds(10, 11, 380, 82);
		panelInformacion.add(lblPuntajeFue);
		
		lblPuntaje = new JLabel(jugador.getPuntaje() + "");
		lblPuntaje.setBorder(new LineBorder(Color.WHITE));
		lblPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntaje.setForeground(Color.WHITE);
		lblPuntaje.setFont(DataHandler.FUENTE_H3);
		lblPuntaje.setBounds(10, 104, 380, 52);
		panelInformacion.add(lblPuntaje);
		
		lblIngresaNombre = new JLabel("Tu nombre");
		lblIngresaNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresaNombre.setForeground(Color.WHITE);
		lblIngresaNombre.setFont(DataHandler.FUENTE_H3);
		lblIngresaNombre.setBounds(10, 161, 380, 82);
		panelInformacion.add(lblIngresaNombre);
		
		btnCargar = new JButton("Cargar mi puntaje");
		btnCargar.setBounds(10, 302, 380, 37);
		btnCargar.setBackground(Color.DARK_GRAY);
		btnCargar.setForeground(Color.WHITE);
		btnCargar.setFont(DataHandler.FUENTE_H4);
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarHighscore();
			}
		});
		panelInformacion.add(btnCargar);		
		
		panelTitulos = new JPanel();
		panelTitulos.setBackground(Color.BLACK);
		panelTitulos.setBounds(10, 11, 864, 89);
		frame.getContentPane().add(panelTitulos);
		panelTitulos.setLayout(null);
		
		lblTitulo = new JLabel(titulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(DataHandler.FUENTE_H1);
		lblTitulo.setBounds(10, 11, 844, 67);
		panelTitulos.add(lblTitulo);
		
		panelHighscores = new JPanel();
		panelHighscores.setBackground(Color.BLACK);
		panelHighscores.setBounds(420, 111, 454, 539);
		frame.getContentPane().add(panelHighscores);
		panelHighscores.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setFont(DataHandler.FUENTE_H4);
		textArea.setBounds(10, 104, 434, 424);
		panelHighscores.add(textArea);
		
		lblTituloHighscores = new JLabel("HIGHSCORES");
		lblTituloHighscores.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloHighscores.setForeground(Color.WHITE);
		lblTituloHighscores.setFont(DataHandler.FUENTE_H2);
		lblTituloHighscores.setBounds(10, 11, 434, 82);
		panelHighscores.add(lblTituloHighscores);
		
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
		JOptionPane.showMessageDialog(frame, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void mostrarHighscores() {
		String scores = hs.toString();
		textArea.setText(scores);
	}
}
