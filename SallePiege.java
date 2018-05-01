package Salle;

import java.util.Random;
import Entite.Joueur;
import handler.Action;

public class SallePiege extends Salle{
	
	Action a;
	Donjon d;
	Piege p;

	//constructeur
	public SallePiege(int x, int y, Joueur j, Donjon d) {
		super(x, y, "/", j, d);
		Random r = new Random();
		p = new Piege(15 + r.nextInt(20));
		this.d = d;
	}
	
	//getter and setter
	public Piege getPiege() {
		return p;
	}	
	
	
	//methode
	@Override
	public void jouerSalle() {
		a = new Action(d,this);
		a.actionJoueur();
	}

}
