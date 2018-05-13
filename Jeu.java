package com;
import java.util.Scanner;

import com.Plateau;

import base.Joueur;

public final class Jeu {
	public static final int O=-1;
	public static final int X=1;
	public static Scanner scanner = new Scanner(System.in);
	public static void run(Plateau plateau)
	{
		int victoire = 0, joueur = Jeu.O;
		
		//Création de deux joueurs 
		Joueur humain = new Humain("Joueur",O);
		Joueur j2 = new Ordinateur("Joueur",X,2);
		
		//Jeu
		while(!plateau.complet())
		{
			
			//Tour du joueur
			if(joueur == Jeu.O)
				humain.jouer(plateau);
			else
				j2.jouer(plateau);
			
			//Vérification de la victoire
			victoire = plateau.victoire();
			if(victoire != 0)
				break;
			
			//Changement de tour
			joueur *= -1;
		}
		
		if(victoire != 0)
			System.out.println("Victoire du joueur " + (joueur == Jeu.X ? "X" : "O"));
		else
			System.out.println("Match nul");
		
		//Fermeture du scanner.
		scanner.close();
	}
}
