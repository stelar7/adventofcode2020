package day2;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

public class One
{
    public static void main(String[] args)
    {
        String regex = "(?<min>\\d*)-(?<max>\\d*) (?<letter>.): (?<word>.*)";
        
        long inputs = StringFromFileSupplier.create("day2.input", false)
                                            .getDataSource()
                                            .stream()
                                            .map(line -> Utils.extractRegex(line, regex))
                                            .filter(line -> {
                                                int    min    = Integer.parseInt(line.get("min"));
                                                int    max    = Integer.parseInt(line.get("max"));
                                                char   letter = line.get("letter").toCharArray()[0];
                                                String word   = line.get("word");
            
                                                long count = Utils.charCount(word)[letter];
                                                return count >= min && count <= max;
                                            }).count();
        
        System.out.println(inputs);
    }
}
