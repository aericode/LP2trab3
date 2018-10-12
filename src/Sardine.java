
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
	public Sardine(Ocean ocean, Location location)
	{
		super(ocean, location);
	}

	protected Fish spawnYoung(Ocean ocean,Location loc){
		Sardine young = new Sardine(ocean, loc);
		return young;
	}
    
}
