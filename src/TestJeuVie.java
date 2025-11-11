import gui.Oval;
import gui.Simulable;
import gui.GUISimulator;
import java.awt.*;
import gui.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TestJeuVie {
    public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (500 , 550 , Color . BLACK ) ;
        gui.setSimulable (new GrilleSimulator (gui)) ;
    }
}

class GrilleSimulator implements Simulable{
    private GUISimulator gui;
    private Grille grille;

    public GrilleSimulator(GUISimulator gui){
        this.gui = gui;
        this.grille = new Grille(10,10);
        draw();
    }

    @Override
    public void next(){
        grille.updateCasesGrille();
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
                if (grille.getelement(i,j) == 1){
                    gui.addGraphicalElement(new Rectangle(j*50, i*50,Color.decode("#1f77b4"),
                            Color.decode("#1f77b4"), 50, 50));
                }
                else{
                    gui.addGraphicalElement(new Rectangle(j*50, i*50,Color.WHITE,
                            Color.WHITE, 50, 50));
                }
            }
        }
    }
}