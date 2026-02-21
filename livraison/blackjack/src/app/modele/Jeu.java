package app.modele;

import java.util.List;
import java.util.Set;

import app.modele.joueur.Joueur;
import app.xutil.observeur.AbstractModeleEcoutable;

public abstract class Jeu extends AbstractModeleEcoutable {
	
	// METHODES CONSTITUANTS LE PATTERN TEMPLATE

	/**
	 * Initialisation de la partie
	 */
	abstract void initialisation();
	
	/**
	 * Deroulement d'une partie
	 */
	abstract void deroulement();
	
	/**
	 * Finalisation d'une partie
	 */
	abstract void finalisation();
	
	/**
	 * Template method
	 */
	public final void commencerUnePartie(){
		initialisation();
		deroulement();
		finalisation();
	}
	
	// AUTRES METHODES
	
	/**
	 * @return un ensemble d'actions possibles dans un jeu
	 */
	public abstract Set<Action> getActions();

	/**
	 * @param joueur le joueur courant
	 * @return un ensemble d'action disponible pour le joueur courant
	 */
	public abstract Set<Action> getActionsValides(Joueur joueur);

	/**
	 * procedure permettant d'appliquer une action à realiser sur une partie en cours
	 * @param joueur le joueur courant
	 * @param action une action valide
	 */
	public abstract void jouer(Joueur joueur, Action action);

	/**
	 * @return une liste de joueurs ordonée en fonction des tours de jeu (qui joue avant qui)
	 */
	public abstract List<Joueur> getJoueurs();
	
	/**
	 * @return le joueur qui va devoir jouer son tour de jeu
	 */
	public abstract Joueur getJoueurCourant();
	
}
