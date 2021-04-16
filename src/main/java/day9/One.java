package day9;

import utils.CircularQueue;
import utils.sources.LongFromFileSupplier;

import java.util.List;

public class One
{
    public static void main(String[] args)
    {
        List<Long> ops = LongFromFileSupplier.create("day9.input", false)
                                             .getDataSource();
        
        
        int                    preamble = 25;
        CircularQueue<Long> queue    = new CircularQueue<>(preamble);
        for (int i = 0; i < preamble; i++)
        {
            queue.add(ops.get(i));
        }
        
        List<Long> rest = ops.subList(preamble, ops.size());
        
        for (Long item : rest)
        {
            if (canMake(item, queue.getItems()))
            {
                queue.add(item);
            } else
            {
                System.out.println(item);
                break;
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
