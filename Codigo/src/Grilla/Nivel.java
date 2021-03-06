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
	protected int milisegundosEnScatter;
	
	protected Pacman pacman;
	
	protected Fantasma r;
	protected Fantasma s;
	protected Fantasma a;
	protected Fantasma n;
	
	Portal portalAnterior1;
	Portal portalAnterior2;
	
	public Nivel(Juego j, Grilla g) {
		ju = j;
		this.g = g;
		portalAnterior1 = null;
		portalAnterior2 = null;
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
						e = crearPortal(fila, col, 1);
						aux.agregarEntidad(e);
						break;
					case 'O':
						e = crearPortal(fila, col, 2);
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
	
	public int getMilisegundosEnScatter() {
		return milisegundosEnScatter;
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
		
		return m;
	}
	
	protected Entidad crearPowerPellet(int fila, int col) {
		PowerPellet p = new PowerPellet(fila, col, g);
		vincularEntidadGrafica(p);
		
		return p;
	}
	
	protected Entidad crearFruta(int fila, int col) {
		Fruta f = new Fruta(fila, col, g);
		vincularEntidadGrafica(f);
		
		return f;
	}
	
	protected Entidad crearPocionBomba(int fila, int col) {
		PocionBomba pb = new PocionBomba(fila, col, g);
		vincularEntidadGrafica(pb);
		
		return pb;
	}
	
	protected Entidad crearPocionVelocidad(int fila, int col) {
		PocionVelocidad pv = new PocionVelocidad(fila, col, g);
		vincularEntidadGrafica(pv);
		
		return pv;
	}
	
	protected Entidad crearPinchos(int fila, int col) {
		Pinchos p = new Pinchos(fila, col, g);
		vincularEntidadGrafica(p);
		
		return p;
	}
	
	protected Entidad crearPortal(int fila, int col,int num) {
		Portal p = new Portal(fila, col, g);
		
		if(num==1) {
			if(portalAnterior1 == null) {
				portalAnterior1 = p;
			}else {
				portalAnterior1.setPortalDestino(p);
				p.setPortalDestino(portalAnterior1);
				portalAnterior1 = null;
			}
		}else {
			if(portalAnterior2 == null) {
				portalAnterior2 = p;
			}else {
				portalAnterior2.setPortalDestino(p);
				p.setPortalDestino(portalAnterior2);
				portalAnterior2 = null;
			}
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
		if(portalAnterior1 != null ) { //Se da solo si tenemos una cantidad impar de portales
			g.removerEntidad(portalAnterior1); //Ya no tenemos una cantidad impar de portales :)
		}
		if(portalAnterior2 != null ) { //Se da solo si tenemos una cantidad impar de portales
			g.removerEntidad(portalAnterior2); //Ya no tenemos una cantidad impar de portales :)
		}
	}
	
}
