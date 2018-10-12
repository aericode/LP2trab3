import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * Write a description of class Fish here.
 * 
 * NOTE: This should serve as a superclass to all specific tyes of fish
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Fish
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
    protected int age;
    // Whether the fish is alive or not.
    protected boolean alive;
    // Encapsulated coordinates for this fish's spot
    protected Location location;
    // The ocean occupied.
    protected Ocean ocean;
    // The fish's food level, which is increased by eating.
    protected int foodLevel;



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


    /**
     * Generates a fish of the calling subclass' class
     * @return fish created based on the subclasses' class
     */
    protected abstract Fish spawnYoung(Ocean ocean,Location loc);


    /**
     * Abstract method for fishes to do their specific actions determined in subclasses
     * @param newFishes lists the fishes bred by the individual, allowing them
     * to be added to the main list at the end of each iteration
     */
	public abstract void act(List<Fish> newFishes);


    public boolean isAlive(){
    	return alive;
    }



	protected void giveBirth(List<Fish> newFishes)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = ocean.getFreeAdjacentLocations(location);
        int births = breed();

        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fish young = spawnYoung(ocean,loc);
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
     * A fish can breed if it has reached the breeding age.
     */
    protected boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }



    protected void setDead()
    {
        alive = false;
        if(location != null) {
            ocean.clear(location);
            location = null;
            ocean = null;
        }
    }
}
