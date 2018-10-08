
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
    private static int BREEDING_AGE;
    // The age to which a fish can live.
    private static int MAX_AGE;
    // The likelihood of a fish breeding.
    private static double BREEDING_PROBABILITY;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE;


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
		age = 0;
        alive = true;
        foodLevel = 7;

        //SETLOCATION
        this.location = location;
        ocean.place(this,location);
	}

	public void act()
    {
        //incrementAge();
        if(alive) {          
            // Try to move into a free location.
            Location newLocation = ocean.freeAdjacentLocation(location);
            if(newLocation != null) {
                this.location = location;
        		ocean.place(this,location);
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
}
