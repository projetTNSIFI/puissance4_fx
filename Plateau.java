package com;

public class Plateau {
	protected int matrix[][];
	
	public Plateau()
	{
		this.matrix = new int[3][3];
	}
	
	public void placer(boolean token,int x, int y)
	{
		char joueur = 1;
		if(token)
			joueur = 2;
		matrix[y][x] = joueur;
	}
	
	public boolean complet()
	{
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[j][i]==0)
					return false;
			}
		}
		return true;
	}
	
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
				case 1:
					System.out.print("O");
					break;
				case 2:
					System.out.print("X");
					break;
				}
			
			}
			System.out.println();
		}
	}
	
	public boolean victoire()
	{
		int nombre_cases_identiques_O = 0;
		int nombre_cases_identiques_X = 0;
		
		//Controle des lignes
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix.length;j++)
			{
				if(matrix[j][i]==1)
					nombre_cases_identiques_O++;
				else if(matrix[j][i]==2)
					nombre_cases_identiques_X++;
				
				if(nombre_cases_identiques_O == 3 || nombre_cases_identiques_X == 3)
					return true;
			}
			nombre_cases_identiques_O = 0;
			nombre_cases_identiques_X = 0;
		}
		
		//Controle des colonnes
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix.length;j++)
			{
				if(matrix[i][j]==1)
					nombre_cases_identiques_O++;
				else if(matrix[i][j]==2)
					nombre_cases_identiques_X++;
				
				if(nombre_cases_identiques_O == 3 || nombre_cases_identiques_X == 3)
					return true;
			}
			nombre_cases_identiques_O = 0;
			nombre_cases_identiques_X = 0;
		}
		
		//Controle des diagonales
		if(matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2] && matrix[1][1] != 0)
			return true;
		if(matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0] && matrix[1][1] != 0)
			return true;
		
		//Pas de victoire
		return false;
	}
}
