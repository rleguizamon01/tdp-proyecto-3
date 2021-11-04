package Utilidad;

public class Position implements Cloneable {
	protected int f;
	protected int c;
	
	public Position(int f, int c) {
		this.f = f;
		this.c = c;
	}
	
	public Position() {
		f = 0;
		c = 0;
	}

	public int getFila() {
		return f;
	}

	public void setFila(int f) {
		this.f = f;
	}

	public int getColumna() {
		return c;
	}

	public void setColumna(int c) {
		this.c = c;
	}
	
	public Position clone() {
		return new Position(f, c);
	}
	
	public boolean equals(Position p) {
		return f == p.getFila() && c == p.getColumna();
	}
	
	public String toString() {
		return "(" + f + ", " + c + ")";
	}
	
	public static boolean seSobreponen(Position l1, Position r1, Position l2, Position r2) {
		boolean ret = true;
		
		int xl1 = l1.getFila();
		int yl1 = l1.getColumna();
		
		int xr1 = r1.getFila();
		int yr1 = r1.getColumna();
		
		int xl2 = l2.getFila();
		int yl2 = l2.getColumna();
		
		int xr2 = r2.getFila();
		int yr2 = r2.getColumna();
		
		//Checkeamos que ningun rectangulo sea una linea
		if(xl1 == xr1 || yl1 == yr1 || xl2 == xr2 || yl2 == yr2)
			ret = false;
		
		//Checkeamos si un rectangulo esta a la izquierda de otro
		if(ret && (xl1 >= xr2 || xl2 >= xr1))
			ret = false;
		
		//Checkeamos si un rectangulo esta por encima del otro
		if(ret && (yr1 >= yl2 || yr2 >= yl1))
			ret = false;
		
		return ret;
	}
	
	public int hashCode() {
		return ((Integer) f).hashCode() + ((Integer) c).hashCode();
	}
	
	public boolean equals(Object o) {
		try {
			Position p = (Position) o;
			return equals(p);
		} catch(ClassCastException ex) {
			return false;
		}
	}
	
}
