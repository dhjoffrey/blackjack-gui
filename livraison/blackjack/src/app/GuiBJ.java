package app;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import app.controleur.ControleurJouer;
import app.modele.Blackjack;
import app.modele.joueur.JoueurHumain;
import app.vue.VueBlackjack;
import app.vue.VueEmplacement;

public class GuiBJ extends JFrame {
	private static final long serialVersionUID = -1077043180450546004L;

	/**
	 * Constructeur de la classe GuiBJ permettant la creation d'une fenetre graphique pour jouer au Blackjack
	 * @param jeu la partie a jouer
	 */
	public GuiBJ(Blackjack jeu) {
		super("jeu");
		
		VueBlackjack vb = new VueBlackjack(jeu);
		
		this.add(vb);
		
		ControleurJouer cj;
		for(VueEmplacement ve : vb.getVuesEmplacements()) {
			if(ve.getJoueur() instanceof JoueurHumain) {
				cj = new ControleurJouer(ve.getVueJouer(), jeu);
			}
			
		}
		
		this.pack();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(50,50,(int) (screenSize.width * 0.8), (int) (screenSize.height * 0.8));
		this.setVisible(true);
	}
}
