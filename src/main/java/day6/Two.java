package day6;

import utils.Utils;
import utils.sources.StringFromFileSupplier;

import java.util.Arrays;

public class Two
{
    public static void main(String[] args)
    {
        long groups = Arrays.stream(StringFromFileSupplier.create("day6.input", false)
                                                          .toString()
                                                          .split("\n\n"))
                            .map(group -> group.split("\n"))
                            .mapToLong(group ->
                                               Arrays.stream(
                                                       Utils.arrayDivide(
                                                               Arrays.stream(group)
                                                                     .map(Utils::letterCount)
                                                                     .reduce(new int[26], Utils::arrayMergeAddByIndex)
                                                               , group.length)
                                                            ).sum()
                                      ).sum();
        
        
        System.out.println(groups);
    }
}
