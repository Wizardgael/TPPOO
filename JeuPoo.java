package handler;

import java.io.IOException;

public class JeuPoo {

	public static void main(String[] args) throws IOException {
		String nom;
		System.out.println("      ____  ____   ____ ");
		System.out.println("     |  _ \\|  _ \\ / ___|");
		System.out.println("     | |_) | |_) | |  _ ");
		System.out.println("     |  _ <|  __/| |_| |");
		System.out.println("     |_| \\_\\_|    \\____|");
		System.out.println("\n\n\n");
		System.out.println("[START] pour commencer");
		String choix;
		do {
			choix = Clavier.lireTexte();
			
		}while(!choix.equals("START") && !choix.equals("start"));
		
		System.out.println("Veuillez choisir le nom du héros :\n=>");
		do {
			nom = Clavier.lireTexte();
			if (nom.length() < 3)
				System.out.println("Le nom est trop court.");
			if (nom.length() > 15)
				System.out.println("Le nom est trop Long.");
		} while (nom.length() < 3 || nom.length() > 15);
		Console c = new Console(nom);
		c.start();

	}

}
