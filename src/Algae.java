import java.util.Random;

/**
 * Represent a location in a rectangular grid.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Algae
{
    private static final int MAX_QUANTITY = 10;
    private static final int SPREAD_THRESHOLD = 4;
    private static Random rand = new Random();

    // Row and column positions.
    private Location location;
    private Ocean ocean;
    private int algae_count;


    /**
     * Represent a row and column.
     * @param row The row.
     * @param col The column.
     */
    public Algae(Ocean ocean, Location newLocation)
    {
        location = newLocation;
        algae_count = rand.nextInt(5);
        this.ocean = ocean;
        ocean.place(this, location);
    }
    
    /**
     * @return The row.
     */
    public int getRow()
    {
        return location.getRow();
    }
    
    /**
     * @return The column.
     */
    public int getCol()
    {
        return location.getCol();
    }

    public void growAlgae()
    {
        if(algae_count<10){
            algae_count++;
        }
    }

    public void spreadAlgae()
    {
        //limitWidth
        //limitHeight
        int limitWidth  = ocean.getWidth();
        int limitHeight = ocean.getHeight();
        Algae receiver;

        //GETS THE CELLS AT THE 4 DIRECTIONS
        if(location.getRow() > 0){
            receiver = ocean.getAlgaeAt(location.getRow()-1,location.getCol());
            giveAlgae(receiver);
        }
        if(location.getRow() < limitHeight){
            receiver = ocean.getAlgaeAt(location.getRow()+1,location.getCol());
            giveAlgae(receiver);
        }
        if(location.getCol() > 0){
            receiver = ocean.getAlgaeAt(location.getRow(),location.getCol()-1);
            giveAlgae(receiver);
        }
        if(location.getCol() < limitWidth){
            receiver = ocean.getAlgaeAt(location.getRow(),location.getCol()+1);
            giveAlgae(receiver);
        }

    }

    public boolean receiveAlgae(){
        if(algae_count>= 10){
            return false;
        }else{
            algae_count++;
            return true;
        }
    }

    public void giveAlgae(Algae otherAlgae){
        if (otherAlgae.receiveAlgae()){
            algae_count--;
        }
    }

    public boolean getEaten(){
        if(algae_count>0){
            algae_count--;
            return true;
        }else{
            return false;
        }
    }

    public void act()
    {
        growAlgae();
        if(algae_count > SPREAD_THRESHOLD) {
            spreadAlgae();
        }
    }

}
