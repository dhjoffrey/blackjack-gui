package app.controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import app.modele.Carte;
import app.modele.Paquet;
import app.vue.VuePaquet;

public class ControleurPiocheVersPaquet {
	
	public ControleurPiocheVersPaquet(VuePaquet vueSource, Paquet destination) {
		
		vueSource.addMouseListener(new MouseListener() { 
			
			public void mouseClicked(MouseEvent e) {
				if(vueSource.getPaquet().getTaille() > 0) {
					Carte carte = vueSource.getPaquet().retirerPremiere();
					destination.ajouter_en_dessous(carte);
					System.out.println("tire la premiere carte de la pioche vers la main (" + carte + ") ...");
					
				}else {
					System.out.println("il n'y a plus de carte dans le paquet");
				}
				
			}

			public void mouseEntered(MouseEvent e) { 
				//vueCarte.setBounds(vueCarte.getX(), 0, vueCarte.getWidth(), vueCarte.getHeight());
				
			} 

			public void mouseExited(MouseEvent e) { 
				//vueCarte.setBounds(vueCarte.getX(), VuePaquetVisible.DECALE_AXE_VALUE, vueCarte.getWidth(), vueCarte.getHeight());
			} 

			public void mousePressed(MouseEvent e) {} 

			public void mouseReleased(MouseEvent e) {} 

		});
	}
	
}
