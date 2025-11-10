import gui.Oval;
import gui.Simulable;
import gui.GUISimulator;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TestBallsSimulator {
    public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (500 , 500 , Color . BLACK ) ;
        gui.setSimulable (new BallsSimulator (gui)) ;
    }
}

class BallsSimulator implements Simulable{
    Balls b = new Balls(new ArrayList<Point>(Arrays.asList(new Point(0,100), new Point(100,100), new Point(200, 100))));
    GUISimulator gui;


    public BallsSimulator(GUISimulator gui){
        this.gui = gui;
    }
    @Override
    public void next(){
        b.translate(10,10);
        draw();
        System.out.println(b);
    }

    @Override
    public void restart(){
        b.reInit();
        draw();
        System.out.println(b);
    }

    public void draw(){
        gui.reset();
        for (Point ball : b.Lballs){
            gui.addGraphicalElement(new Oval((int) ball.getX(), (int) ball.getY(),
                    Color.decode("#1f77b4"), Color.decode("#1f77b4"), 20, 20));
        }
    }
}

