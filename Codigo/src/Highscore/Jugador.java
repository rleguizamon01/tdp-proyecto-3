package Highscore;

import java.io.Serializable;

public class Jugador implements Comparable<Jugador>, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String nombre;
	protected int puntaje;
	
	public Jugador(String n) {
		nombre = n;
		puntaje = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public String toString() {
		return nombre + ": " + puntaje;
	}

	@Override
	public int compareTo(Jugador o) {
		return -((Integer) puntaje).compareTo(o.getPuntaje());
	}
	
	
}
