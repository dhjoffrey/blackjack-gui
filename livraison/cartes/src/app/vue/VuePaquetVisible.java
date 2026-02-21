package app.vue;

import java.awt.Component;

import app.modele.Carte;
import app.modele.Paquet;

public class VuePaquetVisible extends VuePaquet {
	private static final long serialVersionUID = 527633465472232275L;
	
	public static final int DECALE_AXE_VALUE = 15;
	
	public VuePaquetVisible(Paquet paquet, boolean enEventaill) {
		super(paquet, enEventaill);
		
	}
	
	
	@Override
	public void majNaif() {
		this.removeAll();
		int i = 0;
		int valEcart = this.enEventaille ? DECALE_AXE_VALUE : (int) (VueCarte.WIDTH * 1.1);
		
		for(Carte carte : paquet.getCartes()) {
			this.add(new VueCarte(carte, true));
		}
		
		for(Component compVue : this.getComponents()) {
			compVue.setBounds(i * valEcart,DECALE_AXE_VALUE, VueCarte.WIDTH, VueCarte.HEIGHT);
			i++;
		}
	}
	


}
