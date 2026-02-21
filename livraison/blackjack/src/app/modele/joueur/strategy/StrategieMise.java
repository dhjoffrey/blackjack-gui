package app.modele.joueur.strategy;

import app.modele.Blackjack;
import app.modele.joueur.JoueurRobot;

public interface StrategieMise {
	/**
	 * @param jeu la partie en cours
	 * @param bot le joueur
	 * @return le montant de la mise
	 */
	public abstract int choisirMise(Blackjack jeu, JoueurRobot bot);
}
