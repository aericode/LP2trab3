import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a shark.
 * Sharks age, move, breed, and die.
 * Sharks eat groper or herring but they prefer groper.
 * Sharks are loners - they prefer not to swim next to each other
 * @author Richard Jones and Michael Kolling
 */
public class Shark extends Fish
{

	int FOOD_MAX = 7;

    /**
     * Constructor for objects of class Shark (used for newborn fish initialization)
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param location location where the fish will be spawned
     */
    public Shark(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

    /**
     * Overloaded constructor for objects of class Shark (used at the populate method, to make breeding age fish)
     * @param ocean external fish matrix needed to make function calls that reach other fishes
     * @param location location where the fish will be spawned
     * @param age at what age is your fish is born
     */
	public Shark(Ocean ocean, Location location,int age)
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
		Shark young = new Shark(ocean, loc);
		return young;
	}

    /**
     * Looks around for sardine, eats it if found, and says the location where it was
     * @param location location where the shark is
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

    /**
     * Looks around for tuna, eats it if found, and says the location where it was
     * @param location location where the shark is
     * @return where was the eaten tuna or null if there was none
     */
    private Location findTuna(Location location)
    {
        List<Location> adjacent = ocean.adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Fish fish = ocean.getFishAt(where);
            if(fish instanceof Tuna) {
                Tuna tuna = (Tuna) fish;
                if(tuna.isAlive()) { 
                	//remove the sardine
                    tuna.setDead();
                    foodLevel = FOOD_MAX;

                    return where;
                }
            }
        }
        return null;
    }


    /**
     * Defines sharks' behavior, aging, feeding, avoiding other sharks, preference for tuna over sardine,
     * breeding, movement and death condition
     * @param newFishes newborn fishes will be added here to be added to the ocean matrix later
     */
	public void act(List<Fish> newFishes)
    {
        incrementAge();
        incrementHunger();
        if(alive) {
        	giveBirth(newFishes);          
            // Tries to find Tuna
            Location newLocation = findTuna(location);
            if(newLocation == null) { 
                // If it can't it tries to find Sardine
                newLocation = findSardine(location);
            }
            if(newLocation == null) {
                // If it can't it tries to move to a Sharkless region
                newLocation = ocean.getSharklessLocation(location);
            }
            if(newLocation == null){
                // If it can't it tries to move anywhere
                newLocation = ocean.freeAdjacentLocation(location);
            }
            if(newLocation != null) {
                // Moves to any of the previously searched locations
                setLocation(newLocation);
            }
            else {
                // If it can't it dies.
                setDead();
            }
        }
    }

}
