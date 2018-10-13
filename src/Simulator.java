import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

/**
 * (Fill in description and author info here)
 */

public class Simulator
{

    // The probability that a Shark will be created in any given grid position.
    private static final double SHARK_CREATION_PROBABILITY = 0.02;
    // The probability that a Tuna will be created in any given grid position.
    private static final double TUNA_CREATION_PROBABILITY = 0.03;
    // The probability that a Sardine will be created in any given grid position.
    private static final double SARDINE_CREATION_PROBABILITY = 0.05;


    private int step;

    private Ocean ocean;
    private SimulatorView simView;

    private List<Fish> fishes;
    private List<Algae>algae_list;
    
    
    public static void main(String[] args) 
    {
        Simulator sim = new Simulator(50, 60);
        sim.run(10000);
        //sim.loneFish(10000);
    }
    
    
    
    public Simulator(int height, int width)
    {
        fishes = new ArrayList<Fish>(); //adiciona a LISTA onde especies de peixes ficarão
        ocean = new Ocean(height, width); //equivalente a Field

        simView = new SimulatorView(height, width); //interface gráfica
        
        // define in which color fish should be shown
        simView.setColor(Shark.class  , Color.blue);
        simView.setColor(Sardine.class, Color.green);
        simView.setColor(Tuna.class   , Color.red);

        populate();
        //startAlgae();

    }
    

    public void run(int stepNum)
    {
        // put the simulation main loop here
        while(step < stepNum){
            simulateOneStep();
            simView.showStatus(step, ocean);
        }
    }


    public void loneFish(int stepNum)
    {
        Fish tuna = new Tuna(ocean, ocean.randomAdjacentLocation(new Location(20,30)));
        fishes.add(tuna);
        // put the simulation main loop here
        while(step < stepNum){
            List<Fish> newFishes = new ArrayList<Fish>();
            tuna.act(newFishes);
            fishes.addAll(newFishes);

            //simulateOneStep();
            simView.showStatus(step, ocean);
        }
    }

    /**
    * Sets up a simulation with fishes placed at random spots
    */
    private void populate()
    {
        Random rand = new Random();
        ocean.clear();
        for(int row = 0; row < ocean.getHeight(); row++) {
            for(int col = 0; col < ocean.getWidth(); col++) {
                if(rand.nextDouble() <= SHARK_CREATION_PROBABILITY) {
                    Fish shark = new Shark(ocean, new Location(row,col), 10);
                    fishes.add(shark);
                }
                else if(rand.nextDouble() <= TUNA_CREATION_PROBABILITY) {
                    Fish tuna = new Tuna(ocean, new Location(row,col), 10);
                    fishes.add(tuna);
                }
                else if(rand.nextDouble() <= SARDINE_CREATION_PROBABILITY) {
                    Fish sardine = new Sardine(ocean, new Location(row,col), 10);
                    fishes.add(sardine);
                }

            }
        }
    }

    private void startAlgae()
    {
        Random rand = new Random();

        for(int row = 0; row < ocean.getHeight(); row++) {
            for(int col = 0; col < ocean.getWidth(); col++) {
                Algae algae = new Algae(ocean,new Location(row,col));
                algae_list.add(algae);
            }
        }
    }

    public void simulateOneStep()
    {
        step++;

        // Provide space for newborn fish.
        List<Fish> newFishes = new ArrayList<Fish>();        
        // Let all fishes act.
        for(Iterator<Fish> it = fishes.iterator(); it.hasNext(); ) {
            Fish fish = it.next();
            fish.act(newFishes);
            if(! fish.isAlive()) {
                it.remove();
            }
        }
        
        // Add the newly born fishes and rabbits to the main lists.
        fishes.addAll(newFishes);

        simView.showStatus(step, ocean);
    }

}
