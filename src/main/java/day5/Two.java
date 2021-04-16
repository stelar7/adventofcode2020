package day5;

import utils.sources.StringFromFileSupplier;

import java.util.List;
import java.util.stream.Collectors;

public class Two
{
    public static void main(String[] args)
    {
        List<Integer> rows = StringFromFileSupplier.create("day5.input", false)
                                                   .getDataSource()
                                                   .stream()
                                                   .mapToInt(One::findId)
                                                   .boxed()
                                                   .sorted()
                                                   .collect(Collectors.toList());
        
        int last = rows.get(0);
        for (int i = 1; i < rows.size(); i++)
        {
            Integer row = rows.get(i);
            if (row - 1 != last)
            {
                System.out.println(row - 1);
                break;
            }
            
            last = row;
        }
    }
}
