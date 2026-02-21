package app;

import java.util.Arrays;
import java.util.LinkedList;

import app.modele.*;
import app.modele.joueur.*;

public class Main {

	/**
	 * fonction executable permettant de jouer au Blackjack
	 * @param args tableau d'arguments non utilisé
	 */
	public static void main(String[] args) {
		
		int nbPartie = 6;
		
		// DEFINITION DES JOUEURS: 
		
		Joueur bot1 = new JoueurRobotBuilder().avecSolde(5000).avecStratMiseAleatoire().avecStratActionAleatoire().build();
		Joueur bot2 = new JoueurRobotBuilder().avecSolde(4500).avecStratMiseMax().avecStratActionFixeTIRER().build();
		Joueur bot3 = new JoueurRobotBuilder().avecSolde(5500).avecStratMiseMin().avecStratActionFixeTIRER().build();
		Joueur bot4 = new JoueurRobotBuilder().avecSolde(4000).avecStratMiseMax().avecStratActionFixeRESTER().build();
		Joueur bot5 = new JoueurRobotBuilder().avecSolde(6000).avecStratMiseMax().avecStratActionFixeRESTER().build();
		Joueur bot6 = new JoueurRobotBuilder().avecSolde(3500).avecStratMiseAleatoire().avecStratActionSelonValeur(16).build();
		Joueur bot7 = new JoueurRobotBuilder().avecSolde(6500).avecStratMiseAleatoire().avecStratActionSelonValeur(18).build();
		
		LinkedList<Joueur> bots = new LinkedList<Joueur>(Arrays.asList(bot1,bot2,bot3,bot4,bot5,bot6,bot7));
		
		// DEFINITION D'UNE PARTIE
		
		Blackjack partie = new BlackjackBuilder().avecMiseMin(100).avecMiseMax(500).avecEmplacements(7).avecSabot(4).build();
		
		for(int i = 0; i<7; i++) {
			partie.placerJoueur(bots.pop(), i);
		}
		
		Joueur joueur = new JoueurHumain(23000, "Nakajoai");
		
		partie.retirerJoueur(bot3);
		partie.placerJoueur(joueur, 2);
		
		partie.retirerJoueur(bot5);
		// LANCEMENT DE LA GUI
		
		new GuiBJ(partie);// lancement de l'interface graphique
		
		
		// ENCHAINEMENT DES PARTIES
		
		for(int u = 0 ; u < nbPartie ; u++) {
			try { Thread.sleep(1500); } catch (InterruptedException e) {} // pause de x millisecondes
			System.out.println(partie); // histoire d'avoir une representation en console en parrallele
			
			partie.commencerUnePartie();// tout se passe ici au niveau du modèle
		}
		

	}

}
