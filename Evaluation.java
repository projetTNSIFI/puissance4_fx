package com;
import java.util.List;

import com.Noeud;

//Classe qui va générer l'arbre des noeuds possibles et faire l'évaluation de ces noeuds.
public class Evaluation {
	
	/*Genere un arbre contenant tous les noeuds possibles sur une certaine profondeur.
	 * Le noeud n est le premier noeud de l'arbre ( racine ), la profondeur correspond à combiend d'itération de l'arbre nous souhaitons générer
	 * Le token correspond au tour à partir du quel on va générer les noeuds. ( varie entre 1 et 2 ).
	 */
	public static void genererArbre(Noeud n, int profondeur, int token)
	{
		Noeud courant = n;
		if(profondeur > 0)
		{
			courant.genererFils(token);
			
			//Récupération des noeuds fils au noeud courant
			List<Noeud> fils = courant.getFils();
			
			//A la fin d'une iteration, on change le tour 
			token = ((token == 1) ? 2 : 1);
			
			//Pour chacun des noeuds fils, on réitère le processus, mais on change le tour et la profondeur diminue.
			for(Noeud f : fils)
				genererArbre(f,profondeur-1,token);
			
		}
	}
	
	//Fonction qui va attribuer des valeurs aux noeuds 
}
