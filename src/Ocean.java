import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * (Fill in description and author info here)
 */
public class Ocean
{

    // A random number generator for providing random locations.
    private static final Random rand = new Random();

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

    public void clear()
    {
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                ocean[row][col] = null;
            }
        }
    }

    /**
     * Return a shuffled list of locations adjacent to the given one.
     * The list will not include the location itself.
     * All locations will lie within the grid.
     * @param row X coordinate of the function's target
     * @param col Y coordinate of the function's target
     * @return A list of locations adjacent to that given.
     */
    public List<Fish> adjacentLocations(int row, int col)
    {
        boolean is_valid = (row < 0 || row > height) && (col < 0 || col > width);
        // The list of locations to be returned.
        List<Fish> fishSpots = new LinkedList<Fish>();
        if( is_valid ) {

            for(int roffset = -1; roffset <= 1; roffset++) {
                int nextRow = row + roffset;
                if(nextRow >= 0 && nextRow < height) {
                    for(int coffset = -1; coffset <= 1; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            fishSpots.add(ocean[row][col]);
                        }
                    }
                }
            }
            
            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(fishSpots, rand);
        }
        return fishSpots;
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
