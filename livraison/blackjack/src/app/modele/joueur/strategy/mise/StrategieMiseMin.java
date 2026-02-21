package app.modele.joueur.strategy.mise;

import app.modele.Blackjack;
import app.modele.joueur.JoueurRobot;
import app.modele.joueur.strategy.StrategieMise;

public class StrategieMiseMin implements StrategieMise {

	@Override
	public int choisirMise(Blackjack jeu, JoueurRobot bot) {
		if(jeu.getMiseMin() <= bot.getSolde()) {
			return jeu.getMiseMin();
		}
		
		return 0;
	}

}
