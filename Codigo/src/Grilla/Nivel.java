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
	
	protected Fantasma r;
	protected Fantasma s;
	protected Fantasma a;
	protected Fantasma n;
	
	Portal portalAnterior;
	
	public Nivel(Juego j, Grilla g) {
		ju = j;
		this.g = g;
		portalAnterior = null;
	}
	
	public void strategyInitialize(Bloque[][] m) {
		//Mapa
		int qFilas = m.length;
		int qColumnas = m[0].length;
		char c = 0;
		Bloque aux = null;
		Entidad e = null;
		boolean hayPisoAbajo = true;
		
		int col = 0;
		for(int fila = 0; fila < qFilas; fila++) {
			for(col = 0; col < qColumnas; col++) {
				c = mapa[col].charAt(fila);
				aux = new Bloque(fila, col, false);
				hayPisoAbajo = true;
				
				switch(c) {
					case '.':
						e = crearMoneda(fila, col);
						aux.agregarEntidad(e);
						break;
					case 'W':
						e = crearPowerPellet(fila, col);
						aux.agregarEntidad(e);
						break;
					case 'F':
						e = crearFruta(fila, col);
						aux.agregarEntidad(e);
						break;
					case 'B':
						e = crearPocionBomba(fila, col);
						aux.agregarEntidad(e);
						break;
					case 'V':
						e = crearPocionVelocidad(fila, col);
						aux.agregarEntidad(e);
						break;
					case '^':
						e = crearPinchos(fila, col);
						aux.agregarEntidad(e);
						break;
					case '0':
						e = crearPortal(fila, col);
						aux.agregarEntidad(e);
						break;
					case 'A':
						crearPared(fila, col, aux);
						hayPisoAbajo = false;
						break;
				}
				
				if(hayPisoAbajo)
					crearPiso(fila, col);
				
				m[fila][col] = aux;
			}
			col = 0;
		}
		
		agregarEntidadesMoviles(m); //Patron template.
		verificarPortales();
	}
	
	protected abstract void agregarEntidadesMoviles(Bloque[][] m);

	@Override
	public Pacman getPacman() {
		return pacman;
	}

	@Override
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
	}
	
	public int getTotalDeMonedasEnNivel() {
		return contadorMonedas;
	}
	
	protected void vincularEntidadGrafica(Entidad e) {
		EntidadGrafica eg = new EntidadGrafica(e);
		e.setEntidadGrafica(eg);
		g.agregarLabel(eg);
	}
	
	protected Entidad crearMoneda(int fila, int col) {
		Moneda m = new Moneda(fila, col, g);
		vincularEntidadGrafica(m);
		contadorMonedas++;
		
		System.out.println("Moneda en: " + fila + " " + col);
		
		return m;
	}
	
	protected Entidad crearPowerPellet(int fila, int col) {
		PowerPellet p = new PowerPellet(fila, col, g);
		vincularEntidadGrafica(p);
		
		System.out.println("Power Pellet en: " + fila + " " + col);
		
		return p;
	}
	
	protected Entidad crearFruta(int fila, int col) {
		Fruta f = new Fruta(fila, col, g);
		vincularEntidadGrafica(f);
		

		System.out.println("Fruta en: " + fila + " " + col);
		
		return f;
	}
	
	protected Entidad crearPocionBomba(int fila, int col) {
		PocionBomba pb = new PocionBomba(fila, col, g);
		vincularEntidadGrafica(pb);
		
		System.out.println("Pocion Bomba en: " + fila + " " + col);
		
		return pb;
	}
	
	protected Entidad crearPocionVelocidad(int fila, int col) {
		PocionVelocidad pv = new PocionVelocidad(fila, col, g);
		vincularEntidadGrafica(pv);
		
		System.out.println("Pocion Velocidad en: " + fila + " " + col);
		
		return pv;
	}
	
	protected Entidad crearPinchos(int fila, int col) {
		Pinchos p = new Pinchos(fila, col, g);
		vincularEntidadGrafica(p);
		
		System.out.println("Pinchos en: " + fila + " " + col);
		
		return p;
	}
	
	protected Entidad crearPortal(int fila, int col) {
		Portal p = new Portal(fila, col, g);
		
		if(portalAnterior == null) {
			portalAnterior = p;
		}else {
			portalAnterior.setPortalDestino(p);
			p.setPortalDestino(portalAnterior);
			portalAnterior = null;
		}
		
		vincularEntidadGrafica(p);
		
		return p;
	}
	
	protected void crearPared(int fila, int col, Bloque b) {
		b.estadoPared(true);
		ju.actualizar(new Position(fila, col), ResourceHandler.getParedCI());
	}
	
	protected void crearPiso(int fila, int col) {
		ju.actualizar(new Position(fila, col), ResourceHandler.getPisoCI());
	}

	protected void verificarPortales() {
		if(portalAnterior != null) { //Se da solo si tenemos una cantidad impar de portales
			g.removerEntidad(portalAnterior); //Ya no tenemos una cantidad impar de portales :)
		}
	}
	
}
