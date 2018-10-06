
/**
 * (Fill in description and author info here)
 */
public class Ocean
{

    //height of the ocean
    private int height;
    //width of the ocean
    private int width;

    // Storage for the fishes
    private Fish[][] ocean;


    /**
     * Represent an ocean of the given dimensions.
     * @param height The height of the ocean.
     * @param width The width of the ocean.
     */
    public Ocean(int height, int width)
    {
        this.height = height;
        this.width  = width;
        ocean       = new Fish[height][width];
    }
    
    /**
     * Return the fish at the given location, if any.
     * @param row The desired row.
     * @param col The desired column.
     * @return The fish at the given location, or null if there is none.
     */
    public Fish getFishAt(int row, int col)
    {
        return ocean[row][col];
    }
    
    public void putFishAt(int row, int col, Fish fish)
    {
        ocean[row][col] = fish;
    }

    /**
     * @return The height of the ocean.
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * @return The width of the ocean.
     */
    public int getWidth()
    {
        return width;
    }


}
