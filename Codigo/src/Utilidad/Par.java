package Utilidad;

public class Par<A, B> {
	protected A a;
	protected B b;
	
	public Par(A aa, B bb) {
		a = aa;
		b = bb;
	}
	
	public A getPrimeraComponente() {
		return a;
	}
	
	public void setPrimeraComponente(A aa) {
		a = aa;
	}
	
	public B getSegundaComponente() {
		return b;
	}
	
	public void setSegundaComponente(B bb) {
		b = bb;
	}
}
