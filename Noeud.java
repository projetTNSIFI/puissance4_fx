package com;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

//Classe permettant de générer l'arbre contenant tous les plateaux possibles.
public class Noeud {
	public static final int UNDEFINED=9999999;//Valeur permettant de savoir si la valeur d'un noeud a été trouvée ou non.
	public static final int MIN=-1;//NOEUD MIN
	public static final int MAX=1;//NOEUD MAX
	protected Noeud pere;//Noeud pere
	protected List<Noeud> fils;//Liste des noeuds fils
	protected Plateau plateau; //Copie du plateau contenant un coup qui a été joué.
	protected int valeur_noeud;
	protected int type;//Noeud MAX ou noeud MIN
	
	public Noeud(Plateau p, int type)
	{
		pere = null;
		fils = new ArrayList<Noeud>();
		plateau = new Plateau(p);
		valeur_noeud = UNDEFINED;
		this.type = type;
	}
	
	public Noeud(Plateau p, Noeud pere, int type)
	{
		this.pere = pere;
		fils = new ArrayList<Noeud>();
		plateau = new Plateau(p);
		valeur_noeud = UNDEFINED;
		this.type = type;
	}
	
	public Noeud(Plateau p, int type, int valeur_noeud)
	{
		pere = null;
		fils = new ArrayList<Noeud>();
		plateau = new Plateau(p);
		this.valeur_noeud = valeur_noeud;
		this.type = type;
	}
	
	public Noeud(Plateau p, Noeud pere, int type, int valeur_noeud)
	{
		this.pere = pere;
		fils = new ArrayList<Noeud>();
		plateau = new Plateau(p);
		this.valeur_noeud = valeur_noeud;
		this.type = type;
	}
	
	//On genere les noeuds fils et on les ajoute à la liste des noeuds fils.
	protected void genererFils()
	{
		int[][] matrix = plateau.getPlateau();
		
		//Le fils contient le coup du joueur du prochain tour.
		int joueur = (type == MIN) ? Jeu.X : Jeu.O;
		
		//Parmi tous les coups possibles
		for(int i = 0;i < matrix.length;i++)
		{
			for(int j = 0;j < matrix[i].length;j++)
			{
				if(matrix[j][i]!=0)
					continue;
				
				//Si aucun coup n'a été joué à cet emplacement du plateau, on l'ajoute aux fils du noeud courant (en inversant le type du noeud)
				Noeud nouveau = new Noeud(this.plateau, this, type * -1);
				nouveau.plateau.placer(joueur, i, j);//On joue dans une case libre.
				this.fils.add(nouveau);//ajout du noeud fils aux noeuds de l'arbre.
			}
		}
	}
	
	public List<Noeud> getFils()
	{
		return fils;
	}
	
	public boolean estFeuille()
	{
		return fils.isEmpty();
	}
	
	//Retourne le noeud contenant la valeur maximale parmi les fils du noeud courant.
	public Noeud filsMax()
	{
		if(estFeuille())
			return null;
		Noeud filsMax = fils.get(0);
		for(Noeud f : fils)
		{
			if(f.valeur_noeud > filsMax.valeur_noeud)
				filsMax = f;
		}
		return filsMax;
	}
	
	
	public Noeud filsMin()
	{
		if(estFeuille())
			return null;
		Noeud filsMin = fils.get(0);
		for(Noeud f : fils)
		{
			if(f.valeur_noeud < filsMin.valeur_noeud)
				filsMin = f;
		}
		return filsMin;
	}
}
