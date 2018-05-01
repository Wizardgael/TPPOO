package Salle;

import java.util.Random;

import Entite.Joueur;
import Equipement.*;
import handler.Action;
import handler.Clavier;

public class Shop extends Salle {
	Arme item1;
	Armure item2;
	Armure item3;
	int prixPotion = 5;
	Action a;
	Donjon d;
	boolean achat1 = false, achat2 = false, achat3 = false;

	public Shop(int x, int y, Joueur j, Donjon d) {
		super(x, y, "/", j, d);
		this.d = d;
		Random r = new Random();
		item1 = new Arme(3 + r.nextInt(3));
		item1.setPrix(10 + r.nextInt(10));
		item2 = new Armure(10 + r.nextInt(20), Clavier.randomElement());
		item2.setPrix(10 + r.nextInt(10));
		item3 = new Armure(10 + r.nextInt(20), Clavier.randomElement());
		item3.setPrix(10 + r.nextInt(10));
	}
	
	

	public boolean isAchat1() {
		return achat1;
	}

	public void setAchat1(boolean achat1) {
		this.achat1 = achat1;
	}

	public boolean isAchat2() {
		return achat2;
	}

	public void setAchat2(boolean achat2) {
		this.achat2 = achat2;
	}

	public boolean isAchat3() {
		return achat3;
	}

	public void setAchat3(boolean achat3) {
		this.achat3 = achat3;
	}

	public Arme getItem1() {
		return item1;
	}

	public Armure getItem2() {
		return item2;
	}

	public Armure getItem3() {
		return item3;
	}

	public int getPrixPotion() {
		return prixPotion;
	}

	@Override
	public void jouerSalle() {
		a = new Action(d,this);
		a.actionJoueur();
	}

}
