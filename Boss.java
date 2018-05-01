package Entite;

import java.util.ArrayList;
import java.util.Random;

public class Boss extends Monstre {
	private String nom;
	ArrayList<String> listNom = new ArrayList<String>();
	int tour;

	public Boss(int hp, int dmg) {
		super(hp, dmg);
		this.nom = "Dragon";
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
		if (tour == 5) {
			message("crache un torrent de flamme sur " + p.getNom());
			p.setHpPlus(p.getHpPlus() - (super.getDmg() * 4));
			tour = 1;
		} else {
			message("attaque " + p.getNom());
			tour++;
			if (r.nextInt(100) <= 30) {
				p.setHpPlus(p.getHpPlus() - (super.getDmg() * 2));
				System.out.println("COUP CRITIQUE!");
			} else
				p.setHpPlus(p.getHpPlus() - super.getDmg());
			System.out.println(p + "\n");
		}
	}

	public void message(String txt) {
		System.out.println(nom + ": " + txt);
	}

}
