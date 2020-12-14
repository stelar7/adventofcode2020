package day14;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

import java.util.*;

public class One
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day14.input", false)
                                                   .getDataSource();
        
        Map<Integer, BitSet> value = new HashMap<>();
        MemoryMask           mask  = null;
        
        for (String s : input)
        {
            BitSet line = new BitSet(s.length());
            
            if (s.startsWith("mask = "))
            {
                String maskString = new StringBuilder(s.substring("mask = ".length())).reverse().toString();
                mask = new MemoryMask(maskString);
                continue;
            }
            
            Map<String, String> data       = Utils.extractRegex(s, "mem\\[(?<index>\\d+)] = (?<value>\\d+)", "index", "value");
            int                 dataIndex  = Integer.parseInt(data.get("index"));
            long                valueIndex = Long.parseLong(data.get("value"));
            BitSet              memoryData = BitSet.valueOf(new long[]{valueIndex});
            mask.overrideBitset(memoryData);
            value.put(dataIndex, memoryData);
        }
        
        
        System.out.println(value.values().stream().filter(b -> b.cardinality() > 0).mapToLong(b -> b.toLongArray()[0]).sum());
    }
    
    static class MemoryMask
    {
        String data;
        
        public MemoryMask(String data)
        {
            this.data = data;
        }
        
        public void overrideBitset(BitSet value)
        {
            for (int i = 0; i < data.toCharArray().length; i++)
            {
                if (data.charAt(i) == '1')
                {
                    value.set(i, true);
                }
                
                if (data.charAt(i) == '0')
                {
                    value.set(i, false);
                }
            }
        }
    }
}
