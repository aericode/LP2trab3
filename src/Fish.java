import java.util.List;
import java.util.Random;

/**
 * Write a description of class Fish here.
 * 
 * NOTE: This should serve as a superclass to all specific tyes of fish
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fish
{
	// The age at which a fish can start to breed.
    private static int BREEDING_AGE = 10;
    // The age to which a fish can live.
    private static int MAX_AGE = 140;
    // The likelihood of a fish breeding.
    private static double BREEDING_PROBABILITY = 0.35;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE = 5;
    //Randomizer, shared among all fish
    private static Random rand = new Random();



    // The fish's age.
    private int age;
    // Whether the fish is alive or not.
    private boolean alive;
    // Encapsulated coordinates for this fish's spot
    private Location location;
    // The ocean occupied.
    private Ocean ocean;
    // The fish's food level, which is increased by eating.
    private int foodLevel;



	/**
	 * Constructor for objects of class Fish
	 */
	public Fish(Ocean ocean, Location location)
	{
		age = 20;
        alive = true;
        foodLevel = 7;//COLOCAR VARIAVEL DE STARVATION NO LUGAR DO 7, N DE PASSOS SEM MORRER
        this.ocean = ocean;

        setLocation(location);
	}

	public void setLocation(Location newLocation){
		if(location != null) {
            ocean.clear(location);
        }
		location = newLocation;
        ocean.place(this,newLocation);
	}

	public void act(List<Fish> newFishes)
    {
        //incrementAge();
        if(alive) {
        	giveBirth(newFishes);          
            // Try to move into a free location.
            Location newLocation = ocean.freeAdjacentLocation(location);
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                //setDead();
            }
        }
    }

    public boolean isAlive(){
    	return alive;
    }



	private void giveBirth(List<Fish> newFishes)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = ocean.getFreeAdjacentLocations(location);
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fish young = new Fish(ocean,loc);
            newFishes.add(young);
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A fox can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }



    private void setDead()
    {
        alive = false;
        if(location != null) {
            ocean.clear(location);
            location = null;
            ocean = null;
        }
    }
}
