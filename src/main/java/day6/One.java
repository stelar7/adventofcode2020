package day6;

import utils.sources.StringFromFileSupplier;

import java.util.*;
import java.util.stream.Collectors;

public class One
{
    public static void main(String[] args)
    {
        Integer groups = Arrays.stream(StringFromFileSupplier.create("day6.input", false)
                                                             .toString()
                                                             .split("\n\n"))
                               .map(group -> group.replace("\n", ""))
                               .map(group -> new HashSet<>(group.chars().boxed().collect(Collectors.toList())))
                               .reduce(0, (current, next) -> current + next.size(), Integer::sum);
        
        
        System.out.println(groups);
    }
}
