package Grilla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Entidad.Entidad;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class Bloque implements Iterable<Entidad> {
	protected boolean esPared;
	protected Position pos;
	protected List<Entidad> misEntidades;
	
	public Bloque() {
		pos = null;
		esPared = false;
		misEntidades = null;
	}
	
	public Bloque(int f, int c, boolean p) {
		pos = new Position(f, c);
		esPared = p;
		misEntidades = Collections.synchronizedList(new ArrayList<Entidad>());
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
