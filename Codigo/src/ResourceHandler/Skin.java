package ResourceHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Skin implements SkinStrategy{
	//A ser usado en las redefiniciones.
	protected Properties p;
	protected String path;
	
	//A ser usado en esta clase.
	private Properties commonP;
	private String commonPath;
	
	public Skin() {
		commonPath = "/RecursosCompartidos/";
		commonP = new Properties();
		
		try {
			InputStream is = SkinTetris.class.getResourceAsStream("skin.properties");
			commonP.load(is);
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public String getPacmanNCI() {
		return path + p.getProperty("pacmanN");
	}
	
	@Override
	public String getPacmanSCI() {
		return path + p.getProperty("pacmanS");
	}
	
	@Override
	public String getPacmanECI() {
		return path + p.getProperty("pacmanE");
	}
	
	@Override
	public String getPacmanOCI() {
		return path + p.getProperty("pacmanO");
	}

	@Override
	public String getFantasmaRojoCI() {
		return path + p.getProperty("fantasmaRojo");
	}

	@Override
	public String getFantasmaRosaCI() {
		return path + p.getProperty("fantasmaRosa");
	}

	@Override
	public String getFantasmaAzulCI() {
		return path + p.getProperty("fantasmaAzul");
	}

	@Override
	public String getFantasmaNaranjaCI() {
		return path + p.getProperty("fantasmaNaranja");
	}

	@Override
	public String getFantasmaVulnerableCI() {
		return path + p.getProperty("fantasmaVulnerable");
	}

	//---------------------------------------------------------------
	
	@Override
	public String getParedCI() {
		return commonPath + commonP.getProperty("pared");
	}

	@Override
	public String getPisoCI() {
		return commonPath + commonP.getProperty("piso");
	}

	@Override
	public String getMonedaCI() {
		return commonPath + commonP.getProperty("moneda");
	}

	@Override
	public String getPowerPelletCI() {
		return commonPath + commonP.getProperty("powerPellet");
	}

	@Override
	public String getFrutaCI() {
		return commonPath + commonP.getProperty("fruta");
	}

	@Override
	public String getPocionVelocidadCI() {
		return commonPath + commonP.getProperty("pocionVelocidad");
	}

	@Override
	public String getPocionBombaCI() {
		return commonPath + commonP.getProperty("pocionBomba");
	}

	@Override
	public String getBombaCI() {
		return commonPath + commonP.getProperty("bomba");
	}
}
