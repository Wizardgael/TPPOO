package handler;

import Salle.*;

import java.io.IOException;
import java.util.Random;
import Equipement.*;

public class Action {

	Donjon d;
	Salle s;
	boolean nord, sud, est, ouest;

	// constructeur
	public Action(Donjon d, Salle s) {
		this.d = d;
		this.s = s;
		nord = s.isNord();
		sud = s.isSud();
		est = s.isEst();
		ouest = s.isOuest();
	}

	// methode
	public void actionJoueur() {
		if (s instanceof SalleDebut) {
			debut();
		}
		if (s instanceof SalleMonstre) {
			SalleMonstre s = (SalleMonstre) this.s;
			Monstre(s);
		}
		if (s instanceof SalleCoffre) {
			jouerCoffre((SalleCoffre) s);
		}
		if (s instanceof SalleVide) {
			jouerSalleVide();
		}
		if (s instanceof SallePiege) {
			jouerSallePiege((SallePiege) s);
		}
		if (s instanceof Shop) {
			jouerShop((Shop) s);
		}
		if (s instanceof SalleBoss) {
			jouerBoss((SalleBoss) s);
		}

	}

	private void jouerBoss(SalleBoss s) {
		if (s.premierPassage()) {
			System.out.println("Vous êtes téléporté dans une grande salle sombre.");
			System.out.println("Soudain, un jet de flamme manque de vous bruler le visage.");
			System.out.println("Un gigantesque dragon sort de l'ombre.");
			System.out.println("Vous ne pouvez pas fuir.");
			s.setPermierPassage(false);
		}

		if (s.getBoss().getHp() > 0) {
			System.out.println("Que Faire?");
			System.out.println("[A] Attaquer");
			System.out.println("[I] Afficher l'inventaire");
			if (d.getJoueur().getNbPotion() > 0)
				System.out.println("[U] Utiliser une potion");
			System.out.println("[QUITTER] Quitter le jeu");
			System.out.println("=>");
			try {
				switch (Clavier.lireTexte()) {
				case "A":
					d.getJoueur().attaque(s.getBoss());
					if (s.getBoss().getHp() > 0)
						s.getBoss().attaque(d.getJoueur());
					break;
				case "I":
					d.getJoueur().afficherInv();
					break;
				case "U":
					if (d.getJoueur().getNbPotion() > 0)
						d.getJoueur().boirePotion();
					System.out.println("Le " + s.getBoss().getNom() + " en profite pour vous attaquer");
					s.getBoss().attaque(d.getJoueur());
					break;
				case "QUITTER":
					System.exit(1);
				case "a":
					d.getJoueur().attaque(s.getBoss());
					if (s.getBoss().getHp() > 0)
						s.getBoss().attaque(d.getJoueur());
					break;
				case "i":
					d.getJoueur().afficherInv();
					break;
				case "u":
					if (d.getJoueur().getNbPotion() > 0)
						d.getJoueur().boirePotion();
					System.out.println("Le " + s.getBoss().getNom() + " en profite pour vous attaquer");
					s.getBoss().attaque(d.getJoueur());
					break;
				case "quitter":
					System.exit(1);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Le dragon meut et fini en tas de cendre.");
			d.getJoueur().ajouterEquipement(new Arme(100, "Aubéclat"));
			d.getJoueur().ajouterEquipement(new Armure("écailles de dragon", 150, TypeObject.Casque));
			d.getJoueur().ajouterEquipement(new Armure("écailles de dragon", 150, TypeObject.Bouclier));
			d.getJoueur().ajouterEquipement(new Armure("écailles de dragon", 150, TypeObject.Plastron));
			d.getJoueur().ajouterEquipement(new Armure("écailles de dragon", 150, TypeObject.Jambiere));
			d.getJoueur().ajouterEquipement(new Armure("écailles de dragon", 150, TypeObject.Botte));
			System.out.println("Vous trouver 1000 pièces d'or\n+1000 pièces d'or");
			System.out.println("Vous trouver 10 potions \n+10 potions");
			d.setdonjonFin(d.getJoueur().getX(), d.getJoueur().getY());
		}

	}

	private void jouerShop(Shop s) {
		if (s.premierPassage()) {
			s.setPermierPassage(false);
			System.out.println("Vous trouvez une boutique perdu dans le donjon.");
			s.setValeur("$");
		}

		if (d.getJoueur().getOr() > 1)
			System.out.println("Vous avez " + d.getJoueur().getOr() + " pièces d'or");
		else
			System.out.println("Vous avez " + d.getJoueur().getOr() + " pièce d'or");
		System.out.println("Que Faire?");
		if (!s.isAchat1())
			System.out.println("[1] " + s.getItem1() + " : " + s.getItem1().getPrix() + " pièces d'or");
		if (!s.isAchat2())
			System.out.println("[2] " + s.getItem2() + " : " + s.getItem2().getPrix() + " pièces d'or");
		if (!s.isAchat3())
			System.out.println("[3] " + s.getItem3() + " : " + s.getItem3().getPrix() + " pièces d'or");
		System.out.println("[4] Acheter une potion - 5 pièces d'or");
		if (nord)
			System.out.println("[N] Aller au Nord");
		if (sud)
			System.out.println("[S] Aller au Sud");
		if (est)
			System.out.println("[E] Aller à l'Est");
		if (ouest)
			System.out.println("[O] Aller à l'Ouest");
		System.out.println("[I] Afficher l'inventaire");
		if (d.getJoueur().getNbPotion() > 0)
			System.out.println("[U] Utiliser une potion");
		System.out.println("[C] Afficher la carte");
		System.out.println("[QUITTER] Quitter le jeu");
		System.out.println("=>");
		try {
			switch (Clavier.lireTexte()) {
			case "N":
				if (nord)
					d.getJoueur().deplacementNord();
				break;
			case "S":
				if (sud)
					d.getJoueur().deplacementSud();
				break;
			case "E":
				if (est)
					d.getJoueur().deplacementEst();
				break;
			case "O":
				if (ouest)
					d.getJoueur().deplacementOuest();
				break;
			case "C":
				d.carte();
				break;
			case "I":
				d.getJoueur().afficherInv();
				break;
			case "U":
				if (d.getJoueur().getNbPotion() > 0)
					d.getJoueur().boirePotion();
				break;
			case "1":
				if (!s.isAchat1()) {
					if (d.getJoueur().getOr() > s.getItem1().getPrix()) {
						d.getJoueur().ajouterEquipement(s.getItem1());
						d.getJoueur().setOr(d.getJoueur().getOr() - s.getItem1().getPrix());
						s.setAchat1(true);
					} else
						System.out.println("Vous n'avez pas assez d'argent");
				}
				break;
			case "2":
				if (!s.isAchat2()) {
					if (d.getJoueur().getOr() > s.getItem2().getPrix()) {
						d.getJoueur().ajouterEquipement(s.getItem2());
						d.getJoueur().setOr(d.getJoueur().getOr() - s.getItem2().getPrix());
						s.setAchat2(true);
					} else
						System.out.println("Vous n'avez pas assez d'argent");
				}
				break;
			case "3":
				if (!s.isAchat3()) {
					if (d.getJoueur().getOr() > s.getItem3().getPrix()) {
						d.getJoueur().ajouterEquipement(s.getItem3());
						d.getJoueur().setOr(d.getJoueur().getOr() - s.getItem3().getPrix());
						s.setAchat3(true);
					} else
						System.out.println("Vous n'avez pas assez d'argent");
				}
				break;
			case "4":
				if (d.getJoueur().getOr() > s.getPrixPotion()) {
					d.getJoueur().setOr(d.getJoueur().getOr() - s.getPrixPotion());
					d.getJoueur().setPotion(d.getJoueur().getPotion() + 1);
				} else
					System.out.println("Vous n'avez pas assez d'argent");
				break;
			case "QUITTER":
				System.exit(1);
				break;
			case "n":
				if (nord)
					d.getJoueur().deplacementNord();
				break;
			case "s":
				if (sud)
					d.getJoueur().deplacementSud();
				break;
			case "e":
				if (est)
					d.getJoueur().deplacementEst();
				break;
			case "o":
				if (ouest)
					d.getJoueur().deplacementOuest();
				break;
			case "c":
				d.carte();
				break;
			case "i":
				d.getJoueur().afficherInv();
				break;
			case "u":
				if (d.getJoueur().getNbPotion() > 0)
					d.getJoueur().boirePotion();
				break;
			case "quitter":
				System.exit(1);
			}
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void jouerSallePiege(SallePiege s) {
		if (s.premierPassage()) {
			s.setPermierPassage(false);
			System.out.println("Vous arrivez dans une grande salle.");
			System.out.println("Soudain " + s.getPiege().getNom() + " s'active");
			System.out.println("Vous essayer de désamorcer le piège");
			s.getPiege().activation(d.getJoueur());
			s.setValeur("P");
		}
		actionBase();

	}

	private void jouerSalleVide() {
		if (s.premierPassage()) {
			s.setPermierPassage(false);
			System.out.println("Vous arrivez dans une salle complètement vide .");
			System.out.println("Il n'y a rien a faire dans cette salle .");
			s.setValeur("V");
		}
		actionBase();
	}

	private void Monstre(SalleMonstre s) {
		if (s.premierPassage()) {
			System.out.println("Vous arrivez dans une salle sombre .");
			System.out.println("Un " + s.getMonstre().getNom() + " vous attaque .");
			System.out.println("");
			s.setPermierPassage(false);
			s.setValeur("M");
		}

		if (s.getMonstre().getHp() > 0 && !s.Fuite()) {
			System.out.println("Que Faire?");
			System.out.println("[A] Attaquer");
			System.out.println("[F] Fuir");
			System.out.println("[I] Afficher l'inventaire");
			if (d.getJoueur().getNbPotion() > 0)
				System.out.println("[U] Utiliser une potion");
			System.out.println("[C] Afficher la carte");
			System.out.println("[QUITTER] Quitter le jeu");
			System.out.println("=>");
			try {
				switch (Clavier.lireTexte()) {
				case "A":
					d.getJoueur().attaque(s.getMonstre());
					if (s.getMonstre().getHp() > 0)
						s.getMonstre().attaque(d.getJoueur());
					break;
				case "F":
					Random r = new Random();
					if (r.nextBoolean()) {
						System.out.println("Vous fuyez le combat");
						s.setFuite(true);
					} else {
						System.out.println("Vous n'avez pas réussi à fuir");
						System.out.println("Le " + s.getMonstre().getNom() + " en profite pour vous attaquer");
						s.getMonstre().attaque(d.getJoueur());
					}
					break;
				case "C":
					d.carte();
					break;
				case "I":
					d.getJoueur().afficherInv();
					break;
				case "U":
					if (d.getJoueur().getNbPotion() > 0)
						d.getJoueur().boirePotion();
					System.out.println("Le " + s.getMonstre().getNom() + " en profite pour vous attaquer");
					s.getMonstre().attaque(d.getJoueur());
					break;
				case "QUITTER":
					System.exit(1);
				case "a":
					d.getJoueur().attaque(s.getMonstre());
					if (s.getMonstre().getHp() > 0)
						s.getMonstre().attaque(d.getJoueur());
					break;
				case "f":
					Random ran = new Random();
					if (ran.nextBoolean()) {
						System.out.println("Vous fuyez le combat");
						s.setFuite(true);
					} else {
						System.out.println("Vous n'avez pas réussi à fuir");
						System.out.println("Le " + s.getMonstre().getNom() + " en profite pour vous attaquer");
						s.getMonstre().attaque(d.getJoueur());
					}
					break;
				case "c":
					d.carte();
					break;
				case "i":
					d.getJoueur().afficherInv();
					break;
				case "u":
					if (d.getJoueur().getNbPotion() > 0)
						d.getJoueur().boirePotion();
					System.out.println("Le " + s.getMonstre().getNom() + " en profite pour vous attaquer");
					s.getMonstre().attaque(d.getJoueur());
					break;
				case "quitter":
					System.exit(1);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			actionBase();
		}
	}

	private void debut() {
		if (s.premierPassage()) {
			System.out.println("\nVous vous réveillez, piegé dans un donjon.");
			System.out.println("Votre objectif est de vous échaper du donjon.");
			System.out.println("Vous trouvez par terre une arme rouillé et un plastron déchiré.");
			d.getJoueur().ajouterEquipement(new Arme(2));
			d.getJoueur().ajouterEquipement(new Armure(20, TypeObject.Plastron));
			s.setPermierPassage(false);
		}
		while (d.getJoueur().getX() == s.getX() && d.getJoueur().getY() == s.getY()) {
			actionBase();
		}

	}

	private void actionBase() {
		System.out.println("Que Faire?");
		if (nord)
			System.out.println("[N] Aller au Nord");
		if (sud)
			System.out.println("[S] Aller au Sud");
		if (est)
			System.out.println("[E] Aller à l'Est");
		if (ouest)
			System.out.println("[O] Aller à l'Ouest");
		System.out.println("[I] Afficher l'inventaire");
		if (d.getJoueur().getNbPotion() > 0)
			System.out.println("[U] Utiliser une potion");
		System.out.println("[C] Afficher la carte");
		System.out.println("[QUITTER] Quitter le jeu");
		System.out.println("=>");
		try {
			switch (Clavier.lireTexte()) {
			case "N":
				if (nord)
					d.getJoueur().deplacementNord();
				break;
			case "S":
				if (sud)
					d.getJoueur().deplacementSud();
				break;
			case "E":
				if (est)
					d.getJoueur().deplacementEst();
				break;
			case "O":
				if (ouest)
					d.getJoueur().deplacementOuest();
				break;
			case "C":
				d.carte();
				break;
			case "I":
				d.getJoueur().afficherInv();
				break;
			case "U":
				if (d.getJoueur().getNbPotion() > 0)
					d.getJoueur().boirePotion();
				break;
			case "QUITTER":
				System.exit(1);
			case "n":
				if (nord)
					d.getJoueur().deplacementNord();
				break;
			case "s":
				if (sud)
					d.getJoueur().deplacementSud();
				break;
			case "e":
				if (est)
					d.getJoueur().deplacementEst();
				break;
			case "o":
				if (ouest)
					d.getJoueur().deplacementOuest();
				break;
			case "c":
				d.carte();
				break;
			case "i":
				d.getJoueur().afficherInv();
				break;
			case "u":
				if (d.getJoueur().getNbPotion() > 0)
					d.getJoueur().boirePotion();
				break;
			case "quitter":
				System.exit(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void jouerFin(Console c) {
		String choix;
		System.out.println("Félicitation , vous êtes sortis du donjon");
		try {
			do {
				System.out.println("Nouvelle Partie ?(O/N)");

				choix = Clavier.lireTexte();
			} while (!choix.equals("o") && !choix.equals("n") && !choix.equals("O") && !choix.equals("N"));
			if (choix.equals("o") || choix.equals("O")) {
				c.setDonjon(new Donjon(8, 4, c, d.getNom()));
			} else {
				System.exit(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void jouerCoffre(SalleCoffre s) {
		if (s.premierPassage()) {
			System.out.println("Arrivé dans la salle,\nvotre regard est attiré par le coffre au milieu");
			s.setPermierPassage(false);
			s.setValeur("C");
		}
		System.out.println("Que Faire?");
		if (nord)
			System.out.println("[N] Aller au Nord");
		if (sud)
			System.out.println("[S] Aller au Sud");
		if (est)
			System.out.println("[E] Aller à l'Est");
		if (ouest)
			System.out.println("[O] Aller à l'Ouest");
		if (s.getCoffre().fermer())
			System.out.println("[D] Ouvrir le coffre (-1 clé)");
		System.out.println("[I] Afficher l'inventaire");
		if (d.getJoueur().getNbPotion() > 0)
			System.out.println("[U] Utiliser une potion");
		System.out.println("[C] Afficher la carte");
		System.out.println("[QUITTER] Quitter le jeu");
		System.out.println("=>");
		try {
			switch (Clavier.lireTexte()) {
			case "N":
				if (nord)
					d.getJoueur().deplacementNord();
				break;
			case "S":
				if (sud)
					d.getJoueur().deplacementSud();
				break;
			case "E":
				if (est)
					d.getJoueur().deplacementEst();
				break;
			case "O":
				if (ouest)
					d.getJoueur().deplacementOuest();
				break;
			case "C":
				d.carte();
				break;
			case "I":
				d.getJoueur().afficherInv();
				break;
			case "U":
				if (d.getJoueur().getNbPotion() > 0)
					d.getJoueur().boirePotion();
				break;
			case "D":
				if (s.getCoffre().fermer()) {
					if (d.getJoueur().getNbCle() > 0) {
						d.getJoueur().ouvrirCoffre(s.getCoffre());
						d.getJoueur().setNbCle(d.getJoueur().getNbCle() - 1);
						System.out.println("-1 clé");
					} else {
						System.out.println("Vous n'avez pas asser de clé");
					}
				}
				break;
			case "QUITTER":
				System.exit(1);
			case "n":
				if (nord)
					d.getJoueur().deplacementNord();
				break;
			case "s":
				if (sud)
					d.getJoueur().deplacementSud();
				break;
			case "e":
				if (est)
					d.getJoueur().deplacementEst();
				break;
			case "o":
				if (ouest)
					d.getJoueur().deplacementOuest();
				break;
			case "c":
				d.carte();
				break;
			case "i":
				d.getJoueur().afficherInv();
				break;
			case "u":
				if (d.getJoueur().getNbPotion() > 0)
					d.getJoueur().boirePotion();
				break;
			case "d":
				if (s.getCoffre().fermer()) {
					if (d.getJoueur().getNbCle() > 0) {
						d.getJoueur().ouvrirCoffre(s.getCoffre());
						d.getJoueur().setNbCle(d.getJoueur().getNbCle() - 1);
						System.out.println("-1 clé");
					} else {
						System.out.println("Vous n'avez pas asser de clé");
					}
				}
				break;
			case "quitter":
				System.exit(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
