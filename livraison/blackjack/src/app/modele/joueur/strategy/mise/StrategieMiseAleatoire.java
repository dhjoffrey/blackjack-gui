package app.modele.joueur.strategy.mise;

import java.util.Random;

import app.modele.Blackjack;
import app.modele.joueur.JoueurRobot;
import app.modele.joueur.strategy.StrategieMise;

public class StrategieMiseAleatoire implements StrategieMise {

	@Override
	public int choisirMise(Blackjack jeu, JoueurRobot bot) {
		Random decideur = new Random();
		
		// genere une mise aleatoire entre la borne miseMin et miseMax du BlackJack

		if(bot.getSolde() > jeu.getMiseMin()) {
			return jeu.getMiseMin() + decideur.nextInt( jeu.getMiseMax() - jeu.getMiseMin() );
		}
		
		if(bot.getSolde() == jeu.getMiseMin()) {
			return bot.getSolde();
		}

		return 0;
	}

}
