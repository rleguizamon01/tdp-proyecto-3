package Entidad;

import Grilla.Grilla;
import Utilidad.Position;

public class EstadoScatter implements EstadoFantasma {
	private Position esquinaBuscada;
	private Fantasma miFantasma;
	private Grilla miGrilla;
	
	public EstadoScatter(Position eb, Fantasma f, Grilla g) {
		esquinaBuscada = eb;
		miFantasma = f;
		miGrilla = g;
	}
	
	@Override
	public Position siguientePosicion() {
		return masCercanoA(esquinaBuscada);
	}
	
	protected Position masCercanoA(Position target) {
		Position norte = miFantasma.getPosition().clone();
		norte.setColumna(norte.getColumna() - 1);
		
		Position sur = miFantasma.getPosition().clone();
		sur.setColumna(sur.getColumna() + 1);
		
		Position este = miFantasma.getPosition().clone();
		este.setFila(este.getFila() + 1);
		
		Position oeste = miFantasma.getPosition().clone();
		oeste.setFila(oeste.getFila() - 1);
		
		Position[] prioridades = new Position[3];
		int index = 0;
		char d = miFantasma.getDireccion();
		
		/**if(d != 'S') {
			prioridades[index] = norte;
			index++;
		} else if (d != 'N') {
			prioridades[index] = sur;
			index++;
		} else if(d != 'O') {
			prioridades[index] = este;
			index++;
		} else {
			prioridades[index] = oeste;
			index++;
		}**/
		
		switch (d) {
		case 'N':
			prioridades[0] = norte;
			prioridades[1] = este;
			prioridades[2] = oeste;
			break;
			
		case 'S':
			prioridades[0] = sur;
			prioridades[1] = este;
			prioridades[2] = oeste;
			break;
			
		case 'E':
			prioridades[0] = norte;
			prioridades[1] = sur;
			prioridades[2] = este;
			break;
			
		case 'O':
			prioridades[0] = norte;
			prioridades[1] = sur;
			prioridades[2] = oeste;
			break;
		}
		
		float distanciaMinima = Float.MAX_VALUE;
		Position min = null;
		float aux = 0;
		
		for(Position p : prioridades) {
			aux = distancia(p, target);
			if(miGrilla.bloqueLibre(p))
				if(aux <= distanciaMinima) {
					distanciaMinima = aux;
					min = p;
				}
		}
		
		return min;
	}
	
	protected float distancia(Position p1, Position p2) {
		int f1 = p1.getFila();
		int f2 = p2.getFila();
		int c1 = p1.getColumna();
		int c2 = p2.getColumna();
		
		float fCuadrado = (f1-f2)*(f1-f2);
		float cCuadrado = (c1-c2)*(c1-c2);
		
		return (float) Math.sqrt(fCuadrado + cCuadrado);
	}

	@Override
	public void afectar() {
		// TODO Auto-generated method stub

	}

	@Override
	public String caminoImagen() {
		return miFantasma.imagenFantasmaColor();
	}

}
