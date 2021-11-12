package ResourceHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class SkinCarpincho extends Skin {
	private static SkinCarpincho me;

	private SkinCarpincho() {
		super("/RecursosCarpincho/", "skinCarpincho.properties");
	}
	
	public static SkinCarpincho getSkinCarpincho() {
		if(me == null) {
			me = new SkinCarpincho();
		}
		
		return me;
	}
	
	@Override
	public String getPacmanNCI() {
		return path + p.getProperty("pacmanD");
	}

	@Override
	public String getPacmanSCI() {
		return path + p.getProperty("pacmanI");
	}

	@Override
	public String getPacmanECI() {
		return path + p.getProperty("pacmanD");
	}

	@Override
	public String getPacmanOCI() {
		return path + p.getProperty("pacmanI");
	}
	
	@Override
	public String getPacmanVelocidadNCI() {
		return path + p.getProperty("pacmanVD");
	}

	@Override
	public String getPacmanVelocidadSCI() {
		return path + p.getProperty("pacmanVI");
	}

	@Override
	public String getPacmanVelocidadECI() {
		return path + p.getProperty("pacmanVD");
	}

	@Override
	public String getPacmanVelocidadOCI() {
		return path + p.getProperty("pacmanVI");
	}

	@Override
	public String getPacmanBombaNCI() {
		return path + p.getProperty("pacmanBD");
	}

	@Override
	public String getPacmanBombaSCI() {
		return path + p.getProperty("pacmanBI");
	}

	@Override
	public String getPacmanBombaECI() {
		return path + p.getProperty("pacmanBD");
	}

	@Override
	public String getPacmanBombaOCI() {
		return path + p.getProperty("pacmanBI");
	}

	@Override
	public String getFantasmaRojoDCI() {
		return path + p.getProperty("fantasmaRojoD");
	}

	@Override
	public String getFantasmaRojoICI() {
		return path + p.getProperty("fantasmaRojoI");
	}

	@Override
	public String getFantasmaRosaDCI() {
		return path + p.getProperty("fantasmaRosaD");
	}

	@Override
	public String getFantasmaRosaICI() {
		return path + p.getProperty("fantasmaRosaI");
	}

	@Override
	public String getFantasmaAzulDCI() {
		return path + p.getProperty("fantasmaAzulD");
	}

	@Override
	public String getFantasmaAzulICI() {
		return path + p.getProperty("fantasmaAzulI");
	}

	@Override
	public String getFantasmaNaranjaDCI() {
		return path + p.getProperty("fantasmaNaranjaD");
	}

	@Override
	public String getFantasmaNaranjaICI() {
		return path + p.getProperty("fantasmaNaranjaI");
	}

	@Override
	public String getFantasmaVulnerableDCI() {
		return path + p.getProperty("fantasmaVulnerableD");
	}

	@Override
	public String getFantasmaVulnerableICI() {
		return path + p.getProperty("fantasmaVulnerableI");
	}

	@Override
	public String getFantasmaMuertoDCI() {
		return path + p.getProperty("fantasmaMuertoD");
	}

	@Override
	public String getFantasmaMuertoICI() {
		return path + p.getProperty("fantasmaMuertoI");
	}

	@Override
	public String getPowerPelletCI() {
		return path + p.getProperty("powerPellet");
	}

	@Override
	public String getPocionVelocidadCI() {
		return path + p.getProperty("pocionVelocidad");
	}

	@Override
	public String getPocionBombaCI() {
		return path + p.getProperty("pocionBomba");
	}

	@Override
	public String getBombaCI() {
		return path + p.getProperty("bomba");
	}
	
}
