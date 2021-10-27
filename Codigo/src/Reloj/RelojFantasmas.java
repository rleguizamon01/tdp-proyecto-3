package Reloj;

import Logica.Juego;

public class RelojFantasmas extends Reloj {
	protected int velocidadRojo;
	protected int velocidadRosa;
	protected int velocidadAzul;
	protected int velocidadNaranja;
	protected int intervalo;
	protected int ciclo;
	
	public RelojFantasmas(Juego j, int vr, int vs, int va, int vn) {
		super(j);
		
		velocidadRojo = vr;
		velocidadRosa = vs;
		velocidadAzul = va;
		velocidadNaranja = vn;
		
		intervalo = gcd(new int[]{vr, vs, va, vn}); //dcm
		ciclo = vr*va*vs*vn/intervalo; //mcm
	}
	
	public void run() {
		int tick = 0;
		
		try {
			while(sePuedeJugar) {
				Thread.sleep(intervalo);
				tick = (tick + intervalo) % ciclo;
				
				if(tick % velocidadRojo == 0)
					miJuego.pedirMoverRojo();
				if(tick % velocidadRosa == 0)
					miJuego.pedirMoverRosa();
				if(tick % velocidadAzul == 0)
					miJuego.pedirMoverAzul();
				if(tick % velocidadNaranja == 0)
					miJuego.pedirMoverNaranja();
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
	
	protected int gcd(int[] a) {
		int result = 0;
		
		for(int i : a) {
			result = gcd(result, a[i]);
		}
		
		return result;
	}

}
