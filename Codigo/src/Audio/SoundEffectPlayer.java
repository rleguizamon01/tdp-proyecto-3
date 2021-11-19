package Audio;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SoundEffectPlayer extends Thread {
	protected AudioPlayer miAP;
	protected Queue<String> ldr; //ldr = lista de reproduccion.
	
	/*
	 * Usamos una lista de reproduccion para los sonidos para no "comernos" ningun sonido
	 * Esto es, no interrumpir la reproduccion de un sonido A porque algun evento del juego
	 * hizo "trigger" de la reproduccion de un sonido B.
	 * 
	 * Esto causa, de vez en cuando, en situaciones muy especificas, un pequeño delay entre evento y sonido
	 * Hay en casos en los que se da un delay grande, esto es por la libreria.
	 * */
	
	protected boolean seDebeReproducir;
	protected boolean termino;
	
	public SoundEffectPlayer() {
		miAP = null;
		ldr = new ConcurrentLinkedQueue<String>();
		seDebeReproducir = true;
		termino = false;
	}
	
	public void run() {
		iniciar();
	}
	
	protected void iniciar() {
		while(seDebeReproducir) {
			if(!ldr.isEmpty()) { 
				miAP = new AudioPlayer(ldr.poll());
				miAP.reproducir();
			}
		}
	}
	
	public void agregarSonido(String s) {
		ldr.add(s);
	}
	
	public SoundEffectPlayer clone() {
		SoundEffectPlayer ret = new SoundEffectPlayer();
		
		while(!ldr.isEmpty())
			ret.agregarSonido(ldr.poll());
		
		return ret;
	}
}
