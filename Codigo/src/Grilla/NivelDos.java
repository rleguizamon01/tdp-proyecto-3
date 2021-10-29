package Grilla;

import java.util.concurrent.ThreadLocalRandom;

import Entidad.Fantasma;
import Entidad.FantasmaAzul;
import Entidad.FantasmaNaranja;
import Entidad.FantasmaRojo;
import Entidad.FantasmaRosa;
import Entidad.Fruta;
import Entidad.Moneda;
import Entidad.Pacman;
import Entidad.PocionBomba;
import Entidad.PocionVelocidad;
import Entidad.PowerPellet;
import Logica.Juego;
import Utilidad.Position;

public class NivelDos implements EstrategiaNivel {
	private Juego ju;
	private Grilla g;
	private String[] mapa;
	private int contadorMonedas;
	
	private Pacman p;
	
	private Fantasma r;
	private Fantasma s;
	private Fantasma a;
	private Fantasma n;
	
	public NivelDos(Juego j, Grilla g) {
		ju = j;
		this.g = g;
		contadorMonedas = 0;
		mapa = new String[]{"PPPPPPPPPPPPPPPPPPPPPPPPPPPP",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP",
				"PPPPPPAAAAAAAAAAAAAAAAPPPPPP",
				"PPPPAAAMMMMMMMMMMMMMMAAAPPPP",
				"PPAAMMMMAAMAAAAAAMAAMMMMAAPP",
				"PAAMMAAMAAMAAAAAAMAAMAAMMAAP",
				"AAMMAAAMAAMMMAAMMMAAMAAAMMAA",
				"AMMAAPAMAAAAMAAMAAAAMAPAAMMA",
				"AMAAAAAMAAAAMAAMAAAAMAAAAAMA",
				"AMMMMMMMAAMMMMMMMMAAMMMMMMMA",
				"AAAMAAAAAAMAAAAAAMAAAAAAMAAA",
				"AAAMAAAAAAMAAAAAAMAAAAAAMAAA",
				"AMMMMMMMAAMMMAAMMMAAMMMMMMMA",
				"AMAAAAAMAAAAMAAMAAAAMAAAAAMA",
				"AMAAAAAMAAAAMAAMAAAAMAAAAAMA",
				"AMMMMAAMMMMMMMMMMMMMMAAMMMMA",
				"AAAAMAAAAMAAAPPAAAMAAAAMAAAA",
				"AAAAMAAAAMAPPPPPPAMAAAAMAAAA",
				"MMAAMMMMMMAPPPPPPAMMMMMMAAMM",
				"AMAAMAAAAMAPPPPPPAMAAAAMAAMA",
				"AMMMMAAAAMAAAAAAAAMAAAAMMMMA",
				"AMAAMAAMMMMMMMMMMMMMMAAMAAMA",
				"AMAAMAAMAAAAAAAAAAAAMAAMAAMA",
				"AMAAMAAMAAAAAAAAAAAAMAAMAAMA",
				"MMAAMAAMMMMMMAAMMMMMMAAMAAMM",
				"AAAAMAAAAAAAMAAMAAAAAAAMAAAA",
				"AAAAMAAAAAAAMAAMAAAAAAAMAAAA",
				"AMMMMMMMMMMMMMMMMMMMMMMMMMMA",
				"AMAAMAAAAAAAAAAAAAAAAAAMAAMA",
				"AMAAMAPPPPPPPPPPPPPPPPAMAAMA",
				"AMAAMAPPPPPPPPPPPPPPPPAMAAMA",
				"AMAAMAPPPPPPPPPPPPPPPPAMAAMA",
				"AMMMMAPPPPPPPPPPPPPPPPAMMMMA",
				"AAAAAAPPPPPPPPPPPPPPPPAAAAAA",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP"};
		
		
	}

	@Override
	public Pacman getPacman() {
		return p;
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

	@Override
	public void strategyInitialize(Bloque[][] m) {
		Bloque aux = null;
		char c = 0;
		int rand = 0;
		
		//Creamos las entidades
		r = new FantasmaRojo(11, 17, g);
		s = new FantasmaRosa(12, 17, g);
		a = new FantasmaAzul(13, 17, g);
		n = new FantasmaNaranja(14, 17, g);
		p = new Pacman(15, 26, g);
		
		//Mapa
		int qFilas = m.length;
		int qColumnas = m[0].length;
		
		int col = 0;
		for(int fila = 0; fila < qFilas; fila++) {
			for(col = 0; col < qColumnas; col++) {
				c = mapa[col].charAt(fila);
				aux = new Bloque(fila, col, false);
				
				if(c == 'A')
					aux.estadoPared(true);
				
				if(c == 'M') {
					aux.agregarEntidad(new Moneda(fila, col, g));
					contadorMonedas++;
				}
				if(c == 'W')
					aux.agregarEntidad(new PowerPellet(fila, col, g));
				
				if(c == 'F')
					aux.agregarEntidad(new Fruta(fila, col, g));
				
				if(c == 'O') {
					rand = ThreadLocalRandom.current().nextInt(1, 3);
					if(rand % 2 == 0)
						aux.agregarEntidad(new PocionVelocidad(fila, col, g, p.getVelocidad()));
					else
						aux.agregarEntidad(new PocionBomba(fila, col, g));
				}
				
				ju.pedirActualizar(new Position(fila, col), aux.getCaminoImagen());
				
				m[fila][col] = aux;
			}
			col = 0;
		}
		
		//Ponemos a las Entidades en el mapa
		m[11][17].agregarEntidad(r);
		ju.pedirActualizar(r.getPosition(), r.getCaminoImagen());
		
		m[12][17].agregarEntidad(s);
		ju.pedirActualizar(s.getPosition(), s.getCaminoImagen());
		
		m[13][17].agregarEntidad(a);
		ju.pedirActualizar(a.getPosition(), a.getCaminoImagen());
		
		m[14][17].agregarEntidad(n);
		ju.pedirActualizar(n.getPosition(), n.getCaminoImagen());
		
		m[15][26].agregarEntidad(p);
		ju.pedirActualizar(p.getPosition(), p.getCaminoImagen());
		
	}

}
