package app.vue;

import app.modele.Paquet;

public class VuePaquetCache extends VuePaquet {
	private static final long serialVersionUID = -2406455453654306289L;

	private static final double DECALE_AXE_X_VALUE = 1.6;

	private static final double DECALE_AXE_Y_VALUE = 0;

	public VuePaquetCache(Paquet paquet, boolean enEventaill) {
		super(paquet, enEventaill);
		
		//int largeur = VueCarte.WIDTH + (int) (DECALE_AXE_X_VALUE * paquet.getTaille());
		//int longueur = VueCarte.HEIGHT + (int) (DECALE_AXE_Y_VALUE * paquet.getTaille());
		//System.err.println(longueur + " - " + largeur);
		//this.setSize(largeur, longueur);
		//this.setPreferredSize(new Dimension(largeur,longueur));
	
	}

	@Override
	public void majNaif() {
		this.removeAll();
		
		for(int i = paquet.getTaille()-1 ; i >= 0 ; i--) {
			this.add(new VueCarte(paquet.getCartes().get(i), false));
		}
		
		
		double ecartX = this.enEventaille ? DECALE_AXE_X_VALUE : (int) (VueCarte.WIDTH * 1.1);
		double ecartY = this.enEventaille ? DECALE_AXE_Y_VALUE : 0;
		
		int X;
		int Y;
		
		
		int nbVues = this.getComponents().length;
		int indexMax = nbVues - 1;
		
		for(int index = 0 ; index < nbVues ; index += 1){
			X = (int) (index * ecartX);
			Y = (int) (index * ecartY);
			this.getComponent(indexMax-index).setBounds(X,Y, VueCarte.WIDTH, VueCarte.HEIGHT);
		}
		
	}
}
