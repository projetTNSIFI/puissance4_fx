package com;

import java.util.Arrays;

public class Plateau {
	protected int matrix[][];
	
	public Plateau()
	{
		this.matrix = new int[6][7];
	}
	
	//Constructeur de copie
	public Plateau(Plateau copy)
	{
		int[][] m = copy.getPlateau();
		matrix = new int[m.length][];
		
		for(int i = 0;i < m.length;i++)
		{
			matrix[i] = Arrays.copyOf(m[i], m[i].length);
		}
	}
	
	//Place un pion sur le plateau
	public void placer(int joueur,int colonne)
	{
		int i = matrix.length-1;
		while(i > 0 && matrix[i][colonne] != 0)
			i--;
		
		if(matrix[i][colonne] == 0)
			matrix[i][colonne] = joueur;
		
	}
	
	public boolean complet()
	{
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	//TOKEN = -1 : O ; TOKEN = 1 : X
	public void Afficher()
	{
		for(int i  = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				switch(matrix[i][j])
				{
				case 0:
					System.out.print(".");
					break;
				case Jeu.O:
					System.out.print("O");
					break;
				case Jeu.X:
					System.out.print("X");
					break;
				}
			
			}
			System.out.println();
		}
	}
	
	//Retourne le joueur vainqueur, 0 si aucun joueur ne gagne.
	public int victoire()
	{
		int nombre_cases_identiques_O = 0;
		int nombre_cases_identiques_X = 0;
		
		int nombre_colonnes = matrix[0].length;
		int nombre_lignes = matrix.length;
		
		//Controle des lignes
		for(int i = 0;i < nombre_colonnes;i++)
		{
			for(int j = 0;j < nombre_lignes;j++)
			{
				if(matrix[j][i]==Jeu.O)
				{
					nombre_cases_identiques_O++;
					nombre_cases_identiques_X = 0;
				}
				else if(matrix[j][i]==Jeu.X)
				{
					nombre_cases_identiques_X++;
					nombre_cases_identiques_O = 0;
				}
				else
				{
					nombre_cases_identiques_X = 0;
					nombre_cases_identiques_O = 0;
				}
				
				
				if(nombre_cases_identiques_O == 4)
					return Jeu.O;
				else if(nombre_cases_identiques_X == 4)
					return Jeu.X;
			}
			nombre_cases_identiques_O = 0;
			nombre_cases_identiques_X = 0;
		}
		
		//Controle des colonnes
		for(int i = 0;i < nombre_lignes;i++)
		{
			for(int j = 0;j < nombre_colonnes;j++)
			{
				if(matrix[i][j]==Jeu.O)
				{
					nombre_cases_identiques_O++;
					nombre_cases_identiques_X = 0;
				}
				else if(matrix[i][j]==Jeu.X)
				{
					nombre_cases_identiques_X++;
					nombre_cases_identiques_O = 0;
				}
				else
				{
					nombre_cases_identiques_X = 0;
					nombre_cases_identiques_O = 0;
				}
			
				if(nombre_cases_identiques_O == 4)
					return Jeu.O;
				else if(nombre_cases_identiques_X == 4)
					return Jeu.X;
			}
			nombre_cases_identiques_O = 0;
			nombre_cases_identiques_X = 0;
		}
		
		//Controle des diagonales
		int dg = checkDG();
		int dd = checkDD();
		
		if(dg == Jeu.O)
			return Jeu.O;
		else if(dg == Jeu.X)
			return Jeu.X;
		
		if(dd == Jeu.O)
			return Jeu.O;
		else if(dd == Jeu.X)
			return Jeu.X;
		//Pas de victoire
		return 0;
	}
	
	//Verifie la diagonale bas gauche
	public int checkDG()
	{
		int nombre_cases_identiques_X = 0;
		int nombre_cases_identiques_O = 0;
		
		int nombre_lignes = matrix.length;
		int nombre_colonnes = matrix[0].length;
		
		for(int i = 0 ;i < nombre_lignes;i++)
		{
			for(int j = 0;j < nombre_colonnes;j++)
			{
				for(int k = 0;k < 4;k++)
				{
					if(i+k >= nombre_lignes || j+k >= nombre_colonnes)
						continue;
					else
					{
						//System.out.println("i = " + i + " k = "+k + " j = " + j + " nombre_lignes = " + nombre_lignes + " nombre colonnes = " + nombre_colonnes);
						if(matrix[i+k][j+k] == Jeu.O)
							nombre_cases_identiques_O++;
						if(matrix[i+k][j+k] == Jeu.X)
							nombre_cases_identiques_X++;
					}
						
				}
				if(nombre_cases_identiques_O == 4)
					return Jeu.O;
				else if(nombre_cases_identiques_X == 4)
					return Jeu.X;
				nombre_cases_identiques_O = 0;
				nombre_cases_identiques_X = 0;
			}
		}
		
		return 0;
	}
	
	//Verifie la diagonale bas droite.
	public int checkDD()
	{
		int nombre_cases_identiques_X = 0;
		int nombre_cases_identiques_O = 0;
		
		for(int i =0 ;i < matrix.length;i++)
		{
			for(int j =0;j < matrix[i].length;j++)
			{
				for(int k = 0;k < 4;k++)
				{
					if(i-k < 0 || j+k >= matrix[i].length)
						continue;
					else
					{
						if(matrix[i-k][j+k] == Jeu.O)
							nombre_cases_identiques_O++;
						if(matrix[i-k][j+k] == Jeu.X)
							nombre_cases_identiques_X++;
					}
						
				}
				if(nombre_cases_identiques_O == 4)
					return Jeu.O;
				else if(nombre_cases_identiques_X == 4)
					return Jeu.X;
				nombre_cases_identiques_O = 0;
				nombre_cases_identiques_X = 0;
			}
		}
		
		return 0;
	}
	//Retourne la grille de jeu
	public int[][] getPlateau()
	{
		return matrix;
	}
	
	public void setPlateau(Plateau plateau)
	{
		this.matrix = plateau.matrix;
	}
}
