import gui.Oval;
import gui.Simulable;
import gui.GUISimulator;
import java.awt.*;
import gui.Rectangle;

import java.util.*;

public class TestJeuVie {
    public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (500 , 550 , Color . BLACK ) ;
        gui.setSimulable (new GrilleSimulator (gui, 1)) ;
    }
}

class GrilleSimulator implements Simulable{
    private GUISimulator gui;
    private Grille grille;
    private int nombreEtats;
    Map<Integer, Color> couleurEtat= new HashMap<>();

    public GrilleSimulator(GUISimulator gui, int nombreEtats){
        this.gui = gui;
        this.grille = new Grille(10,10);
        this.nombreEtats = nombreEtats;
        if (nombreEtats == 1){
            couleurEtat.put(0, Color.WHITE);
            couleurEtat.put(1,Color.decode("#1f77b4"));
        }
        else{
            // On suppose qu'on forcément 4 états
            couleurEtat.put(0, Color.decode("#FFFFFF"));
            couleurEtat.put(1, Color.decode("#BFBFBF"));
            couleurEtat.put(2, Color.decode("#4D4D4D"));
            couleurEtat.put(3, Color.decode("#000000"));
        }
        draw();
    }

    @Override
    public void next(){
        grille.updateCasesGrille(this.nombreEtats);
        draw();
    }

    @Override
    public void restart(){
        grille.grilletoinit();
        draw();
    }

    public void draw(){
        gui.reset();
        for (int i = 1; i < grille.getn() +1; i++){
            for (int j =1; j < grille.getm() + 1;j++){
                gui.addGraphicalElement(new Rectangle(j*50, i*50,Color.BLACK,
                        couleurEtat.get(grille.getelement(i,j)), 50, 50));

            }
        }
    }
}