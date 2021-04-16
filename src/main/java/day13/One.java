package day13;

import utils.Pair;
import utils.sources.StringFromFileSupplier;

import java.util.*;
import java.util.stream.Collectors;

public class One
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day13.input", false)
                                                   .getDataSource();
        
        int start = Integer.parseInt(input.get(0));
        List<Pair<Integer, Integer>> busses = Arrays.stream(input.get(1).split(","))
                                                    .map(s -> {
                                                        try
                                                        {
                                                            int value = Integer.parseInt(s);
                                                            return new Pair<>(value, (float) value);
                                                        } catch (Exception e)
                                                        {
                                                            return new Pair<>(Integer.MAX_VALUE, (float) Integer.MAX_VALUE);
                                                        }
                                                    })
                                                    .map(i -> new Pair<>(i.getA(), (int) (i.getB() * Math.ceil(start / i.getB()))))
                                                    .sorted(Comparator.comparingInt(a -> a.getB() - start))
                                                    .collect(Collectors.toList());
        
        int bestBuss = busses.get(0).getA();
        int waitTime = busses.get(0).getB() - start;
        
        System.out.println(bestBuss * waitTime);
    }
}
