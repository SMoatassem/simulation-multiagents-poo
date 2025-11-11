import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TestSchelling{
    public static void main(String[] argv){
        GUISimulator gui = new GUISimulator (500 , 550 , Color . BLACK ) ;
        gui.setSimulable (new SchellingSimulator (gui, 2)) ;
    }
}

class SchellingSimulator implements Simulable {
    GUISimulator gui;
    int k;
    private SchellingGrille sGrille;
    Map<Integer, Color> couleurEtat= new HashMap<>();

    public SchellingSimulator(GUISimulator gui, int k){
        this.gui = gui;
        this.k = k;
        this.sGrille = new SchellingGrille(10,10);
        couleurEtat.put(0, Color.decode("#FFFFFF"));
        couleurEtat.put(1, Color.decode("#2A9D8F"));
        couleurEtat.put(2, Color.decode("#1D3557"));
        couleurEtat.put(3, Color.decode("#FFD166"));
        couleurEtat.put(4, Color.decode("#9B5DE5"));

        draw();
    }
    @Override
    public void next(){
        sGrille.updateCasesGrille(k);
        draw();
    }
    @Override
    public void restart(){
        sGrille.grilletoinit();
        draw();
    }


    public void draw(){
        gui.reset();
        for (int i = 1; i < sGrille.getn() +1; i++){
            for (int j =1; j < sGrille.getm() + 1;j++){
                gui.addGraphicalElement(new Rectangle(j*50, i*50,Color.BLACK,
                        couleurEtat.get(sGrille.getelement(i,j)), 50, 50));

            }
        }
    }
}
