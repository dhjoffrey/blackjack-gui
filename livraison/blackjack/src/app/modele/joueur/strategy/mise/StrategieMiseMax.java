package app.modele.joueur.strategy.mise;

import app.modele.Blackjack;
import app.modele.joueur.JoueurRobot;
import app.modele.joueur.strategy.StrategieMise;

public class StrategieMiseMax implements StrategieMise {

	@Override
	public int choisirMise(Blackjack jeu, JoueurRobot bot) {
		if(jeu.getMiseMax() <= bot.getSolde()) {
			return jeu.getMiseMax();
		}
		
		return 0;
	}

}
