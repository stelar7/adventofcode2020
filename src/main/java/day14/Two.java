package day14;

import utils.*;
import utils.sources.StringFromFileSupplier;

import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day14.input", false)
                                                   .getDataSource();
        
        Map<Long, Long> memory = new HashMap<>();
        String          mask   = "";
        
        for (String s : input)
        {
            BitSet line = new BitSet(s.length());
            
            if (s.startsWith("mask = "))
            {
                mask = new StringBuilder(s.substring("mask = ".length())).toString();
                continue;
            }
            
            Map<String, String> data            = Utils.extractRegex(s, "mem\\[(?<index>\\d+)] = (?<value>\\d+)", "index", "value");
            String              dataString      = data.get("value");
            long                dataValue       = Long.parseLong(dataString);
            String              addressString   = data.get("index");
            String              replacementMask = mask.replace("0", "1").replace("X", "0");
            long                address         = Long.parseLong(addressString);
            address &= Long.parseLong(replacementMask, 2);
            
            
            writeValue(memory, mask, address, dataValue);
        }
        
        
        System.out.println(memory.values().stream().mapToLong(i -> i).sum());
    }
    
    public static void writeValue(Map<Long, Long> memory, String mask, long address, long value)
    {
        if (mask.contains("X"))
        {
            int                  index = mask.indexOf('X');
            Pair<String, String> split = Utils.split(mask, index);
            writeValue(memory, split.getA() + "0" + split.getB().substring(1), address, value);
            writeValue(memory, split.getA() + "1" + split.getB().substring(1), address, value);
            return;
        }
        
        long realMask    = Long.parseLong(mask, 2);
        long realAddress = realMask | address;
        memory.put((long) realAddress, value);
    }
}