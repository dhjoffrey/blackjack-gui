package app.modele.joueur;

import java.util.Scanner;

import app.modele.Action;
import app.modele.Blackjack;
import app.modele.Jeu;

public class JoueurHumain extends Joueur {
	protected String nom;
	protected Action lastAction;
	
	/**
	 * Constructeur de la classe JoueurHumain
	 * @param solde le montant dont le joueur dispose pour jouer
	 * @param nom le nom du joueur
	 */
	public JoueurHumain(int solde, String nom) {
		super(solde);
		this.nom = nom;
		this.lastAction = null;
	}

	@Override
	public Action choisirAction(Jeu jeu) {
		//return Action.RESTER;
		Action actionTmp;
		while(this.lastAction == null) {
			try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
			if(lastAction != null) {
				actionTmp = lastAction;
				lastAction = null;
				return actionTmp;
			}
		}
		
		try { throw new Exception("impossible de ne pas trouver d'action"); } catch (Exception e) {}
		
		return null;
		
		/*
		String c;
		Action a = null;
		//Ce booleen permet de controler si le choix correspond à une action
		boolean continueIfException;
		
		Scanner scanner;
		
		do{
			System.out.println("Choisissez une action: t (TIRER) et r (RESTER)");
			scanner = new Scanner(System.in);
			continueIfException = false;

			c = scanner.nextLine();
			
			switch(c) {
			case "t":
				a = Action.TIRER;
				break;
			case "r":
				a = Action.RESTER;
				break;
			default:
				continueIfException = true;
				System.out.println("vous ne pouvez jouer qu'avec t (TIRER) et r (RESTER)");
				
			}
		}while(continueIfException);
		
		//scanner.close();
		
		return a;*/
	}

	@Override
	public boolean choisirPlace(Blackjack jeu) {

		fireChangement();
		return false;
	}

	@Override
	public boolean placerMise(Blackjack jeu) {
		this.mise = 200;
		this.solde -= this.mise;
		
		fireChangement();
		return true;
	}
	
	// GET SET

	public Action getLastAction() {
		return lastAction;
	}

	public void setLastAction(Action lastAction) {
		this.lastAction = lastAction;
	}


}
