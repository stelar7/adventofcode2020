package day15;

import utils.sources.IntFromFileSupplier;

import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        List<Integer> input = IntFromFileSupplier.createFromCommaFile("day15.input", false)
                                                 .getDataSource();
    
        int[] lastIndex = new int[30000000];
        Arrays.fill(lastIndex, -1);
    
        for (int i = 0; i < input.size(); i++)
        {
            lastIndex[input.get(i)] = i;
        }
    
        int turns      = input.size() + 1;
        int lastSpoken = 0;
    
        while (turns < 30000000)
        {
            int lastSpokenIndex = lastIndex[lastSpoken];
            lastIndex[lastSpoken] = turns - 1;
            lastSpoken = (lastSpokenIndex == -1) ? 0 : turns - 1 - lastSpokenIndex;
            turns++;
        }
    
        System.out.println(lastSpoken);
    }
}