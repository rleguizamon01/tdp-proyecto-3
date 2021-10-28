package Grilla;

import Entidad.*;

public interface EstrategiaNivel {
	public void strategyInitialize(Bloque[][] m);
	
	public Pacman getPacman();
	public Fantasma getRojo();
	public Fantasma getRosa();
	public Fantasma getAzul();
	public Fantasma getNaranja();
}
