package Salle;

import Entite.Boss;
import Entite.Joueur;
import handler.Action;

public class SalleBoss extends Salle{
	
	Boss b;
	Action a;
	Donjon d;
	boolean fuite;

	//constructeur
	public SalleBoss(int x, int y, Joueur j, Donjon d) {
		super(x, y, "/", j, d);
		b = new Boss(130,7);
		this.d = d;
	}
	
	//getters and setters
	
	public Boss getBoss() {
		return b;
	}
	
	//methode
	@Override
	public void jouerSalle() {
		a = new Action(d,this);
		a.actionJoueur();
	}
	
}
