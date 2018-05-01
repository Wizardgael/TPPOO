package Salle;

import Entite.Joueur;
import Equipement.Coffre;
import handler.Action;

public class SalleCoffre extends Salle{
	
	Coffre c;
	Action a;
	Donjon d;

	//constructeur
	public SalleCoffre(int x, int y, Joueur j, Donjon d) {
		super(x, y, "/", j, d);
		c = new Coffre();
		this.d = d;
	}
	
	//getter and setter
	public Coffre getCoffre() {
		return c;
	}
	
	//methode
	@Override
	public void jouerSalle() {
		a = new Action(d,this);
		a.actionJoueur();
	}
	


}
