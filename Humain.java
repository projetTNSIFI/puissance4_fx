package com;

import base.Joueur;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Humain extends Joueur{
	
	public Humain(String nom, int pion)
	{
		super(nom,pion);
	}
	
	//Tour du joueur humain.
	public void jouer(Stage rootStage, Plateau plateau) 
	{
		
		Scene scene= rootStage.getScene();
		Group group = (Group) scene.getRoot();
		GridPane grid = (GridPane) group.getChildren().get(0);
		
		//On autorise le clic sur chaque bouton
		for(int i = 0;i < grid.getChildren().size();i++)
		{
			Pion pion = (Pion) grid.getChildren().get(i);
			//Fonction qui devra être réalisé lors du clic sur le bouton.
			pion.setOnMouseReleased(new EventHandler<MouseEvent>()
					{
						@Override
						public void handle(MouseEvent e)
						{
							int i = pion.i;
							int j = pion.j;
							if(plateau.getPlateau()[i][j] == 0)
							{
								plateau.placer(Jeu.O, j);
								pion.setFill(Color.BLUE);
								Jeu.changerJoueur();
							}
						}
					});
		}
	}
}
