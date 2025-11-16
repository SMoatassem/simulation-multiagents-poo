import gui.Oval;
import gui.Simulable;
import gui.GUISimulator;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TestBallsSimulator {
    public static void main ( String [] args ) {
        // Fenêtre graphique 500x500, fond noir
        GUISimulator gui = new GUISimulator (500 , 500 , Color . BLACK ) ;

        // On associe un simulateur de mouvement de balles
        gui.setSimulable (new BallsSimulator (gui)) ;
    }
}

class BallsSimulator implements Simulable{
    private GUISimulator gui; // Interface graphique
    private Balls b;          // Modèle logique : gestion des balles (positions, déplacement)

    public BallsSimulator(GUISimulator gui){
        this.gui = gui;

        // Initialisation de 5 balles avec différentes positions
        // On utilise la largeur et la hauteur de la fenêtre pour les limites
        this.b = new Balls(
                new ArrayList<Point>(
                        Arrays.asList(
                                new Point(251,100),
                                new Point(100,100),
                                new Point(200,100),
                                new Point(23,300),
                                new Point(3,88)
                        )
                ),
                (int) gui.getWidth(),    // limite horizontale
                (int) gui.getHeight()    // limite verticale
        );
    }

    @Override
    public void next(){
        // Mise à jour des positions (déplacement + rebonds)
        b.translate();

        // Mise à jour graphique
        draw();

        // Affichage console des coordonnées (debug)
        System.out.println(b);
    }

    @Override
    public void restart(){
        // Retour aux positions initiales
        b.reInit();

        // Mise à jour graphique
        draw();

        // Affichage console
        System.out.println(b);
    }

    // Dessine toutes les balles dans la GUI
    public void draw(){
        gui.reset(); // On efface les éléments graphiques actuels

        // Pour chaque balle, on dessine un cercle (Oval)
        for (Point ball : b.getArray()){
            gui.addGraphicalElement(
                    new Oval(
                            (int) ball.getX(),              // X
                            (int) ball.getY(),              // Y
                            Color.decode("#1f77b4"),        // couleur du contour
                            Color.decode("#1f77b4"),        // couleur du remplissage
                            20, 20                          // largeur et hauteur du cercle
                    )
            );
        }
    }
}
