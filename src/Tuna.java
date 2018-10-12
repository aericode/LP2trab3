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

	protected Fish spawnYoung(Ocean ocean,Location loc){
		Tuna young = new Tuna(ocean, loc);
		return young;
	}

}
