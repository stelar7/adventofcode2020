package day13;

import utils.sources.StringFromFileSupplier;

import java.util.List;

public class One
{
    public static void main(String[] args)
    {
        List<String> input = StringFromFileSupplier.create("day12.input", false)
                                                   .getDataSource();
        
        System.out.println(input);
    }
}
