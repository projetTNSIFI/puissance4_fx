package com;
import java.util.List;

import com.Noeud;

//Classe qui va générer l'arbre des noeuds possibles et faire l'évaluation de ces noeuds.
public class Evaluation {
	
	/*Genere un arbre contenant tous les noeuds possibles sur une certaine profondeur.
	 * Le noeud n est le premier noeud de l'arbre ( racine ), la profondeur correspond à combiend d'itération de l'arbre nous souhaitons générer
	 * Le token correspond au tour à partir du quel on va générer les noeuds. ( varie entre 1 et 2 ).
	 */
	public static void genererArbre(Noeud courant, int profondeur)
	{
		if(profondeur > 0)
		{
			courant.genererFils();
			
			//Récupération des noeuds fils au noeud courant
			List<Noeud> fils = courant.getFils();
			
			//Pour chacun des noeuds fils, on réitère le processus, mais on change le tour et la profondeur diminue.
			for(Noeud f : fils)
				genererArbre(f, profondeur-1);
			
		}
	}
	
	//Fonction qui va attribuer des valeurs aux noeuds
	public static void evaluer_noeuds(Noeud courant, int profondeur)
	{
		if(profondeur <= 0)
			courant.valeur_noeud = danger(courant,Jeu.X) + danger(courant,Jeu.O);
		
		//On attribue une valeur aux noeuds feuilles
		if(courant.estFeuille())
		{
			//On considère que l'adversaire ( le joueur ) jouera les O
			if(courant.plateau.victoire() == Jeu.X)
				courant.valeur_noeud = 10000;
			else if(courant.plateau.victoire() == Jeu.O)
				courant.valeur_noeud = -1000 + danger(courant,Jeu.O);
			else 
				courant.valeur_noeud = danger(courant,Jeu.X) + danger(courant,Jeu.O);//STRATEGIE DEFENSIVE ET OFFENSIVE
			//SI ON MET UNIQUEMENT danger(courant, Jeu.O) , STRATEGIE DEFENSIVE
		}
		else
		{
			//Sinon la valeur du noeud courant correspond au max ou au min de tous ses noeuds fils.
			if(courant.type == Noeud.MAX)//Noeud correspondant au tour du joueur
			{
				for(Noeud f : courant.fils)
				{
					evaluer_noeuds(f, profondeur - 1);
				}
				courant.valeur_noeud = courant.filsMin().valeur_noeud;
			}
			else if(courant.type == Noeud.MIN)//Noeud correspondant au tour de l'IA
			{
				for(Noeud f : courant.fils)
				{
					evaluer_noeuds(f, profondeur - 1);
				}
				courant.valeur_noeud = courant.filsMax().valeur_noeud;
			}
		}
		
	}
	
	public static void afficherArbre(Noeud courant)
	{
		for(Noeud f : courant.fils)
		{
			f.plateau.Afficher();
			System.out.println("Score du noeud : " + f.valeur_noeud);
		}
	}
	
	//Evalue le danger d'un noeud 
	protected static int danger(Noeud courant, int joueur)
	{
		int danger = 0;
		int ligne = 0,colonne = 0;
		int nombre_lignes = courant.plateau.getPlateau().length;
		int nombre_colonnes = courant.plateau.getPlateau()[0].length;
		int[][] matrix = courant.plateau.getPlateau();
		//On regarde dans toutes les directions ( sauf vers le haut ) pour savoir si le noeud est dangereux.
		
		//GAUCHE ET DROITE
		
		
		//On cherche chaque case libre pour le joueur
		for(colonne = 0; colonne < nombre_colonnes; colonne++)
		{
			for(ligne = nombre_lignes-1;ligne > 0;ligne--)
			{
				//Si on a pas une case vide on passe à la ligne suivante.
				if(matrix[ligne][colonne] != 0)
					continue;
				
				//Si on a une case vide, on évalue le danger
				danger += danger_ligne(matrix,ligne,colonne,joueur);
				danger += danger_colonne(matrix,ligne,colonne,joueur);
				danger += danger_dd(matrix,ligne,colonne,joueur);
				danger += danger_dg(matrix,ligne,colonne,joueur);
				break;//Passage a la colonne suivante.
			}
		}
		
		return danger;
	}
	
