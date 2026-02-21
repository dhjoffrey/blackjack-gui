package app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import app.controleur.ControleurChoixCarteVersPaquet;
import app.controleur.ControleurPiocheVersPaquet;
import app.modele.Paquet;
import app.vue.*;

public class GuiJoueur extends JFrame {
	private static final long serialVersionUID = 7020981638428071201L;

	public GuiJoueur(Paquet pioche, Paquet defausse, Paquet  main) {
		super("jeu de " + pioche.getTaille() + " cartes");
		
		this.setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
		this.getContentPane().setBackground(Color.blue);
		
		VuePaquetCache piocheVue = new VuePaquetCache(pioche, true);
		//VuePaquetCache piocheVue = new VuePaquetCache(pioche, false);
		VuePaquetVisible mainVue = new VuePaquetVisible(main, true);
		VuePaquetVisible defausseVue = new VuePaquetVisible(defausse, false);

		ControleurChoixCarteVersPaquet mainControle = new ControleurChoixCarteVersPaquet(mainVue, defausse);
		ControleurPiocheVersPaquet piocheControle = new ControleurPiocheVersPaquet(piocheVue, main);
		
		add(piocheVue);
		add(mainVue);
		add(defausseVue);
		
		
		JButton bt_shuffle = new JButton("melanger pioche");
		bt_shuffle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pioche.melanger();
				System.out.println("melange le paquet (pioche) ...");
				
			}
			
		});
		
		JButton bt_vider = new JButton("vider defausse");
		bt_vider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				defausse.viderCartesVers(pioche);
				System.out.println("vide la defausse (vers la pioche) ...");
				
			}
			
		});
		
		this.add(bt_shuffle);
		this.add(bt_vider);
		
		this.pack();
		//this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);

		this.setBounds(100, 100, 1000, 500);
		this.getContentPane().setBackground(new Color(209,232,169));
		this.setVisible(true);
	}
	
}
