package Audio;

public class MusicHandler {
	public static void inicializarReproductor(MusicPlayer mp) {
		mp.agregarCancion("/RecursosMusica/Cancion1.mp3");
		mp.agregarCancion("/RecursosMusica/Cancion2.mp3");
		mp.agregarCancion("/RecursosMusica/Cancion3.mp3");
	}
}
