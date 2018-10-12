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
    public Shark(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

	protected Fish spawnYoung(Ocean ocean,Location loc){
		Shark young = new Shark(ocean, loc);
		return young;
	}

	public void act(List<Fish> newFishes)
    {
        //incrementAge();
        if(alive) {
        	giveBirth(newFishes);          
            // Try to move into a free location.
            Location newLocation = ocean.freeAdjacentLocation(location);
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
