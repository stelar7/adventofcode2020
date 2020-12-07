package day7;

import utils.Pair;
import utils.sources.StringFromFileSupplier;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class One
{
    public static void main(String[] args)
    {
        List<String> rules = StringFromFileSupplier.create("day7.input", false)
                                                   .getDataSource();
        
        Map<String, List<Pair<String, Integer>>> containingBags = parseContainingBags(rules);
        
        int count = 0;
        for (Entry<String, List<Pair<String, Integer>>> entry : containingBags.entrySet())
        {
            String                      k = entry.getKey();
            List<Pair<String, Integer>> v = entry.getValue();
            if (containsBagRecursive(containingBags, k, "shiny gold"))
            {
                count++;
            }
        }
        
        System.out.println(count);
    }
    
    private static boolean containsBagRecursive(Map<String, List<Pair<String, Integer>>> containingBags, String entry, String key)
    {
        List<Pair<String, Integer>> content = containingBags.getOrDefault(entry, new ArrayList<>());
        for (Pair<String, Integer> pair : content)
        {
            if (pair.getA().equalsIgnoreCase(key))
            {
                return true;
            }
            
            if (containsBagRecursive(containingBags, pair.getA(), key))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public static Map<String, List<Pair<String, Integer>>> parseContainingBags(List<String> rules)
    {
        Map<String, List<Pair<String, Integer>>> data = new HashMap<>();
        
        rules.forEach(line -> {
            String[] parts = line.split(" contain ");
            String   key   = parts[0].substring(0, parts[0].length() - 5);
            List<Pair<String, Integer>> value = Arrays.stream(parts[1].split(", "))
                                                      .map(part -> {
                                                          String[] inner  = part.split(" ");
                                                          String   first  = Arrays.stream(inner).skip(1).limit(inner.length - 2).collect(Collectors.joining(" "));
                                                          int      second = 0;
                                                          try
                                                          {
                                                              second = Integer.parseInt(inner[0]);
                                                              return new Pair<>(first, second);
                                                          } catch (NumberFormatException e)
                                                          {
                                                              return null;
                                                          }
                                                      })
                                                      .filter(Objects::nonNull)
                                                      .collect(Collectors.toList());
            
            data.put(key, value);
        });
        
        return data;
    }
}
