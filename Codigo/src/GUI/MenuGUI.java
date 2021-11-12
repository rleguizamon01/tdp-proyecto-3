package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Datos.DataHandler;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

import Launcher.Launcher;
import ResourceHandler.ResourceHandler;
import ResourceHandler.Skin;
import ResourceHandler.SkinStrategy;
import Utilidad.Par;

public class MenuGUI {
	private static final String PATH_AMONG_US = "/RecursosMenu/amogus.jpg";
	private static final String PATH_CARPINCHO = "/RecursosMenu/capy.png";
	
	private JFrame frame;
	
	private int index;

	/**
	 * Create the application.
	 */
	public MenuGUI() {
		DataHandler.cargarFuentes();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		index = 0;
		
		ArrayList<Par<ImageIcon, SkinStrategy>> pares;
		pares = new ArrayList<Par<ImageIcon, SkinStrategy>>();
		
		pares.add(new Par<ImageIcon, SkinStrategy>(new ImageIcon(MenuGUI.class.getResource(PATH_AMONG_US)), ResourceHandler.SKIN_AMONG_US));
		pares.add(new Par<ImageIcon, SkinStrategy>(new ImageIcon(MenuGUI.class.getResource(PATH_CARPINCHO)), ResourceHandler.SKIN_AMONG_US));
		
		int cantImagenes = pares.size();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(java.awt.Color.BLACK);
		
		JLabel lblTitulo = new JLabel("PACMAN");
		lblTitulo.setFont(DataHandler.FUENTE_H1);
		lblTitulo.setForeground(java.awt.Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 414, 48);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblImagenSkin = new JLabel();
		lblImagenSkin.setIcon(pares.get(index).getPrimeraComponente());
		lblImagenSkin.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenSkin.setBounds(72, 149, 288, 288);
		frame.getContentPane().add(lblImagenSkin);
		
		JButton btnRight = new JButton(")");
		btnRight.setBackground(Color.DARK_GRAY);
		btnRight.setMnemonic('.');
		btnRight.setForeground(Color.WHITE);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index++;
				if(index >= cantImagenes)
					index = 0;
				lblImagenSkin.setIcon(pares.get(index).getPrimeraComponente());
			}
		});
		btnRight.setFont(DataHandler.FUENTE_H4);
		btnRight.setBounds(370, 203, 54, 181);
		frame.getContentPane().add(btnRight);
		
		JButton btnLeft = new JButton("(");
		btnLeft.setMnemonic(',');
		btnLeft.setBackground(Color.DARK_GRAY);
		btnLeft.setForeground(Color.WHITE);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				if(index < 0)
					index = cantImagenes-1;
				lblImagenSkin.setIcon(pares.get(index).getPrimeraComponente());
			}
		});
		btnLeft.setFont(DataHandler.FUENTE_H4);
		btnLeft.setBounds(10, 203, 54, 181);
		frame.getContentPane().add(btnLeft);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose(); //Cierra la ventana.
				Launcher.lanzarJuego(pares.get(index).getSegundaComponente());
			}
		});
		btnJugar.setBackground(Color.DARK_GRAY);
		btnJugar.setMnemonic(',');
		btnJugar.setForeground(Color.WHITE);
		btnJugar.setFont(DataHandler.FUENTE_H2);
		btnJugar.setBounds(72, 448, 288, 77);
		frame.getContentPane().add(btnJugar);
		
		JLabel lblSkin = new JLabel("SELECCIONE SKIN");
		lblSkin.setForeground(java.awt.Color.WHITE);
		lblSkin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSkin.setFont(DataHandler.FUENTE_H3);
		lblSkin.setBounds(10, 90, 414, 48);
		frame.getContentPane().add(lblSkin);
		
		frame.setVisible(true);
	}
}
