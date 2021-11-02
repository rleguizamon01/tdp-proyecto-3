package Grilla;

import Entidad.*;
import Logica.Juego;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;

public abstract class Nivel implements EstrategiaNivel{
	protected Juego ju;
	protected Grilla g;
	protected String[] mapa;
	protected int contadorMonedas;
	
	protected Pacman pacman;
	
	/**protected Fantasma r;
	protected Fantasma s;
	protected Fantasma a;
	protected Fantasma n;**/
	
	public Nivel(Juego j, Grilla g) {
		ju = j;
		this.g = g;
	}
	
	public void strategyInitialize(Bloque[][] m) {
		//Mapa
		int qFilas = m.length;
		int qColumnas = m[0].length;
		char c = 0;
		Bloque aux = null;
		Entidad e = null;
		EntidadGrafica eg = null;
		
		int col = 0;
		for(int fila = 0; fila < qFilas; fila++) {
			for(col = 0; col < qColumnas; col++) {
				c = mapa[col].charAt(fila);
				aux = new Bloque(fila, col, false);
				
				//M de Moneda
				if(c == 'M') {
					e = new Moneda(fila, col, g);
					eg = new EntidadGrafica(e);
					aux.agregarEntidad(e);
					g.agregarLabel(eg);
					contadorMonedas++;
					System.out.println("Moneda en: " + fila + " " + col);
				}
				
				//W de poWer pellet
				if(c == 'W') {
					e = new PowerPellet(fila, col, g);
					eg = new EntidadGrafica(e);
					aux.agregarEntidad(e);
					g.agregarLabel(eg);
					System.out.println("Power Pellet en: " + fila + " " + col);
				}
				
				//F de Fruta
				if(c == 'F') {
					e = new Fruta(fila, col, g);
					eg = new EntidadGrafica(e);
					aux.agregarEntidad(e);
					g.agregarLabel(eg);
					System.out.println("Fruta en: " + fila + " " + col);
				}
				
				
				//B de pocion Bomba
				if(c == 'B') {
					e = new PocionBomba(fila, col, g);
					eg = new EntidadGrafica(e);
					aux.agregarEntidad(e);
					g.agregarLabel(eg);
					System.out.println("Pocion Bomba en: " + fila + " " + col);
				}
				
				//V de pocion Velocidad
				if(c == 'V') {
					e = new PocionVelocidad(fila, col, g);
					eg = new EntidadGrafica(e);
					aux.agregarEntidad(e);
					g.agregarLabel(eg);
					System.out.println("Pocion Velocidad en: " + fila + " " + col);
				}
				
				//A de pAred
				if(c == 'A') {
					aux.estadoPared(true);
					ju.actualizar(new Position(fila, col), ResourceHandler.getParedCI());
				} else {
					ju.actualizar(new Position(fila, col), ResourceHandler.getPisoCI());
				}
				
				m[fila][col] = aux;
			}
			col = 0;
		}
		
		agregarEntidadesMoviles(m); //Patron template.
	}
	
	protected abstract void agregarEntidadesMoviles(Bloque[][] m);

	@Override
	public Pacman getPacman() {
		return pacman;
	}

	/**@Override
	public Fantasma getRojo() {
		return r;
	}

	@Override
	public Fantasma getRosa() {
		return s;
	}

	@Override
	public Fantasma getAzul() {
		return a;
	}

	@Override
	public Fantasma getNaranja() {
		return n;
	}**/
	
	public int getTotalDeMonedasEnNivel() {
		return contadorMonedas;
	}

}
