package app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import app.modele.Paquet;
import app.vue.VuePaquetVisible;

public class GuiAdmin extends JFrame {
	private static final long serialVersionUID = 7012843464779677978L;

	public GuiAdmin(Paquet pioche) {
		super("pioche a ne pas montrer aux joueurs");
		
		this.setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));

		VuePaquetVisible piocheVue = new VuePaquetVisible(pioche, true);

		add(piocheVue);
		
		JButton bt_shuffle = new JButton("melanger");
		bt_shuffle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pioche.melanger();
				System.out.println("melange le paquet (pioche) ...");
				
			}
			
		});
		
		JButton bt_vider = new JButton("couper");
		bt_vider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pioche.couper();
				System.out.println("coupe en 2 le paquet (picohe) ...");
			}
			
		});
		
		this.add(bt_shuffle);
		this.add(bt_vider);
		
		this.pack();
		//this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);

		this.setBounds(100, 600, 1000, 300);
		this.getContentPane().setBackground(new Color(109,132,169));
		this.setVisible(true);
	}
}
