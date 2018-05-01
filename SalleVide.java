package Salle;

import Entite.Joueur;
import handler.*;

public class SalleVide extends Salle{
	Action a;
	Donjon d;

	//constructeur
	public SalleVide(int x, int y, Joueur j, Donjon d) {
		super(x, y, "/", j, d);
		this.d = d;
	}

	//methode
	@Override
	public void jouerSalle() {
		a = new Action(d,this);
		a.actionJoueur();
	}

}
