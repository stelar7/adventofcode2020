package day1;

import utils.sources.IntFromFileSupplier;

import java.util.List;

public class Two
{
    public static void main(String[] args)
    {
        List<Integer> inputs = IntFromFileSupplier.create("day1.input", false)
                                                  .getDataSource();
    
        for (int i = 0; i < inputs.size(); i++)
        {
            Integer a = inputs.get(i);
            for (int j = 0; j < inputs.size(); j++)
            {
                Integer b = inputs.get(j);
                for (int k = 0; k < inputs.size(); k++)
                {
                    Integer c = inputs.get(k);
                    if(i != j && j != k) {
                        if (a + b + c == 2020) {
                            System.out.println(a * b * c);
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }
}
