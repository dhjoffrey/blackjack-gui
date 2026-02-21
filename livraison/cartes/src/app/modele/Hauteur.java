package app.modele;

public enum Hauteur {
	DEUX("2"), 
	TROIS("3"), 
	QUATRE("4"), 
	CINQ("5"), 
	SIX("6"), 
	SEPT("7"), 
	HUIT("8"), 
	NEUF("9"), 
	DIX("X"), 
	
	VALET("J"), 
	DAME("Q"), 
	ROI("K"),
	
	AS("A");
	
	protected String rpz;
	
	private Hauteur(String rpz) {
		this.rpz = rpz;
	}
	
	@Override
	public String toString() {
		return this.rpz;
	}
}
