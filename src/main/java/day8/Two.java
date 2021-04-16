package day8;

import utils.sources.StringFromFileSupplier;

import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        List<String> ops = StringFromFileSupplier.create("day8.input", false)
                                                 .getDataSource();
        
        outer:
        for (int j = 0; j < ops.size(); j++)
        {
            Object[] op2     = parseInstruction(ops.get(j));
            String   action2 = (String) op2[0];
            int      count2  = (int) op2[1];
            
            if (action2.equalsIgnoreCase("jmp"))
            {
                action2 = "nop";
            } else if (action2.equalsIgnoreCase("nop"))
            {
                action2 = "jmp";
            }
            
            List<String> otherOps = new ArrayList<>(ops);
            otherOps.set(j, action2 + " " + count2);
            
            Set<Integer> visited = new HashSet<>();
            int          acc     = 0;
            for (int i = 0; i < ops.size(); i++)
            {
                if (!visited.add(i))
                {
                    continue outer;
                }
                
                Object[] op     = parseInstruction(otherOps.get(i));
                String   action = (String) op[0];
                int      count  = (int) op[1];
                
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
    
    private static Object[] parseInstruction(String inst)
    {
        String[] todo   = inst.split(" ");
        String   action = todo[0];
        int      count  = Integer.parseInt(todo[1]);
        
        return new Object[]{action, count};
    }
}
