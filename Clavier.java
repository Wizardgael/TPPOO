package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import Equipement.TypeObject;

public class Clavier {

	public static String lireTexte() throws IOException {
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		boolean ok = false;
		String res = "";
		while (!ok) {
			try {
				res = buff.readLine();
				ok = true;
			} catch (NumberFormatException f) {
				System.out.print("\nCeci n'est pas un nombre, veuillez recommencer : ");
			}
		}
		return res;
	}
	
	public static TypeObject randomElement() {
		Random r = new Random();
		return TypeObject.values()[r.nextInt(TypeObject.values().length - 1)];
	}
}
