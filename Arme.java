package Equipement;

import java.util.ArrayList;
import java.util.Random;

public class Arme extends Equipement {

	ArrayList<String> listeNom = new ArrayList<String>();

	private int dmg;
	private int prix;

	//constructeur
	public Arme(int dmg) {
		super("text", TypeObject.Arme);
		this.dmg = dmg;
		listeNom.add("épée");
		listeNom.add("hache");
		listeNom.add("dague");
		listeNom.add("fléau");

		Random r = new Random();
		super.setNom(listeNom.get(r.nextInt(3 - 0 + 1)));
	}
	
	public Arme(int dmg,String nom) {
		super(nom, TypeObject.Arme);
		this.dmg = dmg;
		listeNom.add("épée");
		listeNom.add("hache");
		listeNom.add("dague");
		listeNom.add("fléau");

		Random r = new Random();
		super.setNom(listeNom.get(r.nextInt(3 - 0 + 1)));
	}
	//getter and setters
	
	public int getDmg() {
		return dmg;
	}
	
	public int getPrix() {
		return prix;
	}
	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	//methode

	@Override
	public String msg() {
		return "Vous obtenez 1 " + super.getNom();
	}

	public String stat() {
		return " +" + dmg + " de dégats";
	}

	@Override
	public String toString() {
		return super.getNom() + stat();
	}

}