	//Fonctions permettant d'évaluer le danger en fonction de la position de la case.
	protected static int danger_ligne(int[][] matrix, int ligne, int colonne, int joueur)
	{
		int danger = 0;
		int nombre_cases_O = 0;
		int nombre_cases_vide = 1;//La case actuelle est vide, on la compte
		
		//On compte le nombre d'occurences de pions adverse
		for(int col = colonne; col < matrix[ligne].length;col++)
		{
			if(col == colonne)
				continue;
			
			if(matrix[ligne][col] == joueur)
				nombre_cases_O++;
			else if(matrix[ligne][col] == 0)
				nombre_cases_vide++;
			else
				break;//Si on trouve une case de l'ordinateur, on sort de la boucle.
		}
		for(int col = colonne; col > 0; col--)
		{
			if(col == colonne)
				continue;
			if(matrix[ligne][col] == joueur)
				nombre_cases_O++;
			else if(matrix[ligne][col] == 0)
				nombre_cases_vide++;
			else
				break;//Si on trouve une case de l'ordinateur, on sort de la boucle.
		}
		
		if(nombre_cases_O == 2 && nombre_cases_vide >= 2)
			danger -= 150;
		else if(nombre_cases_O >= 3)
			danger -= 300;
		
		//Danger negatif pour le joueur adverse, positif pour le joueur actuel.
		if(joueur == Jeu.O)
			return danger;
		return danger * -1;
	}
	
	protected static int danger_colonne(int[][] matrix, int ligne, int colonne, int joueur)
	{
		int danger = 0;
		int nombre_cases_O = 0;
		
		//On ne va regarder que vers le bas.
		//On compte le nombre d'occurences de pions adverse
		for(int l = ligne; l < matrix.length;l++)
		{
			if(l == ligne)
				continue;
			if(matrix[l][colonne] == joueur)
				nombre_cases_O++;
			else 
				break;
		}
		
		if(nombre_cases_O == 2)
			danger -= 300;
		else if(nombre_cases_O == 3)
			danger -= 600;
		
		
		if(joueur == Jeu.O)
			return danger;
		return danger * -1;
	}
	
	protected static int danger_dd(int[][] matrix, int ligne, int colonne, int joueur)
	{
		int danger = 0;
		int nombre_cases_vide = 1;
		int nombre_cases_O = 0;
		
		//Vers le haut a droite
		for(int l = ligne; l > 0 ; l--)
		{
			for(int col = colonne; col < matrix[l].length;col++)
			{
				if(l == ligne && col == colonne)
					continue;
				if(matrix[l][colonne] == joueur)
					nombre_cases_O++;
				else if(matrix[l][colonne] == 0)
					nombre_cases_vide++;
				else 
					break;
			}
		}
			
		//Vers le bas a gauche
		for(int l = ligne; l < matrix.length ; l++)
		{
			for(int col = colonne; col > 0; col--)
			{
				if(l == ligne && col == colonne)
					continue;
				if(matrix[l][colonne] == joueur)
					nombre_cases_O++;
				else if(matrix[l][colonne] == 0)
					nombre_cases_vide++;
				else 
					break;
			}
		}
		
		if(nombre_cases_O == 2 && nombre_cases_vide >= 2)
			danger -= 200;
		else if(nombre_cases_O >= 3)
			danger -= 400;

		if(joueur == Jeu.O)
			return danger;
		return danger * -1;
	}
	
	protected static int danger_dg(int[][] matrix, int ligne, int colonne, int joueur)
	{
		int danger = 0;
		int nombre_cases_vide = 1;
		int nombre_cases_O = 0;
		
		//Vers le haut a gauche
		for(int l = ligne; l > 0 ; l--)
		{
			for(int col = colonne; col > 0;col--)
			{
				if(l == ligne && col == colonne)
					continue;
				if(matrix[l][colonne] == joueur)
					nombre_cases_O++;
				else if(matrix[l][colonne] == 0)
					nombre_cases_vide++;
				else 
					break;
			}
		}
			
		//Vers le bas a droite
		for(int l = ligne; l < matrix.length ; l++)
		{
			for(int col = colonne; col < matrix[l].length; col++)
			{
				if(l == ligne && col == colonne)
					continue;
				if(matrix[l][colonne] == joueur)
					nombre_cases_O++;
				else if(matrix[l][colonne] == 0)
					nombre_cases_vide++;
				else 
					break;
			}
		}
		
		if(nombre_cases_O == 2 && nombre_cases_vide >= 2)
			danger -= 200;
		else if(nombre_cases_O >= 3)
			danger -= 400;
		
		if(joueur == Jeu.O)
			return danger;
		return danger * -1;
	}
}
