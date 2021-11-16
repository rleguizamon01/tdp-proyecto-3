package Audio;

import java.io.InputStream;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class AudioPlayer {
	protected String path;
	protected AdvancedPlayer ap;
	
	public AudioPlayer(String s) {
		path = s;
		ap = null;
	}
	
	public AudioPlayer(String s, int p) {
		path = s;
		ap = null;
	}
	
	public void reproducir() {
		try {
			InputStream is = AudioPlayer.class.getResourceAsStream(path);
			ap = new AdvancedPlayer(is);
			ap.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void frenar() {
		ap.stop();
	}
}
