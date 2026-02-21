package app.modele.joueur.strategy;

import app.modele.Action;
import app.modele.Jeu;
import app.modele.joueur.JoueurRobot;
import app.modele.joueur.strategy.action.StrategieActionAleatoire;

public interface StrategieAction {
	/**
	 * @param jeu la partie en cours
	 * @param bot le joueur
	 * @return l'Action decidee par la strategie
	 */
	public abstract Action choisirAction(Jeu jeu, JoueurRobot bot);
}
