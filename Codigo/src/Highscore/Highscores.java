package Highscore;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Highscores implements Serializable {
	public  static final String SCORE_PATH = "./src/Highscore/highscores.tabla";
	private static final long serialVersionUID = 2L;
	
	protected List<Jugador> tabla;
	
	public Highscores() {
		tabla = new ArrayList<Jugador>();
	}
	
	public void agregarJugador(Jugador j) {
		tabla.add(j);
	}
	
	public String toString() {
		ordenar();
		String s = "";
		
		for(Jugador j : tabla)
			s += j + "\n";
		
		return s;
	}
	
	protected void ordenar() {
		Collections.sort(tabla);
	}
}
