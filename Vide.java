package Salle;

import Entite.Joueur;

public class Vide extends Salle{

	//constructeur
	public Vide(int x, int y, Joueur j, Donjon d) {
		super(x, y, " ", j, d);
	}

	//methode
	@Override
	public void jouerSalle() {
		
	}

}
