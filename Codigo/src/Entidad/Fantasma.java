package Entidad;

import Grilla.Grilla;
import Utilidad.Position;

public abstract class Fantasma extends EntidadMovil {
	//Atributos de clase
	protected EstadoFantasma chase;
	protected EstadoFantasma scatter;
	
	//Atributos de Instancia
	protected EstadoFantasma miEstado;
	protected Position esquina;
	
	public Fantasma(int f, int c, int p, Grilla g) {
		super(f, c, p, g);
	}

	@Override
	public Position getSiguientePosicion() {
		return miEstado.siguientePosicion();
	}

	@Override
	public void afectar() {
		miEstado.afectar();
	}
	
	public void ponerEnRun() {
		miEstado = new EstadoRun();
	}
	
	public void ponerEnDead() {
		miEstado = new EstadoDead();
	}
	
	public void ponerEnChase() {
		miEstado = chase;
	}
	
	public void ponerEnScatter() {
		miEstado = scatter;
	}

}
