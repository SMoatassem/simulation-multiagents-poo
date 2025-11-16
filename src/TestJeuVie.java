import gui.Oval;
import gui.Simulable;
import gui.GUISimulator;
import java.awt.*;
import gui.Rectangle;

import java.util.*;

public class TestJeuVie {
    public static void main ( String [] args ) {
        // Création d’une fenêtre graphique de 500x550 pixels
        GUISimulator gui = new GUISimulator (500 , 550 , Color . BLACK ) ;

        // On associe un simulateur de grille (Jeu de la Vie / automate cyclique)
        gui.setSimulable (new GrilleSimulator (gui, 1)) ;
    }
}

class GrilleSimulator implements Simulable{
    private GUISimulator gui;       // Interface graphique
    private Grille grille;          // Modèle logique (automate)
    private int nombreEtats;        // 1 pour Jeu de la Vie, >1 pour automate cyclique
    Map<Integer, Color> couleurEtat= new HashMap<>(); // Couleur correspondant à chaque état

    public GrilleSimulator(GUISimulator gui, int nombreEtats){
        this.gui = gui;

        // Création d'une grille 10x10 (bordures gérées dans Grille)
        this.grille = new Grille(10,10);

        this.nombreEtats = nombreEtats;

        // Couleurs pour chaque état de la grille
        if (nombreEtats == 1){
            // Jeu de la Vie : 0 = mort, 1 = vivant
            couleurEtat.put(0, Color.WHITE);
            couleurEtat.put(1,Color.decode("#1f77b4"));
        }
        else{
            // Automate cyclique : 4 états (exemple de palette en niveaux de gris)
            couleurEtat.put(0, Color.decode("#FFFFFF"));
            couleurEtat.put(1, Color.decode("#BFBFBF"));
            couleurEtat.put(2, Color.decode("#4D4D4D"));
            couleurEtat.put(3, Color.decode("#000000"));
        }

        // Premier affichage
        draw();
    }

    @Override
    public void next(){
        // Passe à l’état suivant du modèle
        grille.updateCasesGrille(this.nombreEtats);

        // Redessine la grille mise à jour
        draw();
    }

    @Override
    public void restart(){
        // Retour à l’état initial sauvegardé dans Grille
        grille.grilletoinit();

        // Mise à jour graphique
        draw();
    }

    // Affiche la grille sur l’interface graphique
    public void draw(){
        gui.reset(); // Efface tout le contenu graphique actuel

        // On parcourt les cellules utiles (sans les bordures externes)
        for (int i = 1; i < grille.getn() +1; i++){
            for (int j =1; j < grille.getm() + 1;j++){

                // Chaque cellule est représentée par un rectangle de 50x50 pixels
                gui.addGraphicalElement(
                        new Rectangle(
                                j*50,            // Position X
                                i*50,            // Position Y
                                Color.BLACK,     // Bordure
                                couleurEtat.get(grille.getelement(i,j)), // Couleur de fond selon état
                                50,              // Largeur
                                50               // Hauteur
                        )
                );
            }
        }
    }
}
