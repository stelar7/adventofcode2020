package day8;

import utils.sources.StringFromFileSupplier;

import java.util.*;

public class One
{
    public static void main(String[] args)
    {
        List<String> ops = StringFromFileSupplier.create("day8.input", false)
                                                 .getDataSource();
        
        int          acc     = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < ops.size(); i++)
        {
            if (!visited.add(i))
            {
                break;
            }
            
            String   op     = ops.get(i);
            String[] todo   = op.split(" ");
            String   action = todo[0];
            int      count  = Integer.parseInt(todo[1]);
            
            if (action.equalsIgnoreCase("acc"))
            {
                acc += count;
            }
            
            if (action.equalsIgnoreCase("jmp"))
            {
                i += count - 1;
            }
        }
        
        
        System.out.println(acc);
    }
}
