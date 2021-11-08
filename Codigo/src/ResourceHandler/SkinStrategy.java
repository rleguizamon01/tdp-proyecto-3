package ResourceHandler;

public interface SkinStrategy {
	//Pacman:
	public String getPacmanNCI();
	public String getPacmanSCI();
	public String getPacmanECI();
	public String getPacmanOCI();
	
	//Fantasmas:
	public String getFantasmaRojoDCI();
	public String getFantasmaRojoICI();
	
	public String getFantasmaRosaDCI();
	public String getFantasmaRosaICI();
	
	public String getFantasmaAzulDCI();
	public String getFantasmaAzulICI();
	
	public String getFantasmaNaranjaDCI();
	public String getFantasmaNaranjaICI();
	
	public String getFantasmaVulnerableDCI();
	public String getFantasmaVulnerableICI();
	
	public String getFantasmaMuertoDCI();
	public String getFantasmaMuertoICI();
	
	//Mapa:
	public String getParedCI();
	public String getPisoCI();
	
	//Entidades Fijas
	public String getMonedaCI();
	public String getPowerPelletCI();
	public String getFrutaCI();
	public String getPocionVelocidadCI();
	public String getPocionBombaCI();
	public String getBombaCI();
	
	//Miscelaneo
	public String getEfectoVelocidadCI();
	public String getEfectoPowerPelletCI();
	public String getEfectoBombaCI();
}
