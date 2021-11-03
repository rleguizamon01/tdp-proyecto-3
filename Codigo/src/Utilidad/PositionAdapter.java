package Utilidad;

public class PositionAdapter {
	public static boolean seSobreponenAdaptado(Position p, Position q, Position r, Position s) {
		/**
		 * ¿Y por que todo este circo?
		 * Porque las posiciones que manejamos nosotros son "matriciales", o sea,
		 * consideran como (0, 0) el punto superior izquierdo, y el algoritmo de
		 * superposicion esta ideado para posiciones cartesianas, las cuales considera
		 * (0, 0) como el punto interior izquierdo.
		 * 
		 * Jugando con GeoGebra y con lo que aprendimos en las materias de matematica,
		 * nos las ingeniamos para proponer la siguiente adaptacion, la cual "traduce"
		 * de nuestro sistema coordenado al que utiliza el algoritmo de superposicion.
		 */
		
		Position a = new Position(p.getFila(), q.getColumna());
		Position b = new Position(q.getFila(), p.getColumna());
		
		Position c = new Position(r.getFila(), s.getColumna());
		Position d = new Position(s.getFila(), r.getColumna());
		
		return Position.seSobreponen(a, b, c, d);
	}
}
