package Entite;

import java.util.ArrayList;
import java.util.Random;

import Equipement.*;
import handler.Clavier;

public class Joueur extends Personnage {

	private int x, y;
	private String nom;
	private int nbPotion = 1;
	private int nbCle = 0;
	private int dmgBonus;
	private int hpBonus;
	private int hpPlus;
	private Arme arme;
	private ArrayList<Armure> listArmure = new ArrayList<Armure>();
	private int or = 0;
	private int fin = 0;

	public Joueur(int x, int y, int hp, int dmg, String nom) {
		super(hp, dmg);
		this.x = x;
		this.y = y;
		hpPlus = hp;
		hpBonus = hp;
		dmgBonus = dmg;
		this.nom = nom;
		arme = new Arme(0);
		listArmure.add(new Armure("cuir déchiré", 0, TypeObject.Casque));
		listArmure.add(new Armure("cuir déchiré", 0, TypeObject.Plastron));
		listArmure.add(new Armure("cuir déchiré", 0, TypeObject.Jambiere));
		listArmure.add(new Armure("cuir déchiré", 0, TypeObject.Botte));
		listArmure.add(new Armure("bois moisi", 0, TypeObject.Bouclier));

	}

	// getter and setter
	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getFin() {
		return fin;
	}
	public String getNom() {
		return nom;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getOr() {
		return or;
	}

	public void setOr(int or) {
		this.or = or;
	}

	public int getPotion() {
		return nbPotion;
	}

	public void setPotion(int nbPotion) {
		this.nbPotion = nbPotion;
	}

	// methode
	public void deplacementNord() {
		y -= 1;
	}

	public void deplacementSud() {
		y += 1;
	}

	public void deplacementEst() {
		x += 1;
	}

	public void deplacementOuest() {
		x -= 1;
	}

	public void message(String txt) {
		System.out.println(nom + ": " + txt);
	}

	@Override
	public String toString() {
		return nom + ": " + hpPlus + "/" + hpBonus;
	}

	public int getHpPlus() {
		return hpPlus;
	}

	public int getNbCle() {
		return nbCle;
	}

	public int getNbPotion() {
		return nbPotion;
	}

	public void setNbCle(int cle) {
		nbCle = cle;
	}

	public void setHpPlus(int hpPlus) {
		this.hpPlus = hpPlus;
	}

	public void attaque(Monstre p) {
		Random ran = new Random();
		message("attaque " + p.getNom());
		if (ran.nextInt(100) <= 10) {
			p.setHp(p.getHp() - (dmgBonus * 2));
			System.out.println("COUP CRITIQUE!");
		} else
			p.setHp(p.getHp() - dmgBonus);
		if (p.getHp() > 0)
			System.out.println(p + "\n");
		else {
			System.out.println("Le " + p.getNom() + " est mort!");
			System.out.println("Vous trouver des pièces d'or sur le cadavre du " + p.getNom());
			int gain = 3 + ran.nextInt(5);
			or += gain;
			System.out.println("+" + gain + " pièces d'or");
			Random r = new Random();
			if (r.nextInt(100) <= 40) {
				nbCle += 1;
				System.out.println("+1 clé\n");
			}
			if (r.nextInt(100) <= 45) {
				nbPotion += 1;
				System.out.println("+1 potion\n");
			}
			if (r.nextInt(100) <= 20) {
				if (r.nextInt(100) <= 50)
					ajouterEquipement(new Arme(2 + r.nextInt(3)));
				else
					ajouterEquipement(new Armure(10 + r.nextInt(20), Clavier.randomElement()));
			}
		}
	}

	public void ajouterEquipement(Equipement a) {
		if (a.getId() == TypeObject.Arme) {
			Arme ar = (Arme) a;
			if (arme.getDmg() < ar.getDmg()) {
				System.out.println("Vous lachez : " + a);
				dmgBonus += ar.getDmg();
				dmgBonus -= arme.getDmg();
				arme = ar;
				System.out.println(a.msg() + a.stat());
			} else {
				System.out.println("Votre équipement est mieux que cet objet");
				System.out.println(a.msg() + a.stat());
			}
		} else {
			Armure ar = (Armure) a;
			for (Armure item : listArmure) {
				if (item.getId() == ar.getId()) {
					if (item.getHpBonus() < ar.getHpBonus()) {
						System.out.println("Vous lachez : " + item);
						listArmure.remove(item);
						listArmure.add(ar);
						hpPlus += ar.getHpBonus();
						hpPlus -= item.getHpBonus();
						hpBonus += ar.getHpBonus();
						hpBonus -= item.getHpBonus();
						System.out.println(a.msg() + a.stat());
						break;
					} else {
						System.out.println(a);
						System.out.println("Votre équipement est mieux que cet objet");
						break;
					}
				}
			}
		}
	}

	public void afficherInv() {
		System.out.println("INVENTAIRE : ");
		System.out.println("- " + arme);
		for (Armure a : listArmure) {
			System.out.println("- " + a);
		}
		if (nbPotion > 1)
			System.out.println("- " + nbPotion + " potions");
		else
			System.out.println("- " + nbPotion + " potion");
		if (nbCle > 1)
			System.out.println("- " + nbCle + " clés");
		else
			System.out.println("- " + nbCle + " clé ");
		if (or > 1)
			System.out.println("- " + or + " pièces d'or");
		else
			System.out.println("- " + or + " pièce d'or\n");

		System.out.println("Vie : " + hpPlus + "/" + hpBonus + "\n");
	}

	public void ouvrirCoffre(Coffre c) {
		if (c.fermer())
			ajouterEquipement(c.getContenu());
	}

	public void boirePotion() {
		if (nbPotion > 0) {
			if (hpPlus == hpBonus)
				System.out.println("Votre santé est déjà au maximum");
			else {
				System.out.println("+20 points de vie");
				System.out.println("-1 potion");
				hpPlus += 20;
				nbPotion--;
				if (hpPlus > hpBonus)
					hpPlus = hpBonus;
				System.out.println(this);
				if (nbPotion <= 1)
					System.out.println(nbPotion + " potion restante\n");
				else
					System.out.println(nbPotion + " potions restante\n");
			}
		} else
			System.out.println("Vous n'avez pas de potions");
	}

}
