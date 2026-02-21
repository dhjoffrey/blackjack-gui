package app.controleur;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import app.modele.Carte;
import app.modele.Paquet;
import app.vue.VueCarte;
import app.vue.VuePaquetVisible;
import app.xutil.observeur.EcouteurModele;

public class ControleurChoixCarteVersPaquet implements EcouteurModele {

	protected VuePaquetVisible vueSource;
	protected Paquet destination;

	public ControleurChoixCarteVersPaquet(VuePaquetVisible vueSource, Paquet destination) {
		this.vueSource = vueSource;
		this.destination = destination;
		
		vueSource.getPaquet().ajoutEcouteur(this);
		ajouterMousesListeners();
	}
	
	public void ajouterMousesListeners() {
		for(Component vueCarte : vueSource.getComponents()) {
				
			vueCarte.addMouseListener(new MouseListener() { 
	
				public void mouseClicked(MouseEvent e) {
					Carte carte = vueSource.getPaquet().retirer(((VueCarte) vueCarte).getCarte());
					destination.ajouter_en_dessous(carte);
					System.out.println("ajoute la carte " + carte + " de la main vers la defausse ...");
					
				}
	
				public void mouseEntered(MouseEvent e) { 
					vueCarte.setBounds(vueCarte.getX(), 0, vueCarte.getWidth(), vueCarte.getHeight());
					
				} 
	
				public void mouseExited(MouseEvent e) { 
					vueCarte.setBounds(vueCarte.getX(), VuePaquetVisible.DECALE_AXE_VALUE, vueCarte.getWidth(), vueCarte.getHeight());
				} 
	
				public void mousePressed(MouseEvent e) {} 
	
				public void mouseReleased(MouseEvent e) {} 
	
			});
		}
	}

	@Override
	public void modeleMisAJour(Object source) {
		ajouterMousesListeners();
		
	}

	
}
