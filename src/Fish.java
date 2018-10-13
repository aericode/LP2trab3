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

	// Initial hunger of a fish
	private static int INIT_HUNGER = 4;
	// The age at which a fish can start to breed.
    private static int BREEDING_AGE = 10;
    // The age to which a fish can live.
    private static int MAX_AGE = 140;
    // The likelihood of a fish breeding.
    private static double BREEDING_PROBABILITY = 0.20;
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
    // The fish's food level, which is increased by eating. How many turns can it take without eating before he dies.
    protected int foodLevel;



	/**
	 * Constructor for objects of class Fish (used for newborn fish initialization)
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param location encapsulated location for easy access and return
	 */
	public Fish(Ocean ocean, Location location)
	{
		age = 0;
        alive = true;
        foodLevel = INIT_HUNGER;
        this.ocean = ocean;

        setLocation(location);
	}

    /**
     * Overloaded constructor for objects of class Fish (used at the populate method, to make breeding age fish)
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param location encapsulated location for easy access and return
     * @param age at what age is your fish is born
	 */
	public Fish(Ocean ocean, Location location, int age)
	{
		this.age = age;
        alive = true;
        foodLevel = INIT_HUNGER;
        this.ocean = ocean;

        setLocation(location);
	}

    /**
     * Realocation of the fish in the ocean matrix
     */
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

    /**
     * Access method for the alive variable
     * @return the alive atribute
     */
    public boolean isAlive(){
    	return alive;
    }


    /**
     * Fills a list with a random amount of newborn fish, already spawned at the needed location
     * @param newFishes lists the fishes bred by the individual, allowing them to be added to the main list at the end of each iteration
     */
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

    /**
     * Lowers the current food level, if it reaches zero the function kills it
     */
    protected void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }

    /**
     * Raises the fish age, and kills it if it reaches the age limit
     */
    protected void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    /**
     * Removes the fish from the grid, and clears it's location
     */
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
