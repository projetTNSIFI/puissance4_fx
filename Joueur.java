package base;

import com.Plateau;

public abstract class Joueur {
	protected String nom;
	protected int pion;
	
	public Joueur(String nom, int pion)
	{
		this.nom = nom;
		this.pion = pion;
	}
	
	public abstract void jouer(Plateau plateau);
	
	public String getNom()
	{
		return nom;
	}
}
