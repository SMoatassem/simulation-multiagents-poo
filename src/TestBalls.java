import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class TestBalls{
    public static void main(String[] argv){
        ArrayList<Point> L = new ArrayList<Point>();

        // Création de la fenêtre graphique (GUI)
        GUISimulator gui = new GUISimulator (500 , 500 , Color . BLACK ) ;

        // Trois balles initiales
        L.add(new Point(5,6));
        L.add(new Point(13,2));
        L.add(new Point(10,11));

        // Création de l’objet Balls avec une zone large 1000x1000
        Balls B = new Balls(L, 1000, 1000);

        // Test des déplacements et réinitialisations
        System.out.println(B);
        B.translate();
        System.out.println(B);
        B.translate();
        System.out.println(B);
        B.translate();
        System.out.println(B);

        // On revient aux positions initiales
        B.reInit();
        System.out.println(B);
    }
}

class Balls {
    private ArrayList<Point> balls;        // Liste des balles (positions courantes)
    private ArrayList<Point> initialPos = new ArrayList<>(); // Positions initiales (copiées)
    private ArrayList<Point> dxParPoint = new ArrayList<>(); // Vecteurs de déplacement de chaque balle
    private int width;   // Largeur de la zone de déplacement
    private int height;  // Hauteur de la zone de déplacement

    public Balls(ArrayList<Point> balls, int width, int height){
        // Sauvegarde des positions initiales
        SaveInit(balls);

        this.balls = balls;

        // Pour chaque balle, on lui affecte un vecteur déplacement (10,10)
        for (int i = 0; i < balls.size(); i++){
            dxParPoint.add(new Point(10,10));
        }

        this.width = width;
        this.height = height;
    }

    // Sauvegarde des positions initiales dans une liste séparée
    public void SaveInit(ArrayList<Point> Lballs){
        for (Point p : Lballs){
            // On copie les coordonnées : pas de référence partagée
            initialPos.add(new Point((int) p.getX(), (int) p.getY()));
        }
    }

    // Déplace chaque balle et gère les collisions contre les bords
    public void translate(){
        for (int i = 0; i < balls.size(); i++){
            Point ball = balls.get(i);
            Point coordTranslation = dxParPoint.get(i); // vecteur (dx, dy)

            // Gestion des rebonds verticaux
            if (ball.getY() + coordTranslation.getY() > height - 100 || ball.getY() + coordTranslation.getY() < 0){
                coordTranslation.y *= -1; // inversion dy
            }

            // Gestion des rebonds horizontaux
            if (ball.getX() + coordTranslation.getX() > width || ball.getX() + coordTranslation.getX() < 0){
                coordTranslation.x *= -1; // inversion dx
            }

            // Application de la translation
            ball.translate((int) coordTranslation.getX(), (int) coordTranslation.getY());
        }
    }

    // On remet les balles à leurs positions initiales
    public void reInit(){
        Iterator<Point> iter_pt = initialPos.iterator();

        // On applique à chaque balle sa position initiale sauvegardée
        for (Point p : balls){
            Point pt_init = iter_pt.next();
            p.setLocation(pt_init);
        }

        // On remet les vecteurs de déplacement à (10,10)
        for (int i = 0; i < dxParPoint.size(); i++){
            dxParPoint.get(i).x = 10;
            dxParPoint.get(i).y = 10;
        }
    }

    // Affichage textuel des balles
    @Override
    public String toString(){
        String res = "[";
        for (Point ball : this.balls){
            res += ball.toString();
        }
        res += "]";
        return res;
    }

    // Récupère la liste des balles (utile pour la GUI externe)
    ArrayList<Point> getArray(){
        return this.balls;
    }
}
