package Grilla;

import java.util.ArrayList;
import Entidad.Entidad;
import Utilidad.Position;
import ResourceHandler.ResourceHandler;

public class Bloque {
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
	
	public String getCaminoImagen() {
		String ci = "";
		
		if(esPared) {
			ci = ResourceHandler.getParedCI();
		} else if(misEntidades.isEmpty()) {
			ci = ResourceHandler.getPisoCI();
		} else {
			ci = caminoMaximaPrioridad();
		}
		
		return ci;
	}
	
	private String caminoMaximaPrioridad() {
		String cmp = "";
		int maximaPrioridad = Integer.MIN_VALUE;
		
		for(Entidad e : misEntidades) {
			if(e.getPrioridad() > maximaPrioridad) {
				cmp = e.getCaminoImagen();
				maximaPrioridad = e.getPrioridad();
			}
		}
		
		return cmp;
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
}
