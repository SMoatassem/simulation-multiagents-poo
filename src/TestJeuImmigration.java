
import gui.GUISimulator;
import java.awt.*;


public class TestJeuImmigration {
    public static void main(String[] argv){
        GUISimulator gui = new GUISimulator (500 , 550 , Color . BLACK ) ;
        gui.setSimulable (new GrilleSimulator (gui, 4));
    }
}
