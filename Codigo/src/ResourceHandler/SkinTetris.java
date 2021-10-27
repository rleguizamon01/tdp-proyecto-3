package ResourceHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SkinTetris extends Skin{
	private static SkinTetris me;
	
	private SkinTetris() {
		super();
		
		path = "/ResourcesTetris/";
		p = new Properties();
		
		try {
			InputStream fis = SkinTetris.class.getResourceAsStream("skinTetris.properties");
			p.load(fis);
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static SkinTetris getSkinTetris() {
		if(me == null) {
			me = new SkinTetris();
		}
		
		return me;
	}
}
