package app.modele.joueur;

import app.modele.*;
import app.xutil.observeur.AbstractModeleEcoutable;

public abstract class Joueur extends AbstractModeleEcoutable {
	protected int solde;
	protected Paquet main;
	protected int mise;
	protected int coefGain;
	
	/**
	 * Constructeur de la classe Joueur
	 * ce joueur represente le slot d'un joueur à une table de BlackJack
	 * @param solde l'agrent que le joueur dispose pour jouer
	 * @param main un paquet de carte representant son jeu de cartes
	 * @param mise l'argent que joueur mise sur une partie
	 * @param coefGain le multiplicateur de gain sur sa mise s'il gagne
	 */
	private Joueur(int solde, Paquet main, int mise, int coefGain) {
		this.solde = solde;
		this.main = main;
		this.mise = mise;
		this.coefGain = coefGain;
	}
	
	/**
	 * Constructeur de la classe Joueur
	 * @param solde l'agrent que le joueur dispose pour jouer
	 */
	public Joueur(int solde) {
		this(solde, new Paquet(), 0, 2);
	}
	
	/**
	 * @param jeu la partie en cours
	 * @return l'action a effectuer
	 */
	public abstract Action choisirAction(Jeu jeu);
	
	/**
	 * @param jeu la partie en cours
	 * @return oui ou non si le joueur est parvenu a se placer
	 */
	public abstract boolean choisirPlace(Blackjack jeu);
	
	/**
	 * @param jeu la partie en cours
	 * @return oui ou non si le joueur est parvenu a inscrire sa mise
	 */
	public abstract boolean placerMise(Blackjack jeu);

	/**
	 * Retire la mise posee par le joueur
	 * @return le montant de la mise
	 */
	public int unsetMise() {
		int montant = this.mise;
		this.mise = 0;

		fireChangement();
		
		return montant;
	}
	
	/**
	 * Procedure ajoutant le montant au solde du joueur
	 * @param montant l'argent a crediter
	 */
	public void crediter(int montant) {
		this.solde += montant;
		
		fireChangement();
	}
	
	/**
	 * Procedure soustrayant le montant au solde du joueur
	 * @param montant l'argent a debiter
	 */
	public void debiter(int montant) {
		this.solde -= montant;
		
		fireChangement();
	}
	
	// GETs
	
	public int getSolde() {
		return solde;
	}

	public Paquet getMain() {
		return main;
	}

	public int getMise() {
		return mise;
	}

	public float getCoefGain() {
		return coefGain;
	}
	
	// representation
	
	@Override
	public String toString() {
		return "  |solde: " + this.solde + "\n  |mise: " + this.mise + "\n  |coefGain: " + this.coefGain + "\n  |main: " + this.main;
	}
	
}
