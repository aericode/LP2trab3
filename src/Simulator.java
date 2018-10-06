import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * (Fill in description and author info here)
 */

public class Simulator
{

    // The probability that a fox will be created in any given grid position.
    private static final double SHARK_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double TUNA_CREATION_PROBABILITY = 0.03;
    // The probability that a fox will be created in any given grid position.
    private static final double SARDINE_CREATION_PROBABILITY = 0.05;



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
        simView.setColor(Sardine.class, Color.blue);
        simView.setColor(Tuna.class   , Color.red);

        Fish tuna = new Tuna();
        ocean.putFishAt(1,1,tuna);
        Fish sardine = new Sardine();
        ocean.putFishAt(2,2,sardine);
        Fish shark = new Shark();
        ocean.putFishAt(3,3,shark);

        //ocean.clear();
    }
    

    public void run(int steps)
    {
        // put the simulation main loop here
        
        simView.showStatus(0, ocean);
    }

    /*
    private void populate() //ORIGINAL PARA REFERENCIA
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    foxes.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    rabbits.add(rabbit);
                }
                // else leave the location empty.
            }
        }
    } //ORIGINAL PARA REFERENCIA

    private void populate()
    {
        Random rand = Randomizer.getRandom();
        ocean.clear();
        for(int row = 0; row < field.getHeight(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= SHARK_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    foxes.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    rabbits.add(rabbit);
                }
                // else leave the location empty.
            }
        }
    }
    */

}
