package day13;

import utils.*;
import utils.sources.StringFromFileSupplier;

import java.util.*;
import java.util.stream.*;

public class Two
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day13.input", false)
                                                   .getDataSource();
        
        
        List<String> busses = Arrays.stream(input.get(1).split(",")).collect(Collectors.toList());
        
        List<Pair<Integer, Integer>> indexedBusses = IntStream.range(0, busses.size())
                                                              .mapToObj(index -> {
                                                                  String currentBuss = busses.get(index);
                                                                  if (currentBuss.equalsIgnoreCase("x"))
                                                                  {
                                                                      return null;
                                                                  }
                                                                  return new Pair<>(index, Integer.parseInt(currentBuss));
                                                              })
                                                              .filter(Objects::nonNull)
                                                              .collect(Collectors.toList());
        
        List<Integer> indecies = indexedBusses.stream().map(Pair::getA).map(i -> -i).collect(Collectors.toList());
        List<Integer> modulos  = indexedBusses.stream().map(Pair::getB).collect(Collectors.toList());
        
        System.out.println(Utils.chineseRemainder(indecies, modulos));
        
    }
}
