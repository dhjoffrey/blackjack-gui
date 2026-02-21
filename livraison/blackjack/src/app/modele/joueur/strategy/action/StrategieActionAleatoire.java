package app.modele.joueur.strategy.action;

import java.util.Random;
import java.util.Set;

import app.modele.Action;
import app.modele.Jeu;
import app.modele.joueur.JoueurRobot;
import app.modele.joueur.strategy.StrategieAction;

public class StrategieActionAleatoire implements StrategieAction {

	Random decideur = new Random();
	
	@Override
	public Action choisirAction(Jeu jeu, JoueurRobot bot) {
		Set<Action> actionsValides = jeu.getActionsValides(bot);

		int indexAleatoire = this.decideur.nextInt(actionsValides.size());
		int index = 0;

		for(Action action : actionsValides) {
			if(indexAleatoire == index) {
				return action;
			}
			index++;
		}
		
		return null;
	}

}
