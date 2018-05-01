package Salle;

import java.util.Random;
import Entite.Joueur;
import handler.Console;

public class Donjon {

	private int largeur, hauteur;
	private Salle[][] donjon;
	private Console c;
	private Joueur j;
	String nom;
	boolean fini = false;

	// constructeur
	public Donjon(int l, int h, Console c, String nom) {
		largeur = l;
		hauteur = h;
		j = new Joueur(0, 0, 100, 5, nom);
		this.nom = nom;
		this.c = c;
		donjon = new Salle[hauteur][largeur];
		for (int x = 0; x < largeur; x++)
			for (int y = 0; y < hauteur; y++) {
				// if (x == 0 || y == hauteur - 1) {
				// Salle s = addSalleMonstre(x, y);
				// donjon[y][x] = s;
				// } else
				donjon[y][x] = addVide(x, y);
			}
		donjon[0][0] = new SalleDebut(0, 0, j, this);

		testPorte();
	}

	// getter and setter

	public Joueur getJoueur() {
		return j;
	}

	public Salle getSalle(int x, int y) {
		return donjon[y][x];
	}

	public String getNom() {
		return nom;
	}

	// add salle
	public Salle addVide(int x, int y) {
		return new Vide(x, y, j, this);
	}

	public Salle addSalleMonstre(int x, int y) {
		return new SalleMonstre(x, y, j, this);
	}

	public Salle addSalleCoffre(int x, int y) {
		return new SalleCoffre(x, y, j, this);
	}

	public Salle addSalleVide(int x, int y) {
		return new SalleVide(x, y, j, this);
	}

	public Salle addSallePiege(int x, int y) {
		return new SallePiege(x, y, j, this);
	}
	
	public Salle addShop(int x, int y) {
		return new Shop(x, y, j, this);
	}

	// methode
	public void carte() {
		for (int x = 0; x < largeur * 2 + 2; x++)
			System.out.print("#");
		System.out.println("");
		System.out.print("#");

		for (int y = 0; y < hauteur; y++)
			for (int x = 0; x < largeur; x++) {
				if (x != largeur - 1) {
					if (setPosPlayer(j, x, y)) {
						System.out.print("¤");
						System.out.print(" ");
					} else {
						System.out.print(donjon[y][x].getValeur());
						System.out.print(" ");
					}
				} else {
					if (setPosPlayer(j, x, y)) {
						System.out.print("¤ #\n#");
					} else {
						System.out.print(donjon[y][x].getValeur() + " #\n#");
					}
				}
			}
		for (int x = 0; x < largeur * 2 + 1; x++)
			System.out.print("#");
		System.out.println("");
		System.out.println("LEGENDE :");
		System.out.println("/ : inconnu ; V : salle vide ; C : coffre \n$ : Boutique ; ¤ : Vous\n ");
	}

	public boolean setPosPlayer(Joueur j, int x, int y) {
		if (j.getX() == x && j.getY() == y)
			return true;
		return false;
	}

	public void testPorte() {
		for (int x = 0; x < largeur; x++)
			for (int y = 0; y < hauteur; y++) {
				// Porte Nord
				if (y == 0) {
					donjon[y][x].setNord(false);
				} else {
					if (donjon[y - 1][x] instanceof Vide)
						donjon[y][x].setNord(false);
					else
						donjon[y][x].setNord(true);
				}
				// Porte Sud
				if (y == hauteur - 1) {
					donjon[y][x].setSud(false);
				} else {
					if (donjon[y + 1][x] instanceof Vide)
						donjon[y][x].setSud(false);
					else
						donjon[y][x].setSud(true);
				}
				// Porte Ouest
				if (x == 0) {
					donjon[y][x].setOuest(false);
				} else {
					if (donjon[y][x - 1] instanceof Vide)
						donjon[y][x].setOuest(false);
					else
						donjon[y][x].setOuest(true);
				}
				// Porte Est
				if (x == largeur - 1) {
					donjon[y][x].setEst(false);
				} else {
					if (donjon[y][x + 1] instanceof Vide)
						donjon[y][x].setEst(false);
					else
						donjon[y][x].setEst(true);
				}

			}

	}

	public void genererDonjon(int x, int y, Salle s) {
		int nb = s.getNbPorte();
		boolean nord = s.isNord(), sud = s.isSud(), est = s.isEst(), ouest = s.isOuest();
		Random r = new Random();
		if (testFin()) {
			donjon[y][x] = new SalleBoss(x, y, j, this);
		} else {
			// création nord
			if (!nord && s.getY() > 0) {
				if (r.nextBoolean()) {
					genererSalle(s.getX(), s.getY() - 1);
					s.setNord(true);
				}
			}
			// creation sud
			if (!sud && s.getY() < hauteur - 1) {
				if (r.nextBoolean()) {
					genererSalle(s.getX(), s.getY() + 1);
					s.setSud(true);
				}
			}
			// création est
			if (!est && s.getX() < largeur) {
				if (r.nextBoolean()) {
					genererSalle(s.getX() + 1, s.getY());
					s.setEst(true);
				}
			}
			// création ouest
			if (!ouest && s.getX() > 0) {
				if (r.nextBoolean()) {
					genererSalle(s.getX() - 1, s.getY());
					s.setOuest(true);
				}
			}

			// si pas de portes généré
			if (s.getNbPorte() == nb) {
				boolean gener = false;
				if (!nord && s.getY() > 0 && !gener) {
					genererSalle(s.getX(), s.getY() - 1);
					gener = true;
				}
				if (!sud && s.getY() < hauteur - 1 && !gener) {
					genererSalle(s.getX(), s.getY() + 1);
					gener = true;
				}
				if (!est && s.getX() < largeur && !gener) {
					genererSalle(s.getX() + 1, s.getY());
					gener = true;
				}
				if (!ouest && s.getX() > 0 && !gener) {
					genererSalle(s.getX() - 1, s.getY());
					gener = true;
				}
			}
		}
	}

	public boolean testFin() {
		if (j.getFin() >= 20 && !fini) {
			fini = true;
			return true;
		}
		return false;
	}

	public void genererSalle(int x, int y) {
		Random r = new Random();
		int random = r.nextInt(100);
		if (random <= 15)
			donjon[y][x] = addSalleCoffre(x, y);
		if (random > 15 && random <= 45)
			donjon[y][x] = addSalleMonstre(x, y);
		if (random > 45 && random <= 55)
			donjon[y][x] = addShop(x, y);
		if (random > 55 && random <= 75)
			donjon[y][x] = addSalleVide(x, y);
		if (random > 75)
			donjon[y][x] = addSallePiege(x, y);

	}

	public void setdonjonFin(int x, int y) {
		donjon[y][x] = new SalleFin(x, y, j, this, c);	
	}
}
