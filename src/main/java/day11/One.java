package day11;

import org.joml.Vector2i;
import utils.*;
import utils.sources.StringFromFileSupplier;

import java.util.*;

public class One
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day11.input", false)
                                                   .getDataSource();
        
        Map<Vector2i, Long> current = Utils.parseGrid(input);
        Map<Vector2i, Long> last    = null;
        
        while (true)
        {
            if (current.equals(last))
            {
                System.out.println(Utils.countGrid(current, 35L));
                return;
            }
            last = current;
            current = iterate(current);
        }
    }
    
    public static Map<Vector2i, Long> iterate(Map<Vector2i, Long> grid)
    {
        Map<Vector2i, Long> nextIteration = new HashMap<>(grid);
        
        grid.forEach((k, v) -> {
            int occupied = Utils.findNeighbourNodes8(grid, k, 35L).size();
            if (v == 76L && occupied == 0)
            {
                nextIteration.put(k, 35L);
                return;
            }
            if (v == 35L && occupied >= 4)
            {
                nextIteration.put(k, 76L);
                return;
            }
        });
        
        return nextIteration;
    }
}
