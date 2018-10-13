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

    // In which step of the simulation are you
    private int step;

    // Stores tha fishes and location data
    private Ocean ocean;
    // Graphical interface
    private SimulatorView simView;

    // List for iterations
    private List<Fish> fishes;

    // Algae that Sardines eat
    private List<Algae>algae_list;
    

    public static void main(String[] args) 
    {
        //starts the simulation
        Simulator sim = new Simulator(50, 60);
        sim.run(10000);
    }
    
    
    /**
    * Constructor method for the Simulator, starts up the fish population
    * @param height Height of the simulation frame (in world units)
    * @param width  Width of the simulation frame (in world units)
    */
    public Simulator(int height, int width)
    {
        // Initializes the elements of the simulation
        fishes = new ArrayList<Fish>(); 
        ocean = new Ocean(height, width); 

        simView = new SimulatorView(height, width); 
        
        // define in which color fish should be shown
        simView.setColor(Shark.class  , Color.blue);
        simView.setColor(Sardine.class, Color.green);
        simView.setColor(Tuna.class   , Color.red);

        populate();
        //startAlgae();

    }
    

    /**
    * Iteration method to keep the simulation running
    * @param stepNum number of iterations before the simulation stops
    */
    public void run(int stepNum)
    {
        // put the simulation main loop here
        while(step < stepNum){
            simulateOneStep();
            simView.showStatus(step, ocean);
        }
    }

    /**
    * Debug method to test individual behaviors of a single fish
    * @param stepNum number of iterations before the simulation stops
    */
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

    /**
    * Initiates the algae coordinates and quantity
    */
    private void startAlgae()
    {

        for(int row = 0; row < ocean.getHeight(); row++) {
            for(int col = 0; col < ocean.getWidth(); col++) {
                Algae algae = new Algae(ocean,new Location(row,col));
                algae_list.add(algae);
            }
        }
    }

    /**
    * Global call for all fishes to do their predetermined behaviours
    */
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
