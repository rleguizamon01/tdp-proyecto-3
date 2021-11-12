package IAs;

import Grilla.Grilla;
import Utilidad.Position;

public class ChaseIAAzul implements ChaseIA {
	protected int cantFilas = 36;
	protected int cantColum = 28;
	protected Grilla miGrilla;
	
	public ChaseIAAzul(Grilla g) {
		miGrilla = g;
	}
	
	
	@Override
	public Position getTargetBlock() {
		Position tb = new Position();
		
		Position posRojo = miGrilla.getRojo().getPosicionAbsoluta();
		Position posPac = miGrilla.getPacman().getPosicionAbsoluta();
		
		//Abstraemos el concepto de Position al de "vector bidimensional" donde fila = primera componente
		//y columna = segunda componente.
		Position vectorRojoPacman = new Position();
		vectorRojoPacman.setFila(posPac.getFila() - posRojo.getFila());
		vectorRojoPacman.setColumna(posPac.getColumna() - posRojo.getColumna());

		/*Para rotarlo, aplicamos una transformacion lineal: multiplicamos por la matriz
		*  ----------
		*  | -1   0 |
		*  |  0  -1 |
		*  ..........
		*  Para no tener que hacer un producto matricial O(n^3) cada vez que querramos mover al fantasma azul
		*  desarrollamos la formula:
		*  ----------    -----    ------
		*  | -1   0 |    | x |    | -x |
		*  |  0  -1 | *  | y | =  | -y |
		*  ..........    -----    ------
		*  PERO esto supone que tenemos cuatro cuadrantes, como en el plano R^2. Este no es el caso para nuestro
		*  sistema de coordenadas matriciales, por lo que "corregimos" sumando el vector {27, 35}. Estos numeros son
		*  elegidos en base a la cantidad de filas y columnas de la matriz. Luego, la formula de rotacion final es:
		*  ----------
		*  | 27 - x |
		*  | 35 - y |
		*  ----------
		*/
		
		tb.setFila(cantFilas - vectorRojoPacman.getFila() - 1);
		tb.setColumna(cantColum - vectorRojoPacman.getColumna() - 1);
		
		return tb;
	}

}
