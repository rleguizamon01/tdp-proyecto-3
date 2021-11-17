package Datos;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Highscore.*;

public class DataHandler {
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
	
	public static void guardar(Serializable s, String path) {
		//Codigo tomado de baeldung y de la calse 31 del profesor.
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		    objectOutputStream.writeObject(s);
		    objectOutputStream.flush();
		    objectOutputStream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	protected static Serializable cargar(String path) {
		Serializable s;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
		    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		    s = (Highscores) objectInputStream.readObject();
		    objectInputStream.close();
		}  catch (IOException | ClassNotFoundException e) {
			s = null;
			e.printStackTrace();
		}
	    
	    return s;
	}
	
	public static Highscores getHighscore() {
		Highscores hs;
		
		hs = (Highscores) cargar(Highscores.SCORE_PATH);
		
		if(hs == null)
			hs = new Highscores();
		
		return hs;
	}
	
	public static void reiniciarHighscores() {
		Highscores hs = new Highscores();
		guardar(hs, Highscores.SCORE_PATH);
	}
}
