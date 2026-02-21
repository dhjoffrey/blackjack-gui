package app.vue;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JLayeredPane;

import app.modele.Paquet;
import app.xutil.observeur.EcouteurModele;

public abstract class VuePaquet extends JLayeredPane implements EcouteurModele {
	private static final long serialVersionUID = -1780511019585379739L;
	
	public static Set<VuePaquet> instances = new HashSet<VuePaquet>();
	//private static final int DECALE_AXE_VALUE = 0 ;
	
	protected Paquet paquet;
	protected boolean enEventaille;
	
	public VuePaquet(Paquet paquet, boolean enEventaille) {
		this.paquet = paquet;
		this.enEventaille = enEventaille;
		
		this.paquet.ajoutEcouteur(this);
		majNaif();
		
		instances.add(this);
		
	}

	public abstract void majNaif();

	@Override
	public void modeleMisAJour(Object source) {
		majNaif();
		this.revalidate();
		this.repaint();
	}
	
	// GETs
	
	public Paquet getPaquet() {
		return paquet;
	}

	public boolean isEnEventaille() {
		return enEventaille;
	}
	
}
