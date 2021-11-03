package ResourceHandler;

public class ResourceHandler {
	public static final SkinStrategy SKIN_AMONG_US = SkinAmongUs.getSkinAmongUs();
	
	private static SkinStrategy s;
	
	public static void setStrategy(SkinStrategy ss) {
		s = ss;
	}
	
	//------------------------------------------------------------------//
	//							NO ENTIDADES							//
	//------------------------------------------------------------------//
	
	public static String getPisoCI() {
		return s.getPisoCI();
	}
	
	public static String getParedCI() {
		return s.getParedCI();
	}
	
	//------------------------------------------------------------------//
	//						ENTIDADES FIJAS								//
	//------------------------------------------------------------------//
	
	public static String getMonedaCI() {
		return s.getMonedaCI();
	}
	
	public static String getPowerPelletCI() {
		return s.getPowerPelletCI();
	}
	
	public static String getBombaCI() {
		return s.getBombaCI();
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
	
	//------------------------------------------------------------------//
	//						ENTIDADES MOVILES							//
	//------------------------------------------------------------------//
	
	public static String getPacmanCI(char d) {
		String ci = "";
		
		switch(d) {
		case 'N':
			ci = s.getPacmanNCI();
			break;
		case 'S':
			ci = s.getPacmanSCI();
			break;
		case 'E':
			ci = s.getPacmanECI();
			break;
		case 'O':
			ci = s.getPacmanOCI();
			break;
		}
		
		return ci;
	}
	
	public static String getFantasmaRojoCI(char d) {
		String ci = "";
		
		switch(d) {
		case 'N':
		case 'E':
			ci = s.getFantasmaRojoDCI();
			break;
		case 'S':
		case 'O':
			ci = s.getFantasmaRojoICI();
			break;
		}
		
		return ci;
	}
	
	public static String getFantasmaRosaCI(char d) {
		String ci = "";
		
		switch(d) {
		case 'N':
		case 'E':
			ci = s.getFantasmaRosaDCI();
			break;
		case 'S':
		case 'O':
			ci = s.getFantasmaRosaICI();
			break;
		}
		
		return ci;
	}
	
	public static String getFantasmaAzulCI(char d) {
		String ci = "";
		
		switch(d) {
		case 'N':
		case 'E':
			ci = s.getFantasmaAzulDCI();
			break;
		case 'S':
		case 'O':
			ci = s.getFantasmaAzulICI();
			break;
		}
		
		return ci;
	}
	
	public static String getFantasmaNaranjaCI(char d) {
		String ci = "";
		
		switch(d) {
		case 'N':
		case 'E':
			ci = s.getFantasmaNaranjaDCI();
			break;
		case 'S':
		case 'O':
			ci = s.getFantasmaNaranjaICI();
			break;
		}
		
		return ci;
	}
	
	public static String getFantasmaVulnerableCI(char d) {
		String ci = "";
		
		switch(d) {
		case 'N':
		case 'E':
			ci = s.getFantasmaVulnerableDCI();
			break;
		case 'S':
		case 'O':
			ci = s.getFantasmaVulnerableICI();
			break;
		}
		
		return ci;
	}
	
	public static String getFantasmaMuertoCI(char d) {
		String ci = "";
		
		switch(d) {
		case 'N':
		case 'E':
			ci = s.getFantasmaMuertoDCI();
			break;
		case 'S':
		case 'O':
			ci = s.getFantasmaMuertoICI();
			break;
		}
		
		return ci;
	}
}