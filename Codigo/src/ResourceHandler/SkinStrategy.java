package ResourceHandler;

public interface SkinStrategy {
	//Pacman:
	public String getPacmanNCI();
	public String getPacmanSCI();
	public String getPacmanECI();
	public String getPacmanOCI();
	
	//Fantasmas:
	public String getFantasmaRojoCI();
	public String getFantasmaRosaCI();
	public String getFantasmaAzulCI();
	public String getFantasmaNaranjaCI();
	public String getFantasmaVulnerableCI();
	
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
}
