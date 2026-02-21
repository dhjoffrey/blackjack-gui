package app.modele;

import java.util.HashMap;
import java.util.Map;

import app.modele.joueur.*;

/**
 * Class de construction de Blackjack
 */
public class BlackjackBuilder {
	private int miseMin, miseMax;
	private Paquet sabot, defausse;
	private Banque croupier;
	private Map<Integer,Joueur> emplacements;

	/**
	 * Constructeur de la classe BlackjackBuilder
	 */
	public BlackjackBuilder() {
		this.miseMin = 0;
		this.miseMax = 0;
		this.sabot = null;
		this.defausse = new Paquet();
		this.croupier = Banque.GetBanque();
		this.emplacements = null;
	}

	public BlackjackBuilder avecMiseMin(int montant) {
		this.miseMin = montant;
		return this;
	}

	public BlackjackBuilder avecMiseMax(int montant) {
		this.miseMax = montant;
		return this;
	}

	public BlackjackBuilder avecSabot(int nbPaquet) {
		this.sabot = genereSabot(4);
		return this;
	}

	public BlackjackBuilder avecEmplacements(int nbEmplacements) {
		this.emplacements = genereEmplacements(nbEmplacements);
		return this;
	}
	
	/**
	 * Construction des emplaçant a partir d'un ensemble de joueurs pré-définis
	 * @param joueurs l'ensemble des joueurs
	 * @return instance courante
	 */
	public BlackjackBuilder avecJoueurs(Joueur...joueurs) {
		this.emplacements = genereEmplacements(joueurs.length);
		
		for(int i = 0 ; i < joueurs.length ; i++) {
			this.emplacements.put(i, joueurs[i]);
		}
		
		return this;
	}

	/**
	 * @return La nouvelle instance de Blackjack construite
	 */
	public Blackjack build() {
		return new Blackjack(miseMin, miseMax, sabot, defausse, croupier, emplacements);
	}

	/**
	 * Il doit y avoir au minimum 1 emplacements sur une table de BlackJack
	 * @param nbEmplacement le nombre de joueurs pouvant s'installer à une table
	 * @return une map representant ces emplacements
	 */
	private static Map<Integer,Joueur> genereEmplacements(int nbEmplacement) {
		if(nbEmplacement < 1) {
			nbEmplacement = 1;
		}

		Map<Integer,Joueur> emplacements = new HashMap<Integer,Joueur>();

		for(int i = 0 ; i < nbEmplacement ; i++) {
			emplacements.put(i, null);
		}

		return emplacements;
	}

	/**
	 * Le sabot contient au minimum 1 paquet de 52 cartes
	 * @param nbPaquet le nombre de paquets que doit contenir le sabot
	 * @return un paquet composé de qte paquets
	 */
	private static Paquet genereSabot(int nbPaquet) {
		if(nbPaquet < 1) {
			nbPaquet = 1;
		}

		Paquet sabot = new Paquet();

		for(int i = 0 ; i < nbPaquet ; i++) {
			sabot.getCartes().addAll(Paquet.creerPaquet52().getCartes());
		}

		return sabot;
	}
}
