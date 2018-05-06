package com;
import java.util.Scanner;

import com.Plateau;

public final class Jeu {
	
	public static void run(Plateau plateau)
	{
		plateau.Afficher();
		boolean token = false,victoire = false;
		Scanner sc = new Scanner(System.in);
		int x,y;
		
		//Jeu
		while(!victoire || plateau.complet())
		{
			//Tour du joueur
			if(!token)
				System.out.println("Tour du joueur O");
			else
				System.out.println("Tour du joueur X");
			System.out.println("Entrez x : ");
			x = sc.nextInt();
			System.out.println("Entrez y : ");
			y = sc.nextInt();
			plateau.placer(token, x, y);
			plateau.Afficher();	
			victoire = plateau.victoire();
			if(victoire)
				break;
			//Changement de tour
			token = !token;
		}
		
		if(victoire)
			System.out.println("Victoire du joueur " + (token ? "X" : "O"));
		else
			System.out.println("Match nul");
		sc.close();
	}
}
