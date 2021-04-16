package day10;

import utils.sources.IntFromFileSupplier;

import java.util.List;
import java.util.stream.Collectors;

public class Two
{
    public static void main(String[] args)
    {
        List<Integer> input = IntFromFileSupplier.create("day10.input", false)
                                                 .getDataSource()
                                                 .stream()
                                                 .mapToInt(i -> i)
                                                 .sorted()
                                                 .boxed()
                                                 .collect(Collectors.toList());
        input.add(input.get(input.size() - 1) + 3);
        
        int[] sequence = {0, 1, 1, 2, 4, 7, 13};
    
        int last = 0;
        long count = 1;
        int inSequenceCount = 1;
        for (Integer i : input)
        {
            if (i == last + 1) {
                inSequenceCount++;
            } else {
                count *= sequence[inSequenceCount];
                inSequenceCount = 1;
            }
            last = i;
        }
        System.out.println(count);
    }
}
