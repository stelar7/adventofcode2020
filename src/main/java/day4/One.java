package day4;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class One
{
    public static void main(String[] args)
    {
        List<String> inputs = StringFromFileSupplier.create("day4.input", false)
                                                    .getDataSource();
        
        List<Map<String, String>> passports = generatePassportStrings(inputs);
        
        long count = passports.stream()
                              .filter(m -> Utils.intersection(m.keySet(), List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")).size() == 7)
                              .count();
        
        
        System.out.println(count);
    }
    
    public static List<Map<String, String>> generatePassportStrings(List<String> inputs)
    {
        List<Map<String, String>> out = new ArrayList<>();
        
        StringBuilder currentPassport = new StringBuilder();
        for (String currentLine : inputs)
        {
            currentPassport.append(currentLine).append(" ");
            
            if (currentLine.isBlank())
            {
                out.add(transformToMap(currentPassport.toString()));
                currentPassport = new StringBuilder();
            }
        }
        out.add(transformToMap(currentPassport.toString()));
        
        return out;
    }
    
    private static Map<String, String> transformToMap(String data)
    {
        return Arrays.stream(data.split(" "))
                     .map(word -> Map.of(word.split(":")[0], word.split(":")[1]))
                     .flatMap(m -> m.entrySet().stream())
                     .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
}
