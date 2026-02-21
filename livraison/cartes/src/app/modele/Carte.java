package app.modele;

public class Carte {
	protected Hauteur hauteur;
	protected Couleur couleur;
	
	/**
	 * Constructeur de la classe Carte
	 * @param hauteur hauteur de la carte
	 * @param couleur couleur de la carte
	 */
	public Carte(Hauteur hauteur, Couleur couleur) {
		this.hauteur=hauteur;
		this.couleur=couleur;
	}
	
	@Override
	public String toString() {
		String str = "";
		/*
		for(int i = 0 ; i < 6 - this.hauteur.name().length() ; i++) {
			str += " ";// espace pour aligner
		}*/
		
		str += this.couleur.toString() + this.hauteur.toString();
		/*
		for(int i = 0 ; i < 7 - this.couleur.name().length() ; i++) {
			str += " ";// espace pour aligner
		}*/
		
		str += "";
		
		return str;
	}
	
	// GET n SET
	
	public Hauteur getHauteur() {
		return hauteur;
	}

	public Couleur getCouleur() {
		return couleur;
	}
}
