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
			courant.valeur_noeud = 0;
		
		//On attribue une valeur aux noeuds feuilles
		if(courant.estFeuille())
		{
			//On considère que l'adversaire ( le joueur ) jouera les O
			if(courant.plateau.victoire() == Jeu.X)
				courant.valeur_noeud = 1000;
			else if(courant.plateau.victoire() == Jeu.O)
				courant.valeur_noeud = -1000;
			else 
				courant.valeur_noeud = 0;			
		}
		else
		{
			//Sinon la valeur du noeud courant correspond au max ou au min de tous ses noeuds fils.
			if(courant.type == Noeud.MAX)//Noeud correspondant au tour de l'IA
			{
				for(Noeud f : courant.fils)
				{
					evaluer_noeuds(f, profondeur - 1);
				}
				courant.valeur_noeud = courant.filsMin().valeur_noeud;
			}
			else if(courant.type == Noeud.MIN)//Noeud correspondant au tour du joueur
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
}
