package Salle;

import Entite.*;

public abstract class Salle {
	
	private int x;
	private int y;
	private boolean nord,sud,est,ouest;
	String valeur;
	private boolean premierPassage;
	
	//connstructeur
	public Salle(int x, int y,String valeur ,Joueur j , Donjon d) {
		this.x = x;
		this.y = y;
		this.valeur = valeur;
		premierPassage = true;
	}
	
	//getters and setters
	public String getValeur() {
		return valeur;
	}
	
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	public abstract void jouerSalle();

	public boolean isNord() {
		return nord;
	}
	
	public void setPermierPassage(boolean rep) {
		this.premierPassage = rep;
	}

	public boolean premierPassage() {
		return premierPassage;
	}

	public void setNord(boolean nord) {
		this.nord = nord;
	}

	public boolean isSud() {
		return sud;
	}

	public void setSud(boolean sud) {
		this.sud = sud;
	}

	public boolean isEst() {
		return est;
	}

	public void setEst(boolean est) {
		this.est = est;
	}

	public boolean isOuest() {
		return ouest;
	}

	public void setOuest(boolean ouest) {
		this.ouest = ouest;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getNbPorte(){
		int c = 0;
		if(nord)
			c++;
		if(sud)
			c++;
		if(est)
			c++;
		if(ouest)
			c++;
		return c;
	}
	
	
	
	

}
