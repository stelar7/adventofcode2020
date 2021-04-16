package day4;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

import java.util.*;
import java.util.function.Predicate;

public class Two
{
    public static void main(String[] args)
    {
        List<String> inputs = StringFromFileSupplier.create("day4.input", false)
                                                    .getDataSource();
        
        List<Map<String, String>> passports = One.generatePassportStrings(inputs);
        
        long count = passports.stream()
                              .filter(Two::validatePassport)
                              .count();
        
        
        System.out.println(count);
    }
    
    @SafeVarargs
    public static boolean validateIntField(String field, Predicate<Integer>... predicate)
    {
        if (field == null)
        {
            return false;
        }
        
        return Utils.countTrue(Integer.parseInt(field), predicate) == predicate.length;
    }
    
    @SafeVarargs
    public static <T> boolean validateField(T field, Predicate<T>... predicates)
    {
        if (field == null)
        {
            return false;
        }
        
        return Utils.allTrue(field, predicates);
    }
    
    public static boolean validatePassport(Map<String, String> passport)
    {
        boolean byrValid = validateIntField(passport.get("byr"),
                                            (year) -> year >= 1920,
                                            (year) -> year <= 2002
                                           );
        
        boolean iyrValid = validateIntField(passport.get("iyr"),
                                            (year) -> year >= 2010,
                                            (year) -> year <= 2020
                                           );
        
        boolean eyrValid = validateIntField(passport.get("eyr"),
                                            (year) -> year >= 2020,
                                            (year) -> year <= 2030
                                           );
        
        System.out.println(passport);
        boolean hgtValid = validateField(passport.get("hgt"),
                                         (input) -> input.matches("(?<data>.*?)(?<type>cm|in)"),
                                         (input) -> {
                                             Map<String, String> map  = Utils.extractRegex(input, "(?<data>.*?)(?<type>cm|in)");
                                             String              type = map.get("type");
                                             if (!type.equalsIgnoreCase("cm"))
                                             {
                                                 return true;
                                             }
            
                                             int value = Integer.parseInt(map.get("data"));
                                             return value >= 150 && value <= 193;
                                         },
                                         (input) -> {
                                             Map<String, String> map  = Utils.extractRegex(input, "(?<data>.*?)(?<type>cm|in)");
                                             String              type = map.get("type");
                                             if (!type.equalsIgnoreCase("in"))
                                             {
                                                 return true;
                                             }
            
                                             int value = Integer.parseInt(map.get("data"));
                                             return value >= 59 && value <= 76;
                                         }
                                        );
        
        boolean hclValid = validateField(passport.get("hcl"), (color) -> color.matches("#[a-f0-9]{6}"));
        boolean eclValid = validateField(passport.get("ecl"), (color) -> List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(color));
        boolean pidValid = validateField(passport.get("pid"), (pid) -> pid.length() == 9);
        
        
        return byrValid && iyrValid && eyrValid && hgtValid && hclValid && eclValid && pidValid;
    }
}
