package day4;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

import java.util.*;

public class Two
{
    public static void main(String[] args)
    {
        List<String> inputs = StringFromFileSupplier.create("day4.input", false)
                                                    .getDataSource();
        
        inputs.add("");
        
        StringBuilder currentPassport = new StringBuilder();
        int           score           = 0;
        for (String currentLine : inputs)
        {
            currentPassport.append(currentLine).append(" ");
            
            if (currentLine.isBlank())
            {
                if (checkIfValid(currentPassport.toString()))
                {
                    score++;
                }
                currentPassport = new StringBuilder();
            }
        }
        
        System.out.println(score);
    }
    
    private static boolean validateFieldIfPresent(String data, String field)
    {
        if (field.equalsIgnoreCase("byr"))
        {
            if (data.matches(".*byr:.* "))
            {
                int value = Integer.parseInt(Utils.extractRegex(data, ".*byr:(?<data>.*?) ").get("data"));
                if (value >= 1920 && value <= 2002)
                {
                    return true;
                } else
                {
                    System.out.println(data + " failed byr");
                    return false;
                }
            }
        }
        
        if (field.equalsIgnoreCase("iyr"))
        {
            if (data.matches(".*iyr:.* "))
            {
                int value = Integer.parseInt(Utils.extractRegex(data, ".*iyr:(?<data>.*?) ").get("data"));
                if (value >= 2010 && value <= 2020)
                {
                    return true;
                } else
                {
                    System.out.println(data + " failed iyr");
                    return false;
                }
            }
        }
        
        if (field.equalsIgnoreCase("eyr"))
        {
            if (data.matches(".*eyr:.* "))
            {
                int value = Integer.parseInt(Utils.extractRegex(data, ".*eyr:(?<data>.*?) ").get("data"));
                if (value >= 2020 && value <= 2030)
                {
                    return true;
                } else
                {
                    System.out.println(data + " failed eyr");
                    return false;
                }
            }
        }
        
        
        if (field.equalsIgnoreCase("hgt"))
        {
            if (data.matches(".*hgt:(?<data>.*?)(?<type>cm|in).*"))
            {
                Map<String, String> map   = Utils.extractRegex(data, ".*hgt:(?<data>.*?)(?<type>cm|in) ");
                String              type  = map.get("type");
                int                 value = Integer.parseInt(map.get("data"));
                if (type.equalsIgnoreCase("cm"))
                {
                    if (value >= 150 && value <= 193)
                    {
                        return true;
                    } else
                    {
                        System.out.println(data + " failed hgt");
                        return false;
                    }
                }
                if (type.equalsIgnoreCase("in"))
                {
                    if (value >= 59 && value <= 76)
                    {
                        return true;
                    } else
                    {
                        System.out.println(data + " failed hgt");
                        return false;
                    }
                }
            }
        }
        
        if (field.equalsIgnoreCase("hcl"))
        {
            if (data.matches(".*hcl:#.*"))
            {
                Map<String, String> map  = Utils.extractRegex(data, ".*?hcl:(?<data>#.*?) ");
                String              type = map.get("data");
                if (type.matches("#[a-f0-9]{6}"))
                {
                    return true;
                } else
                {
                    System.out.println(data + " failed hcl");
                    return false;
                }
            }
        }
        
        if (field.equalsIgnoreCase("ecl"))
        {
            if (data.matches(".*ecl:.*"))
            {
                Map<String, String> map  = Utils.extractRegex(data, ".*?ecl:(?<data>.*?) ");
                String              type = map.get("data");
                if (List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(type))
                {
                    return true;
                } else
                {
                    System.out.println(data + " failed ecl");
                    return false;
                }
            }
        }
        
        if (field.equalsIgnoreCase("pid"))
        {
            if (data.matches(".*pid:.*"))
            {
                Map<String, String> map  = Utils.extractRegex(data, ".*?pid:(?<data>.*?) ");
                String              type = map.get("data");
                if (type.length() == 9)
                {
                    return true;
                } else
                {
                    System.out.println(data + " failed hgt");
                    return false;
                }
            }
        }
        
        System.out.println(data + " didnt match " + field);
        
        return false;
    }
    
    private static boolean checkIfValid(String data)
    {
        return validateFieldIfPresent(data, "byr") &&
               validateFieldIfPresent(data, "iyr") &&
               validateFieldIfPresent(data, "eyr") &&
               validateFieldIfPresent(data, "hgt") &&
               validateFieldIfPresent(data, "hcl") &&
               validateFieldIfPresent(data, "ecl") &&
               validateFieldIfPresent(data, "pid");
    }
}
