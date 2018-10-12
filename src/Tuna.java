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

	public Tuna(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

	public Tuna(Ocean ocean, Location location,int age)
	{
		super(ocean, location, age);
	}

	protected Fish spawnYoung(Ocean ocean,Location loc){
		Tuna young = new Tuna(ocean, loc);
		return young;
	}

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
