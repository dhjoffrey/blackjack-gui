package app.modele;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.modele.Carte;
import app.modele.Paquet;
import app.modele.joueur.*;

public class Blackjack extends Jeu {
	protected int miseMin, miseMax;
	protected Paquet sabot, defausse;
	protected Banque croupier;
	protected Map<Integer,Joueur> emplacements;

	private LinkedList<Joueur> joueurs;
	private Joueur joueurCourant;

	/**
	 * Constructeur de la classe Blackjack
	 * @param miseMin le montant minimum pour pouvoir s'installer a la table
	 * @param miseMax le montant maximum pour pouvoir s'installer a la table
	 * @param sabot paquet de cartes
	 * @param defausse paquet de cartes
	 * @param croupier le joueur banque
	 * @param emplacements les emplacements possibles pour les joueurs a la table
	 */
	public Blackjack(int miseMin, int miseMax, Paquet sabot, Paquet defausse, Banque croupier, Map<Integer, Joueur> emplacements) {
		this.miseMin = miseMin;
		this.miseMax = miseMax;
		this.sabot = sabot;
		this.defausse = defausse;
		this.croupier = croupier;
		this.emplacements = emplacements;
	}

	@Override
	void initialisation() {
		this.joueurs = new LinkedList<Joueur>();
		Joueur joueurTmp;

		// Construction de la liste des joueurs a partir des joueurs installés sur les emplacements
		for(Map.Entry<Integer, Joueur> emplacement : this.emplacements.entrySet()) {

			joueurTmp = emplacement.getValue();

			if(joueurTmp != null) {
				joueurs.addFirst(joueurTmp);
			}
		}
		
		// A condition qu'il y ait au moins 1 joueur d'installé a la table
		if(!joueurs.isEmpty()) {
			// on demande à chaque joueur de placer une mise
			for(Joueur joueur : joueurs) {
				joueur.placerMise(this);
				
				// Si le joueur ne peut pas miser, il doit partir de la table
				if(joueur.getMise() == 0) {
					this.retirerJoueur(joueur);
				}
			}
			
		}
		
		this.defausse.viderCartesVers(this.sabot);
		this.sabot.melanger();
		
	}

	@Override
	void deroulement() {

		if(!joueurs.isEmpty()) {
			
			// DISTRIBUTION DES CARTES

			for(int i = 1 ; i <= 2 ; i++) {
				for(Joueur joueur : joueurs) {// i eme carte pour tout le monde
					joueur.getMain().ajouter_au_dessus(this.sabot.retirerPremiere());
				}
				
				if(i == 1) {
					this.croupier.getMain().ajouter_au_dessus(this.sabot.retirerPremiere());// carte du croupier
				}
				
			}
			
			// LE TOUR DE JEU COMMENCE
			
			Action lastAction;
			for(Joueur joueurCourant : joueurs) {
				this.joueurCourant = joueurCourant;
				fireChangement();
				lastAction = null;

				// tant que le joueur n'a pas decidé de rester ou qu'il n'est pas busted
				while(lastAction != Action.RESTER && valeurDeLaMain(joueurCourant.getMain()) < 21) {
					
					/*if(joueurCourant instanceof JoueurHumain){ // juste de l'affichage en console pour un joueur humain
						System.out.println("croupier: " + croupier.getMain() + " (" + valeurDeLaMain(croupier.getMain()) + ")");
						System.out.println("joueurHumain: " + joueurCourant.getMain() + " (" + valeurDeLaMain(joueurCourant.getMain()) + ")");
					}*/
					
					// choisir et jouer une action
					lastAction = joueurCourant.choisirAction(this);
					jouer(joueurCourant, lastAction);
				}

				if(valeurDeLaMain(joueurCourant.getMain()) > 21) {// joueur bust
					joueurCourant.getMain().viderCartesVers(this.defausse);
				}

			}

			lastAction = null;
			joueurCourant = this.croupier;

			while(lastAction != Action.RESTER && valeurDeLaMain(joueurCourant.getMain()) < 21) {
				lastAction = joueurCourant.choisirAction(this);
				jouer(joueurCourant, lastAction);
				joueurCourant.fireChangement();
			}

		}

	}

	@Override
	void finalisation() {
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {}
		
		int valeurCroupier = valeurDeLaMain(croupier.getMain());
		int valeurJoueur;
		int montantMise;
		int montantBenef;
		
		// Gestion des gains/pertes
		for(Joueur joueur : joueurs) {

			valeurJoueur = valeurDeLaMain(joueur.getMain());
			montantMise = joueur.unsetMise();
			montantBenef = (int)(joueur.getCoefGain() * montantMise) - montantMise;

			if(valeurCroupier <= 21) {
				if(valeurCroupier < valeurJoueur) {
					croupier.debiter(montantBenef);
					joueur.crediter(montantMise + montantBenef);
				}else if(valeurCroupier > valeurJoueur){
					croupier.crediter(montantMise);
				}else {// egalité
					joueur.crediter(montantMise);
				}

			}else {// bust
				if(valeurJoueur != 0) {
					croupier.debiter(montantBenef);
					joueur.crediter(montantMise + montantBenef);
				}else {
					croupier.crediter(montantMise);
				}

			}
			// on met les cartes de la main vers la defausse
			joueur.getMain().viderCartesVers(this.defausse);

		}
		// on met les cartes du coupier dans la defausse aussi
		croupier.getMain().viderCartesVers(this.defausse);

	}

