package Salle;

import java.util.Random;

import Entite.Joueur;
import Entite.Monstre;
import handler.Action;

public class SalleMonstre extends Salle{
	
	Monstre m;
	Action a;
	Donjon d;
	boolean fuite;

	//constructeur
	public SalleMonstre(int x, int y, Joueur j, Donjon d) {
		super(x, y, "/", j, d);
		Random r = new Random();
		m = new Monstre(30 + r.nextInt(50 - 30 ), 3 + r.nextInt(6 - 3));
		this.d = d;
	}
	
	//getters and setters
	
	public Monstre getMonstre() {
		return m;
	}

	public boolean Fuite() {
		return fuite;
	}
	
	public void setFuite(boolean fuite) {
		this.fuite = fuite;
	}
	
	//methode
	@Override
	public void jouerSalle() {
		a = new Action(d,this);
		a.actionJoueur();
	}
	
}
