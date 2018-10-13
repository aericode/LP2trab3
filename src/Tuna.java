import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a tuna.
 * Tuna age, move, breed, and die.
 * They eat herring.
 * 
 * @author Richard Jones and Michael Kolling
 */
public class Tuna extends Fish
{
	
	int FOOD_MAX = 7;

    /**
     * Constructor for objects of class Tuna (used for newborn fish initialization)
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param location encapsulated location for easy access and return
     */
	public Tuna(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

    /**
     * Overloaded constructor for objects of class Tuna (used at the populate method, to make breeding age fish)
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param location encapsulated location for easy access and return
     * @param age at what age is your fish is born
     */
	public Tuna(Ocean ocean, Location location,int age)
	{
		super(ocean, location, age);
	}

    /**
     * Gives a new fish from the subclass for the super to add to the ocean matrix
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param loc location where the newborn needs to be spawneds
     * @return the new fish spawned
     */
	protected Fish spawnYoung(Ocean ocean,Location loc){
		Tuna young = new Tuna(ocean, loc);
		return young;
	}

    /**
     * Defines tunas' behavior, aging, feeding, breeding, movement and death condition
     * @param newFishes newborn fishes will be added here to be added to the ocean matrix later
     */
	public void act(List<Fish> newFishes)
    {
        incrementAge();
        incrementHunger();
        if(alive) {
        	giveBirth(newFishes);          
            // Try to move into a free location.
            Location newLocation = findSardine(location);
            if(newLocation == null) { 
                newLocation = ocean.freeAdjacentLocation(location);
            }
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Looks around for sardine, eats it if found, and says the location where it was
     * @param location location where the Tuna is
     * @return where was the eaten sardine or null if there was none
     */
	private Location findSardine(Location location)
    {
        List<Location> adjacent = ocean.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Fish fish = ocean.getFishAt(where);
            if(fish instanceof Sardine) {
                Sardine sardine = (Sardine) fish;
                if(sardine.isAlive()) { 
                	//remove the sardine
                    sardine.setDead();
                    foodLevel = FOOD_MAX;

                    return where;
                }
            }
        }
        return null;
    }

}
