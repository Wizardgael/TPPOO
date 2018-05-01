package Entite;

import java.util.ArrayList;
import java.util.Random;

public class Monstre extends Personnage {
	private String nom;
	ArrayList<String> listNom = new ArrayList<String>();

	public Monstre(int hp, int dmg) {
		super(hp, dmg);
		listNom.add("Gobelin");
		listNom.add("Slime");
		listNom.add("Minotaure");
		Random r = new Random();
		this.nom = listNom.get(r.nextInt(3));
	}

	// Getter and setter
	public String getNom() {
		return nom;
	}

	// methode
	@Override
	public String toString() {
		return nom + ": " + this.getHp() + "/" + this.getMaxHp();
	}

	public void attaque(Joueur p) {
		Random r = new Random();
		message("attaque " + p.getNom());
		if (r.nextInt(100) <= 10) {
			p.setHpPlus(p.getHpPlus() - (super.getDmg() * 2));
			System.out.println("COUP CRITIQUE!");
		} else
			p.setHpPlus(p.getHpPlus() - super.getDmg());
		System.out.println(p + "\n");
	}

	public void message(String txt) {
		System.out.println(nom + ": " + txt);
	}

}
