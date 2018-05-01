package Equipement;

public abstract class Equipement {
	
	private String nom;
	private TypeObject id;
	
	public Equipement(String nom, TypeObject id) {
		this.nom = nom;
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public TypeObject getId() {
		return id;
	}

	public abstract String msg();
	
	public abstract String stat();	
	
}
