package day1;

import utils.sources.IntFromFileSupplier;

import java.util.List;

public class One
{
    public static void main(String[] args)
    {
        List<Integer> inputs = IntFromFileSupplier.create("day1.input", false)
                                                  .getDataSource();
    
        for (int i = 0, inputsSize = inputs.size(); i < inputsSize; i++)
        {
            Integer a = inputs.get(i);
            for (int j = 0, size = inputs.size(); j < size; j++)
            {
                Integer b = inputs.get(j);
            
                if(i != j) {
                    if (a + b == 2020) {
                        System.out.println(a * b);
                        System.exit(0);
                    }
                }
            }
        }
    }
}
