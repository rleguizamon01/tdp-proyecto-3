package Entidad;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class EntidadGrafica {
	private Entidad e;
	private JLabel j;
	private int w;
	private int h;
	
	public EntidadGrafica(Entidad e) {
		this.e = e;
		
		j = new JLabel();
		j.setIcon(new ImageIcon(EntidadGrafica.class.getResource(e.getCaminoImagen())));
		
		int x = e.getPosicionAbsoluta().getFila();
		int y = e.getPosicionAbsoluta().getColumna();
		w = e.getAncho();
		h = e.getAlto();
		
		j.setBounds(x, y, w, h);
		
		j.setVisible(true);
	}
	
	public JLabel getLabel() {
		return j;
	}
	
	public void desparecer() {
		j.setVisible(false);
	}
	
	public void actualizar(int x, int y) {
		j.setBounds(x, y, w, h);
	}
	
	public void actualizarImagen() {
		j.setIcon(new ImageIcon(EntidadGrafica.class.getResource(e.getCaminoImagen())));
	}
}
