package day2;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

public class Two
{
    public static void main(String[] args)
    {
        String regex = "(?<min>\\d*)-(?<max>\\d*) (?<letter>.): (?<word>.*)";
        
        long inputs = StringFromFileSupplier.create("day2.input", false)
                                            .getDataSource()
                                            .stream()
                                            .map(line -> Utils.extractRegex(line, regex))
                                            .filter(line -> {
                                                int    indexOne = Integer.parseInt(line.get("min")) - 1;
                                                int    indexTwo = Integer.parseInt(line.get("max")) - 1;
                                                char   letter   = line.get("letter").toCharArray()[0];
                                                String word     = line.get("word");
            
                                                long matchCount = Utils.countTrue(word,
                                                                                  w -> w.charAt(indexOne) == letter,
                                                                                  w -> w.charAt(indexTwo) == letter
                                                                                 );
            
                                                return matchCount == 1;
                                            }).count();
        
        System.out.println(inputs);
    }
}
