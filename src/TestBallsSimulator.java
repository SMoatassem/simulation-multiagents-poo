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
    private GUISimulator gui;
    private Balls b;


    public BallsSimulator(GUISimulator gui){
        this.gui = gui;
        this.b = new Balls(new ArrayList<Point>(Arrays.asList(new Point(251,100),
                new Point(100,100), new Point(200, 100), new Point(23,300),
                new Point(3,88))), (int) gui.getWidth(), (int) gui.getHeight());
    }
    @Override
    public void next(){
        b.translate();
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
        for (Point ball : b.getArray()){
            gui.addGraphicalElement(new Oval((int) ball.getX(), (int) ball.getY(),
                    Color.decode("#1f77b4"), Color.decode("#1f77b4"), 20, 20));
        }
    }
}

