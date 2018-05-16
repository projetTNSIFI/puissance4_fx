package com;
import com.Jeu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Main extends Application{

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT=  800;
	public static void main(String[] args) {
		Application.launch(Main.class,args);
	}

	@Override
	public void start(Stage rootStage) throws Exception {
		// TODO Auto-generated method stub
		Plateau plateau = new Plateau();
		Thread jeu = new Thread(new Jeu(rootStage, plateau));
		
		rootStage.setTitle("PUISSANCE 4");
		
		//Création du groupe principal.
		Group root = new Group();
		GridPane grid = new GridPane();
		grid.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//Création de la scène dans laquelle on incorpore le groupe principal ( root ) 
		Scene scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT,Color.BLACK);
		
		//Ajout de la scene au stage principal
		rootStage.setScene(scene);
		
		//Ajout de la grille au groupe principal.
		root.getChildren().add(grid);
		
		//Affichage du plateau de départ.
		plateau.afficher(rootStage);
		
		//Ouverture de la fenêtre.
		rootStage.show();
		
		//Début du jeu.
		jeu.start();
	}

}
