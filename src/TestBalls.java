import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class TestBalls{
    public static void main(String[] argv){
        ArrayList<Point> L = new ArrayList<Point>();
        L.add(new Point(5,6));
        L.add(new Point(13,2));
        L.add(new Point(10,11));

        Balls B = new Balls(L);
        System.out.println(B);
        B.translate(5,2);
        System.out.println(B);
        B.translate(10,13);
        System.out.println(B);
        B.translate(-5,-2);
        System.out.println(B);

        B.reInit();
        System.out.println(B);
    }
}

class Balls {
    ArrayList<Point> Lballs;
    ArrayList<Point> PosInit = new ArrayList<>();

    public Balls(ArrayList<Point> Lballs){
        SaveInit(Lballs);
        this.Lballs = Lballs;
    }

    public void SaveInit(ArrayList<Point> Lballs){
        for (Point p : Lballs){
            PosInit.add(new Point((int) p.getX(), (int) p.getY()));
        }
    }
    public void translate(int dx, int dy){
        for (Point ball : this.Lballs){
            ball.translate(dx, dy);
        }
    }

    public void reInit(){
        Iterator<Point> iter_pt = PosInit.iterator();
        for (Point p : Lballs){
            Point pt_init = iter_pt.next();
            p.setLocation(pt_init);
        }
    }


    @Override
    public String toString(){
        String res = "";
        System.out.print("[");
        for (Point ball : this.Lballs){
            res += ball.toString();
        }
        res += "]";
        return res;
    }

}
