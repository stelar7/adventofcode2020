package day5;

import utils.sources.StringFromFileSupplier;

public class One
{
    public static void main(String[] args)
    {
        long max = StringFromFileSupplier.create("day5.input", false)
                                         .getDataSource()
                                         .stream()
                                         .mapToInt(One::findId)
                                         .max()
                                         .getAsInt();
        
        
        System.out.println(max);
    }
    
    public static int findId(String code)
    {
        int id = 0;
        for (char c : code.toCharArray())
        {
            if (c == 'F' || c == 'L')
            {
                id <<= 1;
            }
            
            if (c == 'B' || c == 'R')
            {
                id = (id << 1) + 1;
            }
        }
        
        return id;
    }
}
