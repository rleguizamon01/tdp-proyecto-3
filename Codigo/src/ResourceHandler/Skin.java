package ResourceHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Skin implements SkinStrategy{
	protected Properties propertiesComun;
	protected String pathComun;
	
	protected Properties p;
	protected String path;
	
	public Skin(String path, String archivoProperties) {
		pathComun = "/RecursosCompartidos/";
		propertiesComun = new Properties();
		
		this.path = path;
		p = new Properties();
		
		try {
			InputStream is = Skin.class.getResourceAsStream("skin.properties");
			propertiesComun.load(is);
			
			InputStream iss = Skin.class.getResourceAsStream(archivoProperties);
			p.load(iss);
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------//
	//							RECURSOS COMUNES						//
	//------------------------------------------------------------------//
	@Override
	public String getParedCI() {
		return pathComun + propertiesComun.getProperty("pared");
	}

	@Override
	public String getPisoCI() {
		return pathComun + propertiesComun.getProperty("piso");
	}

	@Override
	public String getMonedaCI() {
		return pathComun + propertiesComun.getProperty("moneda");
	}
	
	public String getEfectoVelocidadCI() {
		return pathComun + propertiesComun.getProperty("efectoVelocidad");
	}
	
	public String getEfectoPowerPelletCI() {
		return pathComun + propertiesComun.getProperty("efectoPower");
	}
	
	public String getEfectoBombaCI() {
		return pathComun + propertiesComun.getProperty("efectoBomba");
	}
	
	@Override
	public String getPortalCI() {
		return pathComun + propertiesComun.getProperty("portal");
	}
	
	public String getPinchosCI() {
		return pathComun + propertiesComun.getProperty("pinchos");
	}
	
	@Override
	public String getFrutaCI() {
		int rand = ThreadLocalRandom.current().nextInt(1, 4);
		
		if(rand % 3 == 0)
			return pathComun + propertiesComun.getProperty("fruta1");
		else if(rand % 3 == 1)
			return pathComun + propertiesComun.getProperty("fruta2");
		else
			return pathComun + propertiesComun.getProperty("fruta3");
		
	}
}
