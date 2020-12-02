package day2;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

public class Two
{
    public static void main(String[] args)
    {
        long inputs = StringFromFileSupplier.create("day2.input", false)
                                            .getDataSource()
                                            .stream()
                                            .filter(line -> {
                                                String[] split        = line.split(": ");
                                                String   precondition = split[0];
                                                char     letter       = precondition.split(" ")[1].toCharArray()[0];
                                                String   minMax       = precondition.split(" ")[0];
                                                int      indexOne     = Integer.parseInt(minMax.split("-")[0]);
                                                int      indexTwo     = Integer.parseInt(minMax.split("-")[1]);
                                                String   word         = split[1];
            
                                                return Utils.countTrue(
                                                        word,
                                                        w -> w.charAt(indexOne - 1) == letter,
                                                        w -> w.charAt(indexTwo - 1) == letter
                                                                      ) == 1;
                                            }).count();
        
        System.out.println(inputs);
    }
}
