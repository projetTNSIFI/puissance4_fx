package com;

import base.Joueur;

public class Humain extends Joueur{
	
	public Humain(String nom, int pion)
	{
		super(nom,pion);
	}
	
	//Tour du joueur humain.
	public void jouer(Plateau plateau) 
	{
		int length = plateau.getPlateau().length;
		int x,y;
		
		System.out.println("Tour du joueur " + nom);
		//L'utilisateur entre les coordonnées.
		do
		{
			System.out.println("Entrez x : ");
			x = Jeu.scanner.nextInt();
			System.out.println("Entrez y : ");
			y = Jeu.scanner.nextInt();
		}while(x < 0 || x >= length || y < 0 || y >= length || plateau.getPlateau()[y][x] != 0);
		
		//Place le pion sur le plateau.
		plateau.placer(pion, x, y);
		
		//Affichage du plateau après le tour de jeu.
		plateau.Afficher();
	}
}
