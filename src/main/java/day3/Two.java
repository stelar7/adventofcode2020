package day3;

import org.joml.Vector2i;
import utils.sources.StringFromFileSupplier;

import java.util.List;

public class Two
{
    public static void main(String[] args)
    {
        List<String> inputs = StringFromFileSupplier.create("day3.input", false)
                                                    .getDataSource();
        
        long trees =
                countTreesForSlope(inputs, new Vector2i(1, 1)) *
                countTreesForSlope(inputs, new Vector2i(3, 1)) *
                countTreesForSlope(inputs, new Vector2i(5, 1)) *
                countTreesForSlope(inputs, new Vector2i(7, 1)) *
                countTreesForSlope(inputs, new Vector2i(1, 2));
        
        System.out.println(trees);
    }
    
    private static int countTreesForSlope(List<String> map, Vector2i slope)
    {
        int trees = 0;
        
        int      width    = map.get(0).length();
        Vector2i location = new Vector2i(0, 0);
        while (location.y < map.size() - 1)
        {
            location = location.add(slope);
            if (location.x >= width)
            {
                location.set(location.x % width, location.y);
            }
            
            char gridItem = map.get(location.y).charAt(location.x);
            if (gridItem == '#')
            {
                trees++;
            }
        }
        
        return trees;
    }
}
