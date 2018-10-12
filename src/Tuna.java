import java.util.List;
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

	protected Fish spawnYoung(Ocean ocean,Location loc){
		Tuna young = new Tuna(ocean, loc);
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
                Sardine sardine = (Sardine) sardine;
                if(sardine.isAlive()) { 
                	//remove the sardine
                    sardine.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    
                    return where;
                }
            }
        }
        return null;
    }

}
