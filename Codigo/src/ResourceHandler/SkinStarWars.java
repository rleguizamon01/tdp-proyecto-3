package ResourceHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SkinStarWars extends Skin{
private static SkinStarWars me;
	
	private SkinStarWars() {
		super();
		
		path = "/ResourcesStarWars/";
		p = new Properties();
		
		try {
			InputStream fis = SkinStarWars.class.getResourceAsStream("skinStarWars.properties");
			p.load(fis);
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static SkinStarWars getSkinStarWars() {
		if(me == null) {
			me = new SkinStarWars();
		}
		
		return me;
	}
}
