package app.modele.joueur;

import app.modele.Action;
import app.modele.joueur.strategy.StrategieAction;
import app.modele.joueur.strategy.StrategieMise;
import app.modele.joueur.strategy.action.StrategieActionAleatoire;
import app.modele.joueur.strategy.action.StrategieActionFixe;
import app.modele.joueur.strategy.action.StrategieActionSelonValeur;
import app.modele.joueur.strategy.mise.StrategieMiseAleatoire;
import app.modele.joueur.strategy.mise.StrategieMiseMax;
import app.modele.joueur.strategy.mise.StrategieMiseMin;

public class JoueurRobotBuilder {
	private int solde;
	private StrategieMise sMise;
	private StrategieAction sAction;
	
	/**
	 * Constructeur par défaut de la classe JoueurRobotBuilder
	 */
	public JoueurRobotBuilder() {
		this.solde = 0;
		this.sMise = null;
		this.sAction = null;
	}
	
	/**
	 * @param montant l'argent dont dispose le robot pour miser
	 * @return l'instance courante de JoueurRobotBuilder
	 */
	public JoueurRobotBuilder avecSolde(int montant) {
		this.solde = montant;
		return this;
	}
	
	// pour les Strategies sur les Actions: 
	
	public JoueurRobotBuilder avecStratActionAleatoire() {
		this.sAction = new StrategieActionAleatoire();
		return this;
	}
	
	public JoueurRobotBuilder avecStratActionFixeTIRER() {
		this.sAction = new StrategieActionFixe(Action.TIRER);
		return this;
	}
	
	public JoueurRobotBuilder avecStratActionFixeRESTER() {
		this.sAction = new StrategieActionFixe(Action.RESTER);
		return this;
	}
	
	/**
	 * @param valeur valeur selon laquel le bot va decider l'action TIRER ou RESTER
	 * @return instance de JoueurRobotBuilder
	 * @see StrategieActionSelonValeur
	 */
	public JoueurRobotBuilder avecStratActionSelonValeur(int valeur) {
		this.sAction = new StrategieActionSelonValeur(valeur);
		return this;
	}
	
	// pour les Strategies sur les Mises: 
	
	public JoueurRobotBuilder avecStratMiseAleatoire() {
		this.sMise = new StrategieMiseMax();
		return this;
	}
	
	public JoueurRobotBuilder avecStratMiseMin() {
		this.sMise = new StrategieMiseMin();
		return this;
	}
	
	public JoueurRobotBuilder avecStratMiseMax() {
		this.sMise = new StrategieMiseAleatoire();
		return this;
	}
	
	// CONSTRUCTION
	
	/**
	 * @return l'instance de JoueurRobot construite
	 */
	public JoueurRobot build() {
		return new JoueurRobot(this.solde, this.sMise, this.sAction);
	}
}
