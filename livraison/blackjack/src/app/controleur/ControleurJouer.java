package app.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import app.modele.Action;
import app.modele.Blackjack;
import app.vue.VueJouer;
import app.xutil.observeur.EcouteurModele;

public class ControleurJouer implements EcouteurModele {

	protected VueJouer vueJouer;
	protected Blackjack partie;

	/**
	 * Constructeur de la classe ControleurJouer
	 * consiste a donner la possiblité au joueur de choisir l'action qu'il veut effectué sur son tour de jeu
	 * @param vueJouer la vue permettant de selectionner une action pour un joueur precis
	 * @param partie la partie en cours neccessaire afin de savoir qui est le joueur courant
	 */
	public ControleurJouer(VueJouer vueJouer, Blackjack partie) {
		this.vueJouer = vueJouer;
		this.partie = partie;

		partie.ajoutEcouteur(this);

		vueJouer.getTirer().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				vueJouer.getJoueur().setLastAction(Action.TIRER);
			}
		});

		vueJouer.getRester().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				vueJouer.getJoueur().setLastAction(Action.RESTER);
			}
		});

	}

	@Override
	public void modeleMisAJour(Object source) {
		if(this.partie.getJoueurCourant() == this.vueJouer.getJoueur()) {
			vueJouer.getTirer().setEnabled(true);
			vueJouer.getRester().setEnabled(true);
		}else {
			vueJouer.getTirer().setEnabled(false);
			vueJouer.getRester().setEnabled(false);
		}
	}
}
