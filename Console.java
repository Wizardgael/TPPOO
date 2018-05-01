package handler;

import java.io.IOException;
import Salle.*;
import Equipement.Equipement;

public class Console {

	String nom;

	private Donjon donjon;

	

	String choix;
	Salle salleActuel;

	public Equipement equipement;

	//constructeur
	public Console(String nom) {
		this.nom = nom;
		donjon = new Donjon(12, 6, this, nom);
	}

	
	//getters and setters
	public Donjon getDonjon() {
		return donjon;
	}
	
	public void setDonjon(Donjon donjon) {
		this.donjon = donjon;
	}
	
	//

	//methode
	public void start() throws IOException {
		while (true) {
			if (gameOver()) {
				//System.out.println("Vous etes mort !");
				System.out.println("\r\n" + 
						"  _____ ____ _   _ _____ ____ \r\n" + 
						" | ____/ ___| | | | ____/ ___|\r\n" + 
						" |  _|| |   | |_| |  _|| |    \r\n" + 
						" | |__| |___|  _  | |__| |___ \r\n" + 
						" |_____\\____|_| |_|_____\\____|\r\n" + 
						"                              \r\n" + 
						"");
				do {
					System.out.println("Nouvelle Partie ?(o/n)");
					choix = Clavier.lireTexte();
				} while (!choix.equals("o") && !choix.equals("n"));
				if (choix.equals("o")) {
					donjon = new Donjon(8, 4, this, nom);
				} else {
					System.exit(1);
				}
			} else {
				int x = donjon.getJoueur().getX();
				int y = donjon.getJoueur().getY();
				salleActuel = donjon.getSalle(x, y);
				if (salleActuel.premierPassage()) {
					donjon.genererDonjon(salleActuel.getX(), salleActuel.getY(), salleActuel);
					donjon.testPorte();
					donjon.getJoueur().setFin(donjon.getJoueur().getFin() + 1);
				}
				salleActuel.jouerSalle();
			}
		}

	}

	public boolean gameOver() {
		if (donjon.getJoueur().getHpPlus() <= 0)
			return true;
		else
			return false;
	}
}
