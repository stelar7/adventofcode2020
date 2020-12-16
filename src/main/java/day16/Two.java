package day16;

import utils.sources.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Two
{
    public static void main(String[] args)
    {
        String input = StringFromFileSupplier.create("day16.input", false)
                                             .toString();
        
        String[] parts = input.split("\n\n");
        Map<String, List<Predicate<Integer>>> fields = Arrays.stream(parts[0].split("\n"))
                                                             .collect(Collectors.toMap((String e) -> e.split(":")[0], (String e) -> {
                                                                 String                   section = e.split(":")[1];
                                                                 String[]                 ors     = section.split(" or ");
                                                                 List<Predicate<Integer>> checks  = new ArrayList<>();
                                                                 for (String or : ors)
                                                                 {
                                                                     String[]           values = or.split("-");
                                                                     int                first  = Integer.parseInt(values[0].trim());
                                                                     int                second = Integer.parseInt(values[1].trim());
                                                                     Predicate<Integer> check  = (i) -> i >= first && i <= second;
                                                                     checks.add(check);
                                                                 }
                                                                 return checks;
                                                             }));
        
        List<Predicate<Integer>> predicates = fields.values()
                                                    .stream()
                                                    .flatMap(List::stream)
                                                    .collect(Collectors.toList());
        
        List<String> nearby = Arrays.stream(parts[2].split("\n"))
                                    .skip(1)
                                    .collect(Collectors.toList());
        
        // TODO
        List<List<Integer>> validTickets = nearby.stream()
                                           .map(line -> (List<Integer>) (List<?>) IntFromStringSupplier.createFromCommaString(line, false).getDataSource())
                                           .collect(Collectors.toList());
        
        System.out.println();
    }
}