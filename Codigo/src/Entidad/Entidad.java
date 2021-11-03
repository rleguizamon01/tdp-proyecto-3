package Entidad;

import ResourceHandler.ResourceHandler;
import Utilidad.*;
import Grilla.Grilla;

public abstract class Entidad {
	protected Position posicionAbsoluta;
	protected Position posicionZona;
	protected Grilla miGrilla;
	protected int ancho;
	protected int alto;
	protected EntidadGrafica eg;
	
	public abstract void afectar();
	public abstract String getCaminoImagen();
	
	public Entidad(int xAbs, int yAbs, int xZona, int yZona, int w, int h, Grilla g) {
		posicionAbsoluta = new Position(xAbs, yAbs);
		posicionZona = new Position(xZona, yZona);
		ancho = w;
		alto = h;
		miGrilla = g;
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return eg;
	}
	
	public Position getPosicionAbsoluta() {
		return posicionAbsoluta;
	}
	
	public Position getPosicionZona() {
		return posicionZona;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public Position getEsquinaSuperiorIzquierda() {
		return getPosicionAbsoluta();
	}
	
	public Position getEsquinaSuperiorDerecha() {
		Position p = posicionAbsoluta.clone();
		
		p.setFila(p.getFila()+ancho);
		
		return p;
	}
	
	public Position getEsquinaInferiorIzquierda() {
		Position p = posicionAbsoluta.clone();
		
		p.setColumna(p.getColumna()+alto);
		
		return p;
	}
	
	public Position getEsquinaInferiorDerecha() {
		Position p = posicionAbsoluta.clone();
		
		p.setFila(p.getFila()+ancho);
		p.setColumna(p.getColumna()+alto);
		
		return p;
	}
	
	public Position[] getEsquinas() {
		Position[] esq = new Position[4];
		
		esq[0] = getEsquinaSuperiorIzquierda();
		esq[1] = getEsquinaSuperiorDerecha();
		esq[2] = getEsquinaInferiorIzquierda();
		esq[3] = getEsquinaInferiorDerecha();
		
		return esq;
	}
	
	public Position getCentro() {
		Position p = posicionAbsoluta.clone();
		
		p.setFila(p.getFila()+(ancho/2));
		p.setColumna(p.getColumna()+(alto/2));
		
		return p;
	}
	
	public void setEntidadGrafica(EntidadGrafica entGraf) {
		eg = entGraf;
	}
	
	public void setPosicionAbsoluta(Position posicionAbsoluta) {
		this.posicionAbsoluta = posicionAbsoluta;
	}
	
	public void setPosicionZona(Position posicionZona) {
		this.posicionZona = posicionZona;
	}
	
	public void setMiGrilla(Grilla miGrilla) {
		this.miGrilla = miGrilla;
	}
	
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	public void setAlto(int alto) {
		this.alto = alto;
	}
}
