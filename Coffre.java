package Equipement;

import java.util.ArrayList;
import java.util.Random;

public class Coffre {

	private ArrayList<TypeObject> listType = new ArrayList<TypeObject>();
	Equipement contenu;
	int nbPotion = 0;
	TypeObject type;
	boolean ouvert = false;
	
	//constructeurs
	public Coffre() {
		listType.add(TypeObject.Arme);
		listType.add(TypeObject.Casque);
		listType.add(TypeObject.Botte);
		listType.add(TypeObject.Plastron);
		listType.add(TypeObject.Bouclier);
		listType.add(TypeObject.Jambiere);
		Random r = new Random();
		type = listType.get(r.nextInt(5));
		if(type == TypeObject.Arme)
			contenu = new Arme(2 + r.nextInt(3));
		else
			contenu = new Armure(10+ r.nextInt(20),type);
	}
	
	//getter and setter
	public Equipement getContenu() {
		ouvert = true;
		return contenu;
	}
	
	public boolean fermer() {
		return !ouvert;
	}
	
	
}
