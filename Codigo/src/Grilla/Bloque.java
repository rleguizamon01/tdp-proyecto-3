package Grilla;

import java.util.ArrayList;
import java.util.Iterator;

import Entidad.Entidad;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class Bloque implements Iterable<Entidad> {
	protected boolean esPared;
	protected Position pos;
	protected ArrayList<Entidad> misEntidades;
	
	public Bloque() {
		pos = null;
		esPared = false;
		misEntidades = null;
	}
	
	public Bloque(Position p, boolean pared, ArrayList<Entidad> iterable) {
		pos = p;
		esPared = pared;
		misEntidades = (ArrayList<Entidad>) iterable.clone(); //Sin este casteo no compila.
	}
	
	public Bloque(Position p, boolean pared) {
		pos = p;
		esPared = pared;
		misEntidades = new ArrayList<Entidad>();
	}
	
	public Bloque(Position p) {
		pos = p;
		esPared = false;
		misEntidades = new ArrayList<Entidad>();
	}
	
	public Bloque(int f, int c, boolean p) {
		pos = new Position(f, c);
		esPared = p;
		misEntidades = new ArrayList<Entidad>();
	}
	
	public void estadoPared(boolean p) {
		esPared = p;
	}
	
	public boolean esPared() {
		return esPared;
	}
	
	public synchronized void agregarEntidad(Entidad e) {
		misEntidades.add(e);
	}
	
	public synchronized void eliminarEntidad(Entidad e) {
		misEntidades.remove(e);
	}
	
	public void ejecutarEfectos() {
		for(Entidad e : misEntidades) {
			e.afectar();
		}
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public String toString() {
		return pos.toString();
	}

	@Override
	public Iterator<Entidad> iterator() {
		return misEntidades.iterator();
	}
}