	@Override
	public Set<Action> getActions() {
		return new HashSet<Action>(Arrays.asList(Action.values()));
	}

	@Override
	public Set<Action> getActionsValides(Joueur joueur) {
		Set<Action> actions = new HashSet<Action>();

		int valeurDeLaMain = valeurDeLaMain(joueur.getMain());

		// Si le joueur n'a pas de carte (busted)
		if(valeurDeLaMain > 0) {

			// Si le joueur a une valeur inferieur a 21, alors il peut tirer des cartes
			if(valeurDeLaMain < 21) {
				actions.add(Action.TIRER);
			}

			// Un joueur a toujours le droit de conserver sa main
			actions.add(Action.RESTER);
		}

		return actions;
	}

	@Override
	public void jouer(Joueur joueur, Action action) {
		if(action == Action.TIRER) {
			joueur.getMain().ajouter_au_dessus(this.sabot.retirerPremiere());
		}
		
		fireChangement();
	}
	
	// INITIALISATION HELPER

	/**
	 * @param joueur le joueur qui veut jouer
	 * @param posEmplacement l'index de l'emplacement
	 * @return Vrai ou faux si le joueur a pu se placer
	 */
	public boolean placerJoueur(Joueur joueur, int posEmplacement) {
		if(this.emplacements.containsValue(joueur)) {
			System.err.println("placerJoueur(): tentaive de placer un joueur deja participant au jeu");
			return false;
		}
		if(!getPlacesDisponibles().contains(posEmplacement)) {
			System.err.println("placerJoueur(): impossible de placer un joueur");
			return false;
		}

		this.emplacements.put(posEmplacement, joueur);

		fireChangement();

		return true;
	}

	/**
	 * @param joueur le joueur
	 * @return vrai ou faux si le joueur est retiré
	 */
	public boolean retirerJoueur(Joueur joueur) {
		for(Map.Entry<Integer, Joueur> emplacement : this.emplacements.entrySet()) {
			if(joueur == emplacement.getValue()) {
				emplacement.setValue(null);

				fireChangement();

				return true;
			}
		}

		return false;
	}

	/**
	 * @return l'ensemble des emplacements disponibles
	 */
	public Set<Integer> getPlacesDisponibles(){
		Set<Integer> dispos = new HashSet<Integer>();

		for(Map.Entry<Integer, Joueur> emplacement : this.emplacements.entrySet()) {
			if(emplacement.getValue() == null) {
				dispos.add(emplacement.getKey());
			}
		}

		return dispos;
	}
	
	// STATIC METHOD

	/**
	 * @return la valeur que represente un paquet de carte (la main d'un joueur)
	 */
	public static int valeurDeLaMain(Paquet main) {
		int total = 0;
		int as = 0;

		for(Carte carte : main.getCartes()) {

			switch(carte.getHauteur()) 
			{
			case DEUX: total += 2; break;
			case TROIS: total += 3; break;
			case QUATRE: total += 4; break;
			case CINQ: total += 5; break;
			case SIX: total += 6; break;
			case SEPT: total += 7; break;
			case HUIT: total += 8; break;
			case NEUF: total += 9; break;
			case DIX: total += 10; break;
			case VALET: total += 10; break;
			case DAME: total += 10; break;
			case ROI: total += 10; break;
			case AS: as += 1; break;
			default: break;
			}

		}

		for(int i = 0 ; i < as ; i++) {
			total += total > 10 ? 1 : 11;
		}

		return total;
	}

	// GETs

	@Override
	public List<Joueur> getJoueurs() {
		return this.joueurs;
	}

	@Override
	public Joueur getJoueurCourant() {
		return this.joueurCourant;
	}

	public int getMiseMin() {
		return miseMin;
	}

	public int getMiseMax() {
		return miseMax;
	}

	public Paquet getSabot() {
		return sabot;
	}

	public Paquet getDefausse() {
		return defausse;
	}

	public Banque getCroupier() {
		return croupier;
	}

	public Map<Integer, Joueur> getEmplacements() {
		return emplacements;
	}

	// Representation en console: 

	@Override
	public String toString() {
		String rpz = "#########################\n";

		rpz += "Croupier: \n" + this.croupier + "\n  |valeur de la main: " + valeurDeLaMain(this.croupier.getMain()) + "\n\n";

		Joueur joueurTmp;

		for(int i = 0 ; i < this.emplacements.size() ; i++) {

			rpz += "Joueur " + (i+1) + ": \n";

			joueurTmp = this.emplacements.get(i);

			if(joueurTmp != null) {
				rpz += joueurTmp + "\n  |valeur de la main: " + valeurDeLaMain(joueurTmp.getMain()) + "\n";
			}

			rpz += "\n";
		}

		return rpz;
	}

}
