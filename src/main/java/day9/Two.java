package day9;

import utils.*;
import utils.sources.*;

import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        List<Long> ops = LongFromFileSupplier.create("day9.input", false)
                                             .getDataSource();
        
        
        int                 preamble = 25;
        CircularQueue<Long> queue    = new CircularQueue<>(preamble);
        for (int i = 0; i < preamble; i++)
        {
            queue.add(ops.get(i));
        }
        
        List<Long> rest   = ops.subList(preamble, ops.size());
        long       target = 0;
        for (Long item : rest)
        {
            if (canMake(item, queue.getItems()))
            {
                queue.add(item);
            } else
            {
                target = item;
                break;
            }
        }
        
        getNumbersAddingTo(target, ops);
        
    }
    
    public static void getNumbersAddingTo(long number, List<Long> items)
    {
        outer:
        for (int i = 0; i < items.size(); i++)
        {
            for (int j = i + 1; j < items.size(); j++)
            {
                if (i == j)
                {
                    continue;
                }
                
                List<Long> temp  = items.subList(i, j);
                long       value = temp.stream().mapToLong(l -> l).sum();
                if (value == number)
                {
                    long min = temp.stream().mapToLong(l -> l).min().getAsLong();
                    long max = temp.stream().mapToLong(l -> l).max().getAsLong();
                    
                    System.out.println(min + max);
                    break outer;
                }
            }
        }
    }
    
    public static boolean canMake(long number, List<Long> items)
    {
        for (int i = 0; i < items.size(); i++)
        {
            for (int j = 0; j < items.size(); j++)
            {
                if (i == j)
                {
                    continue;
                }
                
                if (items.get(i) + items.get(j) == number)
                {
                    System.out.println(number + " is the sum of " + items.get(i) + " and " + items.get(j));
                    return true;
                }
            }
        }
        
        return false;
    }
}
