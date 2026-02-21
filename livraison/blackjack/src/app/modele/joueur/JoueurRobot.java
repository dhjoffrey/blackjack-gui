package app.modele.joueur;

import java.util.Random;
import java.util.Set;

import app.modele.Action;
import app.modele.Blackjack;
import app.modele.Jeu;
import app.modele.joueur.strategy.StrategieAction;
import app.modele.joueur.strategy.StrategieMise;
import app.modele.joueur.strategy.action.StrategieActionAleatoire;
import app.modele.joueur.strategy.mise.StrategieMiseAleatoire;

public class JoueurRobot extends Joueur {

	protected StrategieMise sMise;
	protected StrategieAction sAction;

	/**
	 * Constructeur de la classe JoueurRobot
	 * un robot joue automatiquement selon une strategie que l'on lui specifie
	 * a la fois pour choisir une mise, que pour choisir une action lorsqu'il doit jouer
	 * @param solde l'argent dont dispose le robot pour miser
	 * @param sMise la strategie qui dit combien le robot va miser
	 * @param sAction la strategie qui dit qu'est ce que le robot va choisir comme action pour jouer un tour
	 */
	public JoueurRobot(int solde, StrategieMise sMise, StrategieAction sAction) {
		super(solde);
		this.sMise = sMise;
		this.sAction = sAction;
	}
	
	/**
	 * Constructeur par défaut de la classe JoueurRobot
	 * par defaut, un robot suivera une strategie basée sur l'aleatoire
	 */
	public JoueurRobot(int solde) {
		this(solde, new StrategieMiseAleatoire(), new StrategieActionAleatoire());
	}
	
	@Override
	public Action choisirAction(Jeu jeu) {
		Action choixAction = this.sAction.choisirAction(jeu, this);

		if(choixAction == null) {
			try {
				throw new Exception("un joueur n'est pas sensé choisir une action s'il en a aucune de disponible");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return choixAction;
	}

	@Override
	public boolean choisirPlace(Blackjack jeu) {
		Random decideur = new Random();
		
		Set<Integer> placesDispos = jeu.getPlacesDisponibles();
		int nbPlacesDispos = placesDispos.size();

		if(nbPlacesDispos > 1) {// S'il y a plusieurs choix de places ou s'installer
			int indexAleatoire = decideur.nextInt(nbPlacesDispos);
			jeu.placerJoueur(this, indexAleatoire);

		}else if(nbPlacesDispos == 1) {//S'il n'y a qu'une seule place de dispo
			jeu.placerJoueur(this, 0);

		}else {// S'il n'y a aucune place dispo
			System.err.println("choisirPlace(): impossible de placer le joueur");
			return false;
		}
		
		fireChangement();
		
		return true;
	}

	@Override
	public boolean placerMise(Blackjack jeu) {
		
		// Reset au cas ou: 
		this.solde += this.mise;
		this.mise = 0;
		
		// Choix de mise
		this.mise = this.sMise.choisirMise(jeu, this); // on demande a la strategie de choisir une valeur
		this.solde -= this.mise;
		
		fireChangement();
		
		return this.mise > 0;
	}


}
