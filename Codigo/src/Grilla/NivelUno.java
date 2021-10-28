package Grilla;

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
				"AMAAAAMAAAAAMAAMAAAAAMAAAAMA",
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
				"PPPPPAMAAPPPPPPPPPPAAMAPPPPP",
				"PPPPPAMAAPAAAAAAAAPAAMAPPPPP",
				"AAAAAAMAAPAAAAAAAAPAAMAAAAAA",
				"AMMMMMMMMMMMMAAMMMMMMMMMMMMA",
				"AMAAAAMAAAAAMAAMAAAAAMAAAAMA",
				"AMAAAAMAAAAAMAAMAAAAAMAAAAMA",
				"AMMMAAMMMMMMMPPMMMMMMMAAMMMA",
				"AAAMAAMAAMAAAAAAAAMAAMAAMAAA",
				"AAAMAAMAAMAAAAAAAAMAAMAAMAAA",
				"AMMMMMMAAMMMMAAMMMMAAMMMMMMA",
				"AMAAAAAAAAAAMAAMAAAAAAAAAAMA",
				"AMAAAAAAAAAAMAAMAAAAAAAAAAMA",
				"AMMMMMMMMMMMMMMMMMMMMMMMMMMA",
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP",
				"PPPPPPPPPPPPPPPPPPPPPPPPPPPP"
				};
	}
	
	@Override
	public void strategyInitialize(Bloque[][] m) {
		Bloque aux = null;
		char c = 0;
		
		System.out.println(m.length + " " + m[0].length);
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
				
				ju.pedirActualizar(new Position(fila, col), aux.getCaminoImagen());
				
				m[fila][col] = aux;
			}
			col = 0;
		}
		
		//Entidades
		r = new FantasmaRojo(11, 17, g);
		m[11][17].agregarEntidad(r);
		ju.pedirActualizar(new Position(11, 17), r.getCaminoImagen());
		
		s = new FantasmaRosa(12, 17, g);
		m[12][17].agregarEntidad(s);
		ju.pedirActualizar(new Position(12, 17), s.getCaminoImagen());
		
		a = new FantasmaAzul(13, 17, g);
		m[13][17].agregarEntidad(a);
		ju.pedirActualizar(new Position(13, 17), a.getCaminoImagen());
		
		n = new FantasmaNaranja(14, 17, g);
		m[14][17].agregarEntidad(n);
		ju.pedirActualizar(new Position(14, 17), n.getCaminoImagen());
		
		p = new Pacman(14, 26, g);
		m[14][26].agregarEntidad(p);
		System.out.println(p.getCaminoImagen());
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
