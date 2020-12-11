package day11;

import utils.*;
import utils.sources.StringFromFileSupplier;

import java.util.*;
import java.util.stream.Collectors;

public class Two
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day11.input", false)
                                                   .getDataSource();
        
        List<Long> validStates = List.of(35L, 76L);
        List<Cell> current = Utils.parseGridToCells(input, validStates, validStates)
                                  .stream()
                                  .filter(c -> validStates.contains(c.getState()))
                                  .collect(Collectors.toList());
        
        List<Cell> last = null;
        
        while (true)
        {
            if (current.equals(last))
            {
                System.out.println(Utils.countCellStates(current, 35L));
                return;
            }
            last = current;
            current = iterate(current);
        }
    }
    
    public static List<Cell> iterate(List<Cell> grid)
    {
        List<Cell> nextIteration = new ArrayList<>();
        
        grid.forEach(entry -> {
            long value = entry.getState();
            if (value == 46L)
            {
                return;
            }
            
            Cell       toAdd    = new Cell(entry);
            List<Cell> nearby   = grid.stream().filter(c -> entry.getNeighbourLocations().contains(c.getLocation())).collect(Collectors.toList());
            long       occupied = Utils.countCellStates(nearby, 35L);
            
            if (value == 76L && occupied == 0)
            {
                toAdd.toggleState();
            }
            if (value == 35L && occupied >= 5)
            {
                toAdd.toggleState();
            }
            
            nextIteration.add(toAdd);
        });
        
        return nextIteration;
    }
}
