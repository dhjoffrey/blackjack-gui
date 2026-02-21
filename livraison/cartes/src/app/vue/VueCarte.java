package app.vue;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import app.modele.Carte;

public class VueCarte extends JLabel {
	private static final long serialVersionUID = 2880389816651446654L;
	public static final int WIDTH = 60;
	public static final int HEIGHT = 100;
	
	protected Carte carte;
	protected boolean voir;

	public VueCarte(Carte carte, boolean voir) {
		super("<html>"+carte.getCouleur()+"<br>"+carte.getHauteur()+"</html>");
		this.carte = carte;
		
		if(voir){
			this.setBackground(Color.white);
		}else {
			this.setBackground(Color.DARK_GRAY);
		}
		
		this.setHorizontalAlignment(SwingConstants.RIGHT);
		this.setVerticalAlignment(SwingConstants.TOP);
		
		this.setSize(WIDTH, HEIGHT);
		this.setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

	}
	
	public void retourner() {
		voir = !voir;
		if(voir) {
			this.setBackground(Color.white);
		}else {
			this.setBackground(Color.black);
		}
	}
	
	public Carte getCarte() {
		return carte;
	}
	
	public boolean getVoir() {
		return voir;
	}



}
