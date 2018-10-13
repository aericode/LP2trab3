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

	public Sardine(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

	public Sardine(Ocean ocean, Location location,int age)
	{
		super(ocean, location, age);
	}

	protected Fish spawnYoung(Ocean ocean,Location loc){
		Sardine young = new Sardine(ocean, loc);
		return young;
	}

	public void act(List<Fish> newFishes)
    {
        incrementAge();
        //incrementHunger();
        if(alive) {
            //eatAlgae();
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

    private void eatAlgae(){
        Algae algae = ocean.getAlgaeAt(location);
        if(algae.getEaten()){
            foodLevel = FOOD_MAX;
        }
    }
    
}
