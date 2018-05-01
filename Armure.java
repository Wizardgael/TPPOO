package Equipement;

import java.util.ArrayList;
import java.util.Random;


public class Armure extends Equipement {

	ArrayList<String> listeNom = new ArrayList<String>();

	private int hpBonus;
	private int prix;
	
	//Constructeur

	public Armure(int hpBonus, TypeObject id) {
		super("text", id);
		this.hpBonus = hpBonus;
		listeNom.add("fer");
		listeNom.add("cuir");
		listeNom.add("or");
		listeNom.add("diamant");

		Random r = new Random();
		super.setNom(listeNom.get(r.nextInt(3)));
	}
	
	public Armure(String nom,int hpBonus, TypeObject id) {
		super(nom, id);
		this.hpBonus = hpBonus;
	}

	//getters and setters
	public int getHpBonus() {
		return hpBonus;
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
		if (super.getId() == TypeObject.Bouclier)
			return "Vous obtenez 1 bouclier en " + super.getNom();
		if (super.getId() == TypeObject.Casque)
			return "Vous obtenez 1 casque en " + super.getNom();
		if (super.getId() == TypeObject.Plastron)
			return "Vous obtenez 1 plastron en " + super.getNom();
		if (super.getId() == TypeObject.Jambiere)
			return "Vous obtenez 1 jambière en " + super.getNom();
		if (super.getId() == TypeObject.Botte)
			return "Vous obtenez 1 paire de bottes en " + super.getNom();
		return null;
	}

	@Override
	public String stat() {
		return " +" + hpBonus + " Point de vie";
	}

	@Override
	public String toString() {
		if (super.getId() == TypeObject.Bouclier)
			return "Bouclier en " + super.getNom() + stat();
		if (super.getId() == TypeObject.Casque)
			return "Casque en " + super.getNom() + stat();
		if (super.getId() == TypeObject.Plastron)
			return "Plastron en " + super.getNom() + stat();
		if (super.getId() == TypeObject.Jambiere)
			return "Jambière en " + super.getNom() + stat();
		if (super.getId() == TypeObject.Botte)
			return "Paire de bottes en " + super.getNom() + stat();
		return null;
	}

}
