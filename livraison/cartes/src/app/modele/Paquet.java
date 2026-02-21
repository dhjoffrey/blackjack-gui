package app.modele;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import app.xutil.observeur.AbstractModeleEcoutable;

public class Paquet extends AbstractModeleEcoutable {

	protected LinkedList<Carte> cartes;// voir la derniere carte du paquet comme la premiere a piocher

	/**
	 * Constructeur avec parametre de la classe Carte
	 * @param cartes une liste de cartes
	 */
	public Paquet(LinkedList<Carte> cartes) {
		this.cartes = cartes;
		fireChangement();
	}

	/**
	 * Constructeur par defaut de la classe Carte
	 */
	public Paquet() {
		this(new LinkedList<Carte>());
	}

	// FONCTIONS DE BASE

	/**
	 * Procedure permettant d'ajouter une carte par le dessus
	 * @param carte une carte
	 */
	public void ajouter_au_dessus(Carte carte) {
		this.cartes.addLast(carte);
		fireChangement();
	}

	/**
	 * Procedure permettant d'ajouter une carte par le dessous
	 * @param carte une carte
	 */
	public void ajouter_en_dessous(Carte carte) {
		this.cartes.addFirst(carte);
		fireChangement();
	}

	/**
	 * Procedure permettant de melanger les cartes du paquet
	 */
	public void melanger() {
		Collections.shuffle(this.cartes);
		fireChangement();
	}

	/**
	 * Procedure permettant de couper un paquet de carte en 2 (aleatoirement)
	 */
	public void couper() {
		
		if(getTaille() > 2) {
			// tire un nombre aleatoire parmis tous les index des cartes dans le paquet, 
			// exclu de la premiere (0) et la derniere (nb de cartes - 2) | (sinon on ne couperai rien)
			int index = 1 + new Random().nextInt(getTaille() - 2);
			
			// optimisable (parcour inverse si index haut)
			for(int i = 0 ; i < index ; i++) {
				this.cartes.addLast(this.cartes.removeFirst());
			}
			fireChangement();
			
		}else if(getTaille() == 2) {
			this.cartes.addLast(this.cartes.removeFirst());
			fireChangement();
		}
		
	}

	/**
	 * @return la carte piochee
	 */
	public Carte retirer(int index) {
		Carte derniere = this.cartes.remove(index);
		fireChangement();

		return derniere;
	}
	

	public Carte retirer(Carte retCarte) {
		int i = 0;
		for(Carte carte : this.cartes) {
			if(carte == retCarte) {
				return retirer(i);
			}
			i++;
		}
		System.err.println("???");
		return null;
		
	}
	
	/**
	 * @return la carte piochee
	 */
	public Carte retirerPremiere() {
		return retirer(getTaille() - 1);
	}

	// UTILES
	
	@Override
	public String toString() {
		String str = "|";
		
		for(Carte carte : this.cartes) {
			str += carte.toString()+ "|";
		}
		
		return str;
	}

	/**
	 * Fonction statique permettant la creation d'un paquet de 52 cartes
	 * 
	 * @return un paquet de 52 cartes
	 */
	public static Paquet creerPaquet52() {
		Paquet paq = new Paquet();

		for (Hauteur h : Hauteur.values()) {
			for (Couleur c : Couleur.values()) {
				paq.getCartes().add(new Carte(h, c));
			}
		}

		return paq;
	}
	
	public void echangerCartes(Paquet paquet) {
		LinkedList<Carte> tmp = this.getCartes();
		this.setCartes(paquet.getCartes());
		paquet.setCartes(tmp);
		
	}
	
	private void setCartes(LinkedList<Carte> cartes){
		this.cartes = cartes;
		fireChangement();
	}
	
	public void viderCartesVers(Paquet paquet) {
		if(paquet.getTaille() == 0) {
			echangerCartes(paquet);
		}else {
			int nbCartes = this.getTaille();
			for(int i = 0 ; i < nbCartes ; i++) {
				paquet.ajouter_en_dessous(this.retirerPremiere());
			}
			
		}
	}

	/**
	 * @return le nombre de carte(s) dans le paquet
	 */
	public int getTaille() {
		return this.cartes.size();
	}

	// GET n SET

	public LinkedList<Carte> getCartes() {
		return cartes;
	}

}
