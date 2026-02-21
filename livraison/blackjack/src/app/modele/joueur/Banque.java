package app.modele.joueur;

import app.modele.Action;
import app.modele.Blackjack;
import app.modele.Jeu;

/**
 * Class singleton Banque
 */
public class Banque extends Joueur {
	private static Banque CROUPIER = new Banque();

	/**
	 * Constructeur de la classe Banque
	 */
	private Banque() {
		super(0);
	}
	
	/**
	 * @return l'instance unique de Banque
	 */
	public static Banque GetBanque() {
		return Banque.CROUPIER;
	}
	
	// Fonctions: 

	@Override
	public Action choisirAction(Jeu jeu) {
		return Blackjack.valeurDeLaMain(this.main) < 17 ? Action.TIRER : Action.RESTER;
	}
	
	@Override
	public boolean choisirPlace(Blackjack jeu) {
		System.err.println("la banque ne joue pas contre elle même (Banque)");
		return false;

	}

	@Override
	public boolean placerMise(Blackjack jeu) {
		System.err.println("la banque ne joue pas contre elle même (Banque)");
		return false;
	}
	
	// representation
	
	@Override
	public String toString() {
		return "  |solde: " + this.solde + "\n  |main: " + this.main;
	}
	
}
