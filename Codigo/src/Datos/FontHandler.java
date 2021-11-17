package Datos;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class FontHandler {
	public static Font FUENTE_H1;
	public static Font FUENTE_H2;
	public static Font FUENTE_H3;
	public static Font FUENTE_H4;
	
	public static void cargarFuentes() {
		try {
			FUENTE_H1 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Datos/FuenteOchoBit.ttf")).deriveFont(48f);
			FUENTE_H2 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Datos/FuenteOchoBit.ttf")).deriveFont(36f);
			FUENTE_H3 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Datos/FuenteOchoBit.ttf")).deriveFont(24f);
			FUENTE_H4 = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Datos/FuenteOchoBit.ttf")).deriveFont(12f);

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		    ge.registerFont(FUENTE_H1);
		    ge.registerFont(FUENTE_H2);
		    ge.registerFont(FUENTE_H3);
		    ge.registerFont(FUENTE_H4);
		} catch (Exception e) {
			FUENTE_H1 = null;
			FUENTE_H2 = null;
			FUENTE_H3 = null;
			FUENTE_H4 = null;
		    e.printStackTrace();
		}
	}
}
