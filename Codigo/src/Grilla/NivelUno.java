package Grilla;


import Entidad.*;
import Logica.Juego;

public class NivelUno extends Nivel{
	/*
	 * Definimos ciclo scatter como el tiempo que tarda el fantasma mas lento del nivel
	 * en llegar al punto en el cual "da vueltas" y dar una vuelta completa. 
	 */
	public NivelUno(Juego j, Grilla g) {
		super(j, g);
		milisegundosEnScatter = 22000; //22 segundos son 2 ciclos Scatter.
		mapa = new String[]{
				"                            ",
				"                            ",
				"                            ",
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
				"A..........B.AA.V..........A",
				"A.AAAA.AAAAA.AA.AAAAA.AAAA.A",
				"AWAAAA.AAAAA.AA.AAAAA.AAAAWA",
				"A.AAAA.AAAAA.AA.AAAAA.AAAA.A",
				"A..........................A",
				"A AAAA.AA AAAAAAAA AA.AAAA A",
				"A AAAA.AA AAAAAAAA AA.AAAA A",
				"A     .AA    AA    AA.     A",
				"AAAAAA.AAAAA AA AAAAA.AAAAAA",
				"     A.AAAAA AA AAAAA.A     ",
				"     A.AA          AA.A     ",
				"     A.AA AAA^^AAA AA.A     ",
				"AAAAAA.AA A      A AA.AAAAAA",
				"0     .   A      A   .     0",
				"AAAAAA.AA A      A AA.AAAAAA",
				"     A.AA AAAAAAAA AA.A     ",
				"     A.AA    F     AA.A     ",
				"     A.AA AAAAAAAA AA.A     ",
				"AAAAAA.AA AAAAAAAA AA.AAAAAA",
				"A            AA            A",
				"A AAAA AAAAA AA AAAAA AAAA A",
				"A AAAA AAAAA AA AAAAA AAAA A",
				"AW..AA                AA..WA",
				"AAA.AA AA AAAAAAAA AA AA.AAA",
				"AAA.AA AA AAAAAAAA AA AA.AAA",
				"A...   AA....AA....AA   ...A",
				"A.AAAAAAAAAA.AA.AAAAAAAAAA.A",
				"A.AAAAAAAAAA.AA.AAAAAAAAAA.A",
				"A..........................A",
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
				"                            ",
				"                            "
				};
	}
	
	protected void agregarEntidadesMoviles(Bloque[][] m) {
		//Pacman:
		pacman = new Pacman(14, 26, g);
		EntidadGrafica egPacman = new EntidadGrafica(pacman);
		pacman.setEntidadGrafica(egPacman);
		m[14][26].agregarEntidad(pacman);
		g.agregarLabel(egPacman);
		
		//Fantasma Rojo:
		r = new FantasmaRojo(13, 17, g);
		EntidadGrafica egRojo = new EntidadGrafica(r);
		r.setEntidadGrafica(egRojo);
		m[13][17].agregarEntidad(r);
		g.agregarLabel(egRojo);
		
		//Fantasma Rosa:
		s = new FantasmaRosa(14, 17, g);
		EntidadGrafica egRosa = new EntidadGrafica(s);
		s.setEntidadGrafica(egRosa);
		m[14][17].agregarEntidad(s);
		g.agregarLabel(egRosa);
	}
}
