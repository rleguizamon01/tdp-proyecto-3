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
import java.awt.Toolkit;

import Launcher.Launcher;
import ResourceHandler.ResourceHandler;
import ResourceHandler.SkinStrategy;
import Utilidad.Par;

public class MenuGUI {
	private static final String PATH_AMONG_US = "/RecursosMenu/amogus.jpg";
	private static final String PATH_CARPINCHO = "/RecursosMenu/capy.png";
	
	private JFrame frmMenu;
	
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
		pares.add(new Par<ImageIcon, SkinStrategy>(new ImageIcon(MenuGUI.class.getResource(PATH_CARPINCHO)), ResourceHandler.SKIN_CARPINCHO));
		
		int cantImagenes = pares.size();
		
		frmMenu = new JFrame();
		frmMenu.setTitle("MENU");
		frmMenu.setResizable(false);
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(JuegoGUI.class.getResource("/RecursosMenu/mati.png")));
		frmMenu.setBounds(100, 100, 450, 610);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		frmMenu.getContentPane().setBackground(java.awt.Color.BLACK);
		
		JLabel lblTitulo = new JLabel("PACMAN");
		lblTitulo.setFont(DataHandler.FUENTE_H1);
		lblTitulo.setForeground(java.awt.Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 414, 48);
		frmMenu.getContentPane().add(lblTitulo);
		
		JLabel lblImagenSkin = new JLabel();
		lblImagenSkin.setIcon(pares.get(index).getPrimeraComponente());
		lblImagenSkin.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenSkin.setBounds(72, 149, 288, 288);
		frmMenu.getContentPane().add(lblImagenSkin);
		
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
		frmMenu.getContentPane().add(btnRight);
		
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
		frmMenu.getContentPane().add(btnLeft);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.setVisible(false);
				frmMenu.dispose(); //Cierra la ventana.
				Launcher.lanzarJuego(pares.get(index).getSegundaComponente());
			}
		});
		btnJugar.setBackground(Color.DARK_GRAY);
		btnJugar.setMnemonic(',');
		btnJugar.setForeground(Color.WHITE);
		btnJugar.setFont(DataHandler.FUENTE_H2);
		btnJugar.setBounds(72, 448, 288, 77);
		frmMenu.getContentPane().add(btnJugar);
		
		JLabel lblSkin = new JLabel("SELECCIONE SKIN");
		lblSkin.setForeground(java.awt.Color.WHITE);
		lblSkin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSkin.setFont(DataHandler.FUENTE_H3);
		lblSkin.setBounds(10, 90, 414, 48);
		frmMenu.getContentPane().add(lblSkin);
		
		frmMenu.setVisible(true);
		frmMenu.getRootPane().setDefaultButton(btnJugar);
	}
}
