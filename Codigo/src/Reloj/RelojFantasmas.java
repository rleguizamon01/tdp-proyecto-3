package Reloj;

import java.util.ArrayList;
import java.util.Collections;

import Logica.Juego;

public class RelojFantasmas extends Reloj {
	protected ArrayList<Integer> indices;
	
	public RelojFantasmas(boolean spj, int in, Juego j) {
		super(spj, in, j);
		
		indices = new ArrayList<Integer>();
		
		indices.add(0);
		indices.add(1);
		indices.add(2);
		indices.add(3);
	}

	@Override
	protected void notificar() {
		/* 
		 * Hacemos esto para llamar al movimiento de los fantasmas en un orden aleatorio cada vez tick.
		 * Es quizas un poco "extra" pero nos parecio un buen detalle que no se llamara a mover siempre
		 * en el mismo orden.
		 */
		Collections.shuffle(indices);
		for(int i : indices) {
			switch(i) {
				case 0:
					miJuego.pedirMoverRojo();
					break;
				case 1:
					miJuego.pedirMoverRosa();
					break;
				case 2:
					miJuego.pedirMoverAzul();
					break;
				case 3:
					miJuego.pedirMoverNaranja();
					break;
			}
		}
	}

}
