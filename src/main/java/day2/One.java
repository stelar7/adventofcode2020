package day2;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

public class One
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
                                                int      min          = Integer.parseInt(minMax.split("-")[0]);
                                                int      max          = Integer.parseInt(minMax.split("-")[1]);
                                                String   word         = split[1];
            
                                                int count = Utils.charCount(word)[letter];
                                                return count >= min && count <= max;
                                            }).count();
        
        System.out.println(inputs);
    }
}
