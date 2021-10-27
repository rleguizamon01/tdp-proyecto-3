package ResourceHandler;

public class ResourceHandler {
	public static final SkinStrategy SKIN_TETRIS = SkinTetris.getSkinTetris();
	public static final SkinStrategy SKIN_STAR_WARS = SkinStarWars.getSkinStarWars();
	
	private static SkinStrategy s;
	
	public static void setStrategy(SkinStrategy ss) {
		s = ss;
	}
	
	public static String getPacmanNCI() {
		return s.getPacmanNCI();
	}
	
	public static String getPacmanSCI() {
		return s.getPacmanSCI();
	}
	
	public static String getPacmanECI() {
		return s.getPacmanECI();
	}
	
	public static String getPacmanOCI() {
		return s.getPacmanOCI();
	}
	
	public static String getFantasmaRojoCI() {
		return s.getFantasmaRojoCI();
	}
	
	public static String getFantasmaRosaCI() {
		return s.getFantasmaRosaCI();
	}
	
	public static String getFantasmaAzulCI() {
		return s.getFantasmaAzulCI();
	}
	
	public static String getFantasmaNaranjaCI() {
		return s.getFantasmaNaranjaCI();
	}
	
	public static String getFantasmaVulnerableCI() {
		return s.getFantasmaVulnerableCI();
	}
	
	/*
	 * ACA PODEMOS HACER QUE NO DEPENDA DE LA STRATEGY, SINO QUE DEVUELVA SIEMPRE LO MISMO.
	 * O MEJOR TODAVIA, IMPLEMENTAR LOS METODOS DE UNA EN LA CLASE SKIN, Y QUE NADA LOS REDEFINA.
	 * A VER EN DESARROLLO.
	 * */
	
	public static String getParedCI() {
		return s.getParedCI();
	}
	
	public static String getPisoCI() {
		return s.getPisoCI();
	}
	
	public static String getMonedaCI() {
		return s.getMonedaCI();
	}
	
	public static String getPowerPelletCI() {
		return s.getPowerPelletCI();
	}
	
	public static String getFrutaCI() {
		return s.getFrutaCI();
	}
	
	public static String getPocionVelocidadCI() {
		return s.getPocionVelocidadCI();
	}
	
	public static String getPocionBombaCI() {
		return s.getPocionBombaCI();
	}
	
	public static String getBombaCI() {
		return s.getBombaCI();
	}
}
