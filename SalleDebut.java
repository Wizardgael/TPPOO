package Salle;

import Entite.Joueur;
import handler.*;

public class SalleDebut extends Salle{
	Action a;
	Donjon d;

	//constructeur
	public SalleDebut(int x, int y, Joueur j, Donjon d) {
		super(x, y, "D", j, d);
		this.d = d;
	}
	
	
	//methode
	@Override
	public void jouerSalle() {
		a = new Action(d,this);
		a.actionJoueur();
	}

}
