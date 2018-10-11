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
	public Tuna(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

	private void giveBirth(List<Fish> newFishes)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = ocean.getFreeAdjacentLocations(location);
        int births = breed();

        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Tuna young = new Tuna(ocean,loc);
            newFishes.add(young);
        }
    }

}
