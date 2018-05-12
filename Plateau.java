package com;

import java.util.Arrays;

public class Plateau {
	protected int matrix[][];
	
	public Plateau()
	{
		this.matrix = new int[3][3];
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
	public void placer(int joueur,int x, int y)
	{
		matrix[y][x] = joueur;
	}
	
	public boolean complet()
	{
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[j][i] == 0)
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
		
		//Controle des lignes
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[j][i]==Jeu.O)
					nombre_cases_identiques_O++;
				else if(matrix[j][i]==Jeu.X)
					nombre_cases_identiques_X++;
				
				
				if(nombre_cases_identiques_O == 3)
					return Jeu.O;
				else if(nombre_cases_identiques_X == 3)
					return Jeu.X;
			}
			nombre_cases_identiques_O = 0;
			nombre_cases_identiques_X = 0;
		}
		
		//Controle des colonnes
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[i][j]==Jeu.O)
					nombre_cases_identiques_O++;
				else if(matrix[i][j]==Jeu.X)
					nombre_cases_identiques_X++;
			
				if(nombre_cases_identiques_O == 3)
					return Jeu.O;
				else if(nombre_cases_identiques_X == 3)
					return Jeu.X;
			}
			nombre_cases_identiques_O = 0;
			nombre_cases_identiques_X = 0;
		}
		
		//Controle des diagonales
		if(matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2] && matrix[1][1] == Jeu.O)
			return Jeu.O;
		if(matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0] && matrix[1][1] == Jeu.O)
			return Jeu.O;
		
		if(matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2] && matrix[1][1] == Jeu.X)
			return Jeu.X;
		if(matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0] && matrix[1][1] == Jeu.X)
			return Jeu.X;
		
		//Pas de victoire
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
