package app.modele.joueur.strategy.action;

import app.modele.Action;
import app.modele.Blackjack;
import app.modele.Jeu;
import app.modele.joueur.JoueurRobot;
import app.modele.joueur.strategy.StrategieAction;

public class StrategieActionSelonValeur implements StrategieAction {

	private int val;
	
	/**
	 * Constructeur de la classe StrategieActionSelonValeur
	 * cette strategie permet de dire si on continue a tirer ou non une carte selon une valeur
	 * @param val la valeur maximum ou l'on continue a tirer une carte
	 */
	public StrategieActionSelonValeur(int val) {
		this.val = val;
	}
	
	@Override
	public Action choisirAction(Jeu jeu, JoueurRobot bot) {
		return (Blackjack.valeurDeLaMain(bot.getMain()) <= val) ? Action.TIRER : Action.RESTER;
	}

}
