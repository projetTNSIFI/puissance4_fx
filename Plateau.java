package com;

import java.util.Arrays;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	public void afficherConsole()
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
	
	public void afficher(Stage rootStage)
	{
		//Récupération du root de la scene
		Scene scene = rootStage.getScene();
		Group group = (Group) scene.getRoot();
		GridPane grid = (GridPane)group.getChildren().get(0);
		
		//Ajout d'un bouton pour chaque case dans le groupe.
		if(grid.getChildren().isEmpty())
		{
			//On ajoute les boutons au groupe
			for(int i = 0; i < matrix.length;i++)
			{
				for(int j = 0; j < matrix[i].length;j++)
				{
					Pion pion;
					switch(matrix[i][j])
					{
					case 0:
						pion = new Pion(i*50,j*50,50,i,j);
						pion.setFill(Color.GREY);
						break;
					case Jeu.X:
						pion = new Pion(i*50,j*50,50,i,j);
						pion.setFill(Color.RED);
						break;
					case Jeu.O:
						pion = new Pion(i*50,j*50,50,i,j);
						pion.setFill(Color.BLUE);
						break;
					default :
						pion = new Pion(i*50,j*50,50,i,j);
						pion.setFill(Color.GREY);
						break;
					}
					
					//Positionnement des boutons
					//btn.setLayoutX(100 * i);
					//btn.setLayoutY(100 * j);
					//btn.setPadding(new Insets(90));
					//Ajout du bouton au groupe.
					grid.add(pion,j,i);
				}
			}
		}
		//Si le groupe contient déjà des boutons, on met à jour leur contenu.
		else
		{
			/*
			for(int i = 0;i < matrix.length;i++)
			{
				for(int j = 0; j < matrix[i].length;j++)
				{
					Bouton btn = (Bouton) grid.getChildren().get(j + 3 * i);
					switch(matrix[j][i])
					{
					case 0:
						btn.setText("-");
						break;
					case Jeu.X:
						btn.setText("X");
						break;
					case Jeu.O:
						btn.setText("O");
						break;
					default :
						btn.setText("-");
						break;
					}
				}
			}*/
			
			for(int i = 0;i < grid.getChildren().size();i++)
			{
				Pion pion = (Pion) grid.getChildren().get(i);
				switch(matrix[pion.i][pion.j])
				{
				case 0:
					pion.setFill(Color.GREY);
					break;
				case Jeu.X:
					pion.setFill(Color.RED);
					break;
				case Jeu.O:
					pion.setFill(Color.BLUE);
					break;
				default :
					pion.setFill(Color.GREY);
					break;
				}
			}
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
