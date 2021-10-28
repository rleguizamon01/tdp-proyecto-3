package Grilla;

import java.util.concurrent.ThreadLocalRandom;

import Entidad.*;
import Logica.Juego;
import Utilidad.Position;

public class NivelUno implements EstrategiaNivel {
	private Juego ju;
	private Grilla g;
	private String[] mapa;
	
	private Pacman p;
	
	private Fantasma r;
	private Fantasma s;
	private Fantasma a;
	private Fantasma n;
	
	public NivelUno(Juego j, Grilla g) {
		this.ju = j;
		this.g = g;
		
		mapa = new String[]{
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP",
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
				"AMMMMMMMMMMMMAAMMMMMMMMMMMMA",
				"AMAAAAMAAAAAMAAMAAAAAMAAAAMA",
				"AWAAAAMAAAAAMAAMAAAAAMAAAAWA",
				"AMAAAAMAAAAAMAAMAAAAAMAAAAMA",
				"AMMMMMMMMMMMMMMMMMMMMMMMMMMA",
				"AMAAAAMAAMAAAAAAAAMAAMAAAAMA",
				"AMAAAAMAAMAAAAAAAAMAAMAAAAMA",
				"AMMMMMMAAMMMMAAMMMMAAMMMMMMA",
				"AAAAAAMAAAAAPAAPAAAAAMAAAAAA",
				"PPPPPAMAAAAAPAAPAAAAAMAPPPPP",
				"PPPPPAMAAPPPPPPPPPPAAMAPPPPP",
				"PPPPPAMAAPAAAPPAAAPAAMAPPPPP",
				"AAAAAAMAAPAPPPPPPAPAAMAAAAAA",
				"PPPPPPMPPPAPPPPPPAPPPMPPPPPP",
				"AAAAAAMAAPAPPPPPPAPAAMAAAAAA",
				"PPPPPAMAAPAAAAAAAAPAAMAPPPPP",
				"PPPPPAMAAPPPPFPPPPPAAMAPPPPP",
				"PPPPPAMAAPAAAAAAAAPAAMAPPPPP",
				"AAAAAAMAAPAAAAAAAAPAAMAAAAAA",
				"AMMMMMMMMMMMMAAMMMMMMMMMMMMA",
				"AMAAAAMAAAAAMAAMAAAAAMAAAAMA",
				"AMAAAAMAAAAAMAAMAAAAAMAAAAMA",
				"AWMMAAMMMMMMMPPMMMMMMMAAMMWA",
				"AAAMAAMAAMAAAAAAAAMAAMAAMAAA",
				"AAAMAAMAAMAAAAAAAAMAAMAAMAAA",
				"AMMMMMMAAMMMMAAMMMMAAMMMMMMA",
				"AMAAAAAAAAAAMAAMAAAAAAAAAAMA",
				"AMAAAAAAAAAAMAAMAAAAAAAAAAMA",
				"AMMMMMMMMMMMMOMMMMMMMMMMMMMA",
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP"
				};
	}
	
	@Override
	public void strategyInitialize(Bloque[][] m) {
		Bloque aux = null;
		char c = 0;
		int rand = 0;
		
		//System.out.println(m.length + " " + m[0].length);
		
		//Creamos las entidades
		r = new FantasmaRojo(11, 17, g);
		s = new FantasmaRosa(12, 17, g);
		a = new FantasmaAzul(13, 17, g);
		n = new FantasmaNaranja(14, 17, g);
		p = new Pacman(14, 26, g);
		
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
				
				if(c == 'M')
					aux.agregarEntidad(new Moneda(fila, col, g));
				
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
		ju.pedirActualizar(new Position(11, 17), r.getCaminoImagen());
		
		m[12][17].agregarEntidad(s);
		ju.pedirActualizar(new Position(12, 17), s.getCaminoImagen());
		
		m[13][17].agregarEntidad(a);
		ju.pedirActualizar(new Position(13, 17), a.getCaminoImagen());
		
		m[14][17].agregarEntidad(n);
		ju.pedirActualizar(new Position(14, 17), n.getCaminoImagen());
		
		m[14][26].agregarEntidad(p);
		ju.pedirActualizar(new Position(14, 26), p.getCaminoImagen());
	}

	@Override
	public Pacman getPacman() {
		return p;
	}

	@Override
	public Fantasma getRojo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fantasma getRosa() {
		return r;
	}

	@Override
	public Fantasma getAzul() {
		return a;
	}

	@Override
	public Fantasma getNaranja() {
		return n;
	}

}
