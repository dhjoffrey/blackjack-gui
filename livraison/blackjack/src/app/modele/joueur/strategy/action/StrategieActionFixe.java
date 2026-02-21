package app.modele.joueur.strategy.action;

import app.modele.Action;
import app.modele.Jeu;
import app.modele.joueur.JoueurRobot;
import app.modele.joueur.strategy.StrategieAction;

public class StrategieActionFixe implements StrategieAction {
	private Action action;
	
	/**
	 * Constructeur de la classe StrategieActionFixe
	 * cette strategie permet de jouer tout le temps la meme action
	 * donc jusqu'a etre bust ou avoir 21 au BlackJack
	 * @param action l'action jouee en boucle
	 */
	public StrategieActionFixe(Action action) {
		this.action = action;
	}
	
	@Override
	public Action choisirAction(Jeu jeu, JoueurRobot bot) {
		return this.action;
	}

}
