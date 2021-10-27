package Entidad;

import Utilidad.Position;

public interface EstadoFantasma {
	public Position siguientePosicion();
	public void afectar();
	public String caminoImagen();
}
