package app.vue;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import app.controleur.ControleurJouer;
import app.modele.Blackjack;
import app.modele.joueur.Joueur;
import app.modele.joueur.JoueurHumain;
import app.xutil.observeur.EcouteurModele;

public class VueEmplacement extends JPanel implements EcouteurModele {
	private static final long serialVersionUID = -4895789585328153627L;
	
	VuePaquetVisible main;
	JLabel solde, mise, valeurMain;
	VueJouer vueJouer;

	Joueur joueur;
	
	/**
	 * Constructeur de la classe VueEmplacement
	 * @param joueur le joueur assis a cette place
	 */
	public VueEmplacement(Joueur joueur) {
		this.joueur = joueur;
		
		if(joueur != null) { // si un joueur est installé a cette chaise (prêt à jouer)
			joueur.ajoutEcouteur(this);
			joueur.getMain().ajoutEcouteur(this);
			
			main = new VuePaquetVisible(joueur.getMain(), true);
			this.add(main);
			
			valeurMain = new JLabel("valeur: " + Blackjack.valeurDeLaMain(joueur.getMain()));
			this.add(valeurMain);
			
			solde = new JLabel("solde: " + joueur.getSolde());
			this.add(solde);
			
			mise = new JLabel("mise: " + joueur.getMise());
			this.add(mise);
			
			VueJouer vj;
			if(joueur instanceof JoueurHumain) {
				vj = new VueJouer((JoueurHumain)joueur);
				this.add(vj);
				this.vueJouer = vj; 
			}
		}
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(new Color(209,232,169));
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Mise a jour de notre du JPanel courant
	 */
	public void maj() {
		valeurMain.setText("valeur: " + Blackjack.valeurDeLaMain(joueur.getMain()));
		solde.setText("solde: " + joueur.getSolde());
		mise.setText("mise: " + joueur.getMise());
	}
	
	@Override
	public void modeleMisAJour(Object source) {
		maj();
		this.revalidate();
		this.repaint();
	}

	// GET
	
	public Joueur getJoueur() {
		return joueur;
	}

	public VueJouer getVueJouer() {
		return vueJouer;
	}
}
