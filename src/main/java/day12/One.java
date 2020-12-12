package day12;

import org.joml.Vector2i;
import utils.*;
import utils.sources.StringFromFileSupplier;

import java.util.List;

public class One
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day12.input", false)
                                                   .getDataSource();
        
        Droid ship = new Droid();
        ship.turn(Direction.EAST);
        for (String line : input)
        {
            String letter   = line.substring(0, 1);
            String distance = line.substring(1);
            int    count    = Integer.parseInt(distance);
            
            if (letter.equalsIgnoreCase("R"))
            {
                for (int i = 0; i < count / 90; i++)
                {
                    ship.turnRight();
                }
            } else if (letter.equalsIgnoreCase("L"))
            {
                for (int i = 0; i < count / 90; i++)
                {
                    ship.turnLeft();
                }
            } else
            {
                Direction dir = Direction.fromLetter(letter);
                ship.move(dir, count);
            }
        }
        
        System.out.println(Utils.manhattanDistance(new Vector2i(), ship.position()));
    }
}
