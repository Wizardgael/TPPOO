package Salle;

import java.util.ArrayList;
import java.util.Random;

import Entite.Joueur;

public class Piege {
	
	private int dmg;
	String nom;
	ArrayList<String> listNom = new ArrayList<String>();
	
	public Piege(int dmg) {
		this.dmg = dmg;
		Random r = new Random();
		listNom.add("le piege de feu");
		listNom.add("la chambre des lames");
		listNom.add("la fosse");
		nom = listNom.get(r.nextInt(3));
	}
	
	public String getNom() {
		return nom;
	}
	
	public void activation(Joueur j) {
		Random r = new Random();
		if(r.nextInt(100) > 45) {
			j.setHpPlus(j.getHpPlus() - dmg);
			System.out.println("Vous n'avez pas réussi a désactiver le piège");
			System.out.println(j);
		}else {
			System.out.println("Le piège a été désamorcé");
		}
	}

}
