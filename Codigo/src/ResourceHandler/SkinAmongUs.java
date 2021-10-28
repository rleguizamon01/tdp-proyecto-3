package ResourceHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class SkinAmongUs extends Skin {
	private static SkinAmongUs me;

	private SkinAmongUs() {
		super("/RecursosAmongUs/", "skinAmongUs.properties");
	}
	
	public static SkinAmongUs getSkinAmongUs() {
		if(me == null) {
			me = new SkinAmongUs();
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
	public String getFrutaCI() {
		int rand = ThreadLocalRandom.current().nextInt(1, 4);
		
		if(rand % 3 == 0)
			return path + p.getProperty("fruta1");
		else if(rand % 3 == 1)
			return path + p.getProperty("fruta2");
		else
			return path + p.getProperty("fruta3");
		
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
