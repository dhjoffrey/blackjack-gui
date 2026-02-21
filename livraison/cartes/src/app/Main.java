package app;

import app.modele.Paquet;

public class Main {

	public static void main(String[] args) {
		/*
		Paquet paquet = Paquet.creerPaquet52();
		
		for(Carte c : paquet.getCartes()) {
			System.out.println(c.toString());
		}
		
		paquet.couper();
		System.out.println(paquet.getTaille());
		
		paquet.melanger();
		
		for(Carte c : paquet.getCartes()) {
			System.out.println(c.toString());
		}*/
		
		Paquet pioche = Paquet.creerPaquet52();
		Paquet defausse = new Paquet();
		Paquet main1 = new Paquet();
		//Paquet main2 = new Paquet();
		
		new GuiJoueur(pioche, defausse, main1);
		new GuiAdmin(pioche);
		
		
		System.out.println(pioche);
	}
	
}
