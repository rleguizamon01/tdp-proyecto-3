package Grilla;

import Entidad.EntidadGrafica;
import Entidad.FantasmaAzul;
import Entidad.FantasmaRojo;
import Entidad.FantasmaRosa;
import Entidad.Pacman;
import Logica.Juego;

public class NivelDos extends Nivel {

	public NivelDos(Juego j, Grilla g) {
		super(j, g);
		mapa = new String[]{
				"                            ",
				"                            ",
				"      AAAAAAAAAAAAAAAA      ",
				"   AAAA..............AAAA   ",
				"  AA....AA.AAAAAA.AA....AA  ",
				" AA..AA AA.AAAAAA.AA AA..AA ",
				"AA..AAA AA...AA...AA AAA..AA",
				"A..AA A AAAA.AA.AAAA A AA..A",
				"A.AAAAA AAAA.AA.AAAA AAAAA.A",
				"AW..    AA...  ...AA    ..WA",
				"AAA.AAAAAA.AAAAAA.AAAAAA.AAA",
				"AAA.AAAAAA.AAAAAA.AAAAAA.AAA",
				"A.......AA...AA...AA.......A",
				"A.AAAAA.AAAA.AA.AAAA.AAAAA.A",
				"A.AAAAA.AAAA.AA.AAAA.AAAAA.A",
				"A....AA......  ......AA....A",
				"AAAA.AAAA AAA  AAA AAAA.AAAA",
				"AAAA.AAAA A      A AAAA.AAAA",
				"0 AA.     A      A     .AA 0",
				"A AA.AAAA A      A AAAA.AA A",
				"A....AAAA AAAAAAAA AAAA....A",
				"A.AA.AA......F.......AA.AA.A",
				"A.AA.AA.AAAAAAAAAAAA.AA.AA.A",
				"A.AA.AA.AAAAAAAAAAAA.AA.AA.A",
				"0.AA.AA......AA......AA.AA.0",
				"AAAA.AAAAAAA.AA.AAAAAAA.AAAA",
				"AAAA.AAAAAAA.AA.AAAAAAA.AAAA",
				"A...W........  ........V...A",
				"A.AA.AAAAAAAAAAAAAAAAAA.AA.A",
				"A.AA.A                A.AA.A",
				"A.AA.A                A.AA.A",
				"A.AA.A                A.AA.A",
				"A....A                A....A",
				"AAAAAA                AAAAAA",
				"                            ",
				"                            "};
	}

	@Override
	protected void agregarEntidadesMoviles(Bloque[][] m) {
		pacman = new Pacman(15, 26, g);
		EntidadGrafica egPacman = new EntidadGrafica(pacman);
		pacman.setEntidadGrafica(egPacman);
		m[15][26].agregarEntidad(pacman);
		g.agregarLabel(egPacman);
		
		//Fantasma Rojo:
		r = new FantasmaRojo(13, 18, g);
		EntidadGrafica egRojo = new EntidadGrafica(r);
		r.setEntidadGrafica(egRojo);
		m[13][18].agregarEntidad(r);
		g.agregarLabel(egRojo);
		
		//Fantasma Rosa:
		s = new FantasmaRosa(14, 18, g);
		EntidadGrafica egRosa = new EntidadGrafica(s);
		s.setEntidadGrafica(egRosa);
		m[14][18].agregarEntidad(s);
		g.agregarLabel(egRosa);
		
		//Fantasma Azul:
		a = new FantasmaAzul(13, 17, g);
		EntidadGrafica egAzul = new EntidadGrafica(a);
		a.setEntidadGrafica(egAzul);
		m[13][17].agregarEntidad(a);
		g.agregarLabel(egAzul);
	}

}
