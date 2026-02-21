package app.vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.modele.Blackjack;
import app.modele.joueur.Joueur;

public class VueBlackjack extends JPanel {
	private static final long serialVersionUID = -6431832965602406471L;
	
	protected List<VueEmplacement> vuesEmplacements;

	/**
	 * Constructeur de la classe VueBlackjack
	 * @param jeu une partie de blackjack
	 */
	public VueBlackjack(Blackjack jeu) {
		this.vuesEmplacements = new ArrayList<VueEmplacement>();
		
		VuePaquet vueSabot = new VuePaquetCache(jeu.getSabot(), true);
		VuePaquet vueDefausse = new VuePaquetCache(jeu.getDefausse(), true);
		
		JPanel ligne1 = new JPanel();
		JPanel ligne2 = new JPanel();
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		
		ligne1.setLayout(new BoxLayout(ligne1,BoxLayout.X_AXIS));
		ligne1.setBackground(new Color(209,232,169));
		
		ligne1.add(vueDefausse);
		ligne1.add(new VueEmplacement(jeu.getCroupier()));
		ligne1.add(vueSabot);
		
		
		ligne2.setLayout(new GridLayout());
		
		VueEmplacement vueEmplacement;
		for(int i = 0 ; i<jeu.getEmplacements().size() ; i++) {
			vueEmplacement = new VueEmplacement(jeu.getEmplacements().get(i));
			ligne2.add(vueEmplacement);
			this.vuesEmplacements.add(vueEmplacement);
		}
		
		this.add(ligne1);
		this.add(ligne2);
	}

	// GET
	
	public List<VueEmplacement> getVuesEmplacements() {
		return vuesEmplacements;
	}

}
