package app.vue;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import app.controleur.ControleurJouer;
import app.modele.joueur.JoueurHumain;

public class VueJouer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JButton tirer, rester;
	JoueurHumain joueur;
	
	/**
	 * Constructeur de la class VueJouer
	 * Represente les actions qu'un joueur humain doit choisir quand il joue
	 * @param joueur le joueur concerné par ces boutons
	 */
	public VueJouer(JoueurHumain joueur) {
		this.joueur = joueur;
		
		this.setLayout(new GridLayout());
		
		tirer = new JButton("tirer");
		rester = new JButton("rester");
		this.add(rester);
		this.add(tirer);
		
		tirer.setEnabled(false);
		rester.setEnabled(false);
		
		
	}
	
	// GET

	public JButton getTirer() {
		return tirer;
	}

	public JButton getRester() {
		return rester;
	}

	public JoueurHumain getJoueur() {
		return joueur;
	}

}
