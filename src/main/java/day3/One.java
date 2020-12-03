package day3;

import org.joml.Vector2i;
import utils.sources.StringFromFileSupplier;

import java.util.List;

public class One
{
    public static void main(String[] args)
    {
        List<String> inputs = StringFromFileSupplier.create("day3.input", false)
                                                    .getDataSource();
        
        int trees = 0;
        
        int      width    = inputs.get(0).length();
        Vector2i location = new Vector2i(0, 0);
        Vector2i slope    = new Vector2i(3, 1);
        while (location.y < inputs.size() - 1)
        {
            location = location.add(slope);
            if (location.x >= width)
            {
                location.set(location.x % width, location.y);
            }
            
            char gridItem = inputs.get(location.y).charAt(location.x);
            if (gridItem == '#')
            {
                trees++;
            }
        }
        
        System.out.println(trees);
    }
}
