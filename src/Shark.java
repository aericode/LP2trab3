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
	
    public Shark(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

	public Shark(Ocean ocean, Location location,int age)
	{
		super(ocean, location, age);
	}

	protected Fish spawnYoung(Ocean ocean,Location loc){
		Shark young = new Shark(ocean, loc);
		return young;
	}

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



	public void act(List<Fish> newFishes)
    {
        incrementAge();
        incrementHunger();
        if(alive) {
        	giveBirth(newFishes);          
            // Try to move into a free location.
            Location newLocation = findTuna(location);
            if(newLocation == null) { 
                newLocation = findSardine(location);
            }
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

}
