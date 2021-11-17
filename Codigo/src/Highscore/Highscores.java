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
		
		int maximoTamano = 36;
		int longitudJugador = 0;
		int longitudPuntaje = 0;
		int cantidadEspacios = 0;
		
		String nombre = "";
		int puntaje = 0;
		
		for(Jugador j : tabla) {
			nombre = j.getNombre();
			puntaje = j.getPuntaje();
			
			longitudJugador = nombre.length();
			longitudPuntaje = contarDigitos(puntaje);
			
			cantidadEspacios = maximoTamano - (longitudJugador + longitudPuntaje);
			
			s += nombre + repetirCaracter('-', cantidadEspacios) + puntaje + "\n";
			
		}
		
		return s;
	}
	
	protected String repetirCaracter(char c, int n) {
		String s = "";
		
		for(int i = 0; i < n; i++)
			s += c;
		
		return s;
	}
	
	protected int contarDigitos(int n) {
		int res = 0;
		
		do {
			n /= 10;
			res++;
		} while(n != 0);
		
		return res;
	}
	
	protected void ordenar() {
		Collections.sort(tabla);
	}
}
