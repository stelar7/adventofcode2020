package day4;

import utils.sources.StringFromFileSupplier;

import java.util.List;

public class One
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
    
    private static boolean checkIfValid(String data)
    {
        if (data.matches(".*byr:.*[\\n\\r\\s]")) {
            if (data.matches(".*iyr:.*[\\n\\r\\s]")) {
                if (data.matches(".*eyr:.*[\\n\\r\\s]")) {
                    if (data.matches(".*hgt:.*[\\n\\r\\s]")) {
                        if (data.matches(".*hcl:.*[\\n\\r\\s]")) {
                            if (data.matches(".*ecl:.*[\\n\\r\\s]")) {
                                if (data.matches(".*pid:.*[\\n\\r\\s]")) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
