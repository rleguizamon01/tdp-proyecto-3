package Grilla;

import Entidad.EntidadGrafica;
import Entidad.Pacman;
import Logica.Juego;

public class NivelTres extends Nivel {

	public NivelTres(Juego j, Grilla g) {
		super(j, g);
		mapa = new String[]{
				"                            ",
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
				"A.............W............A",
				"A.AAAAAAAAAAAAAAAAAAAAAAAA.A",
				"A.........AA....AA.........A",
				"A.AAAAAAA.AA.AA.AA.AAAAAAA.A",
				"A.AAAAAAA.AA.AA.AA.AAAAAAA.A",
				"A.AA.........AA.........AA.A",
				"A.AA.AA.AAAA.AA.AAAA.AA.AA.A",
				"A....AA.AAAA.AA.AAAA.AA....A",
				"AAAA.AA.AAAA.AA.AAAA.AA.AAAA",
				"AAAA.AA..............AA.AAAA",
				".....AAAA.AAAAAAAA.AAAA.....",
				"A.AA.AAAA.AAAAAAAA.AAAA.AA.A",
				"A.AA....................AA.A",
				"A.AAAA.AA.AAA  AAA.AA.AAAA.A",
				"A.AAAA.AA.A      A.AA.AAAA.A",
				"A......AA.A      A.AA......A",
				"A.AA.AAAA.A      A.AAAA.AA.A",
				"A.AA.AAAA.AAAAAAAA.AAAA.AA.A",
				"A.AA.........F..........AA.A",
				"A.AAAA.AAAAA.AA.AAAAA.AAAA.A",
				"A.AAAA.AAAAA.AA.AAAAA.AAAA.A",
				"A......AA....AA....AA......A",
				"AAA.AA.AA.AAAAAAAA.AA.AA.AAA",
				"AAA.AA.AA.AAAAAAAA.AA.AA.AAA",
				"A...AA................AA...A",
				"A.AAAA.AAAAA.AA.AAAAA.AAAA.A",
				"A.AAAA.AAAAA.AA.AAAAA.AAAA.A",
				"AB.....AA....AA....AA.....VA",
				"A.AAAA.AA.AAAAAAAA.AA.AAAA.A",
				"A.AAAA.AA.AAAAAAAA.AA.AAAA.A",
				"A......AA..........AA......A",
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAA",
				"                            ",
				"                            "};
	}

	@Override
	protected void agregarEntidadesMoviles(Bloque[][] m) {
		pacman = new Pacman(15, 26, g);
		EntidadGrafica egPacman = new EntidadGrafica(pacman);
		pacman.setEntidadGrafica(egPacman);
		m[3][3].agregarEntidad(pacman);
		g.agregarLabel(egPacman);
	}

}
