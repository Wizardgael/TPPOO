package Salle;

import Entite.Joueur;
import handler.Action;
import handler.Console;

public class SalleFin extends Salle{
	
	Console c;
	Action a;
	
	//constructeur
	public SalleFin(int x, int y, Joueur j, Donjon d, Console c) {
		super(x, y, "/", j, d);
		this.c = c;
		a = new Action(d,this);
	}

	//methode
	@Override
	public void jouerSalle() {
		a.jouerFin(c);
	}

}
