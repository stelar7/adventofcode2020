package day7;

import utils.Pair;
import utils.sources.StringFromFileSupplier;

import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        List<String> rules = StringFromFileSupplier.create("day7.input", false)
                                                   .getDataSource();
        
        Map<String, List<Pair<String, Integer>>> containingBags = One.parseContainingBags(rules);
        
        int count = countBagsRecursive(containingBags, "shiny gold");
        
        // subtract the original bag we look into
        System.out.println(count - 1);
    }
    
    
    private static int countBagsRecursive(Map<String, List<Pair<String, Integer>>> containingBags, String lookup)
    {
        int                         count      = 0;
        List<Pair<String, Integer>> keyedEntry = containingBags.getOrDefault(lookup, new ArrayList<>());
        
        for (Pair<String, Integer> entry : keyedEntry)
        {
            String  key   = entry.getA();
            Integer value = entry.getB();
            count += countBagsRecursive(containingBags, key) * value;
        }
        
        return count + 1;
    }
}
