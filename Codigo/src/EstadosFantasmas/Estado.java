package EstadosFantasmas;

import Entidad.Fantasma;
import Grilla.Grilla;
import ResourceHandler.ResourceHandler;
import Utilidad.Position;

public abstract class Estado implements EstadoFantasma {
	protected Fantasma miFantasma;
	protected Grilla miGrilla;
	
	public Estado(Fantasma f, Grilla g) {
		miFantasma = f;
		miGrilla = g;
	}
	
	protected Position masCercanoA(Position target) {
		Position norte = miFantasma.getPosicionAbsoluta().clone();
		norte.setColumna(norte.getColumna() - miFantasma.getVelocidad());
		
		Position sur = miFantasma.getPosicionAbsoluta().clone();
		sur.setColumna(sur.getColumna() + miFantasma.getVelocidad());
		
		Position este = miFantasma.getPosicionAbsoluta().clone();
		este.setFila(este.getFila() + miFantasma.getVelocidad());
		
		Position oeste = miFantasma.getPosicionAbsoluta().clone();
		oeste.setFila(oeste.getFila() - miFantasma.getVelocidad());
		
		Position[] prioridades = new Position[3];
		int index = 0;
		char d = miFantasma.getDireccion();
		
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
		
		Position pEID;
		Position pEII;
		Position pESD;
		
		for(Position p : prioridades) {
			aux = distancia(p, target);
			
			pEID = new Position(p.getFila() + miFantasma.getAncho() - 1, p.getColumna() + miFantasma.getAlto() - 1);
			pEII = new Position(p.getFila(), p.getColumna() + miFantasma.getAlto() - 1);
			pESD = new Position(p.getFila() + miFantasma.getAncho() - 1, p.getColumna());
			
			if(miGrilla.zonaLibre(p) && miGrilla.zonaLibre(pEID) && miGrilla.zonaLibre(pEII) && miGrilla.zonaLibre(pESD))
				if(aux < distanciaMinima) {
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
}
