import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class TestBalls{
    public static void main(String[] argv){
        ArrayList<Point> L = new ArrayList<Point>();
        GUISimulator gui = new GUISimulator (500 , 500 , Color . BLACK ) ;
        L.add(new Point(5,6));
        L.add(new Point(13,2));
        L.add(new Point(10,11));

        Balls B = new Balls(L, 1000, 1000);
        System.out.println(B);
        B.translate();
        System.out.println(B);
        B.translate();
        System.out.println(B);
        B.translate();
        System.out.println(B);

        B.reInit();
        System.out.println(B);
    }
}

class Balls {
    private ArrayList<Point> balls;
    private ArrayList<Point> initialPos = new ArrayList<>();
    private ArrayList<Point> dxParPoint = new ArrayList<>();
    private int width;
    private int height;

    public Balls(ArrayList<Point> balls, int width, int height){
        SaveInit(balls);
        this.balls = balls;
        for (int i = 0; i < balls.size(); i++){
            dxParPoint.add(new Point(10,10));
        }
        this.width = width;
        this.height = height;
    }

    public void SaveInit(ArrayList<Point> Lballs){
        for (Point p : Lballs){
            initialPos.add(new Point((int) p.getX(), (int) p.getY()));
        }
    }
    public void translate(){
        for (int i = 0; i < balls.size(); i++){
            Point ball = balls.get(i);
            Point coordTranslation = dxParPoint.get(i);
            if (ball.getY() + coordTranslation.getY() > height - 100 || ball.getY() + coordTranslation.getY() < 0){
                coordTranslation.y *= -1;
            }
            if (ball.getX() + coordTranslation.getX() > width || ball.getX() + coordTranslation.getX() < 0){
                coordTranslation.x *= -1;
            }

            ball.translate((int) coordTranslation.getX(), (int) coordTranslation.getY());
        }
    }

    public void reInit(){
        Iterator<Point> iter_pt = initialPos.iterator();
        for (Point p : balls){
            Point pt_init = iter_pt.next();
            p.setLocation(pt_init);
        }
        for (int i = 0; i < dxParPoint.size(); i++){
            dxParPoint.get(i).x = 10;
            dxParPoint.get(i).y = 10;
        }
    }


    @Override
    public String toString(){
        String res = "[";
        for (Point ball : this.balls){
            res += ball.toString();
        }
        res += "]";
        return res;
    }

    ArrayList<Point> getArray(){
        return this.balls;
    }
}
