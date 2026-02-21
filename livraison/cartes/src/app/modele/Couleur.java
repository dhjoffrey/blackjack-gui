package app.modele;

public enum Couleur {
	TREFLE("♣"), 
	PIQUE("♠"), 
	CARREAU("♦"), 
	COEUR("♥"); 
	
	protected String rpz;
	
	private Couleur(String rpz) {
		this.rpz = rpz;
	}
	
	@Override
	public String toString() {
		return this.rpz;
	}
}
