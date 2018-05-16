package com;

import com.Plateau;

import base.Joueur;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Jeu implements Runnable {
	public static final int O=-1;
	public static final int X=1;
	//public static Scanner scanner = new Scanner(System.in);
	
	public static int joueur = Jeu.O;
	protected static int victoire = 0;
	
	protected Stage rootStage;
	protected Plateau plateau;
	
	public Jeu(Stage stage, Plateau plateau)
	{
		this.rootStage = stage;
		this.plateau = plateau;
	}
	
	@Override
	public void run()
	{
		
		//Création de deux joueurs 
		Joueur humain = new Humain("Joueur",O);
		Joueur ordinateur = new Ordinateur("Joueur",X,2);
		
		//Jeu
		while(!plateau.complet())
		{
			
			//Tour du joueur
			if(joueur == Jeu.O)
				humain.jouer(rootStage, plateau);
			else
				ordinateur.jouer(rootStage, plateau);
			
			//Affichage du plateau.
			Platform.runLater(new Runnable()
			{
				@Override 
				public void run()
				{
					plateau.afficher(rootStage);
					Thread.currentThread().interrupt();
				}
			});
			
			//Vérification de la victoire
			victoire = plateau.victoire();
			if(victoire != 0)
				break;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(victoire != 0)
		{
			System.out.println("Victoire du joueur " + (victoire == Jeu.X ? "ROUGE" : "BLEU"));
			//Affichage du plateau.	
			afficherGagnant("Victoire du joueur " + (victoire == Jeu.X ? "ROUGE" : "BLEU"));
		}
		else
		{
			System.out.println("Match nul");
			afficherGagnant("Match nul.");
		}
			
		
		Thread.currentThread().interrupt();
		//Fermeture du scanner.
		//scanner.close();
	}
	
	public static void changerJoueur() 
	{
		joueur *= -1;
	}
	
	public void afficherGagnant(String message)
	{
		Platform.runLater(new Runnable()
		{
			@Override 
			public void run()
			{
				 Alert alert = new Alert(Alert.AlertType.INFORMATION);
		         alert.setTitle("Fin de partie");
		         alert.setHeaderText(message);
		         alert.show();
			}
		});
	}
}
