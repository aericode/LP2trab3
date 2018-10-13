import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a sardine.
 * sardines age, move, breed, and die.
 * They eat plankton.
 * They exhibit flocking behaviour - they tend to seek company. 
 * If they spot a predator close by, they panic.
 * 
 */
public class Sardine extends Fish
{
    int FOOD_MAX = 7;

    /**
     * Constructor for objects of class Sardine (used for newborn fish initialization)
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param location encapsulated location for easy access and return
     */
	public Sardine(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

    /**
     * Overloaded constructor for objects of class Sardine (used at the populate method, to make breeding age fish)
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param location encapsulated location for easy access and return
     * @param age at what age is your fish is born
     */
	public Sardine(Ocean ocean, Location location,int age)
	{
		super(ocean, location, age);
	}

    /**
     * Gives a new fish from the subclass for the super to add to the ocean matrix
     * @return the new fish spawned
     */
	protected Fish spawnYoung(Ocean ocean,Location loc){
		Sardine young = new Sardine(ocean, loc);
		return young;
	}

    /**
     * Defines sardines' behavior, flocking, regular movement and feeding(unimplemented).
     * @param newFishes newborn fishes will be added here to be added to the ocean matrix later
     */
	public void act(List<Fish> newFishes)
    {
        incrementAge();
        //incrementHunger();
        if(alive) {
            //eatAlgae();
        	giveBirth(newFishes);          
            // Looks for a region with sardines around (flocking)
            Location newLocation = ocean.getSardinefulLocation(location);
            if(newLocation == null){
                //looks for any free location
                newLocation = ocean.freeAdjacentLocation(location);
            }
            if(newLocation != null) {
                //sets the location
                setLocation(newLocation);
            }
            if(newLocation == null){
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * tries to subtract algae from it's current spot.
     */
    private void eatAlgae(){
        Algae algae = ocean.getAlgaeAt(location);
        if(algae.getEaten()){
            foodLevel = FOOD_MAX;
        }
    }
    
}
