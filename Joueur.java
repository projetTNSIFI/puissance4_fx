package base;

import com.Plateau;

import javafx.stage.Stage;

public abstract class Joueur {
	protected String nom;
	protected int pion;
	
	public Joueur(String nom, int pion)
	{
		this.nom = nom;
		this.pion = pion;
	}
	
	public abstract void jouer(Stage rootStage, Plateau plateau);
	
	public String getNom()
	{
		return nom;
	}
}
