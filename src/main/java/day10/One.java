package day10;

import utils.sources.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class One
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
        
        Map<Integer, AtomicInteger> distances = new HashMap<>();
        int current = 0;
        for (int next : input)
        {
            distances.computeIfAbsent(Math.abs(next - current), k -> new AtomicInteger(0)).incrementAndGet();
            current = next;
        }
        
        System.out.println(distances.get(1).get() * distances.get(3).get());
    }
}
