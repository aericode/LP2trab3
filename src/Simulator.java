import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * (Fill in description and author info here)
 */

public class Simulator
{
    private Ocean ocean;
    private SimulatorView simView;

    private List<Fish> fishes;
    
    
    public static void main(String[] args) 
    {
        Simulator sim = new Simulator(50, 60);
        sim.run(1000);
    }
    
    
    
    public Simulator(int height, int width)
    {
        fishes = new ArrayList<Fish>(); //adiciona a LISTA onde especies de peixes ficarão
        ocean = new Ocean(height, width); //equivalente a Field

        simView = new SimulatorView(height, width); //interface gráfica
        
        // define in which color fish should be shown
        simView.setColor(Shark.class  , Color.gray);
        simView.setColor(Sardine.class, Color.cyan);
        simView.setColor(Tuna.class   , Color.red);
    }
    
    public void run(int steps)
    {
        // put the simulation main loop here
        
        simView.showStatus(0, ocean);
    }
}
