package Audio;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MusicPlayer extends Thread {
	protected AudioPlayer miAP;
	protected Queue<String> ldr; //ldr = lista de reproduccion.
	protected boolean seDebeReproducir;
	
	public MusicPlayer() {
		miAP = null;
		ldr = new ConcurrentLinkedQueue<String>();
		seDebeReproducir = true;
	}
	
	public void run() {
		iniciar();
	}
	
	protected void iniciar() {
		while(seDebeReproducir && !ldr.isEmpty()) {
			miAP = new AudioPlayer(ldr.peek());
			miAP.reproducir();
			ldr.add(ldr.poll());
		}
	}
	
	public void skip() {
		ldr.add(ldr.poll());
	}
	
	public void agregarCancion(String s) {
		ldr.add(s);
	}
	
	public MusicPlayer clone() {
		MusicPlayer ret = new MusicPlayer();
		
		while(!ldr.isEmpty())
			ret.agregarCancion(ldr.poll());
		
		return ret;
	}
}
