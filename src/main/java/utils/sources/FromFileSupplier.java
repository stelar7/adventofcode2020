package utils.sources;

import utils.Utils;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FromFileSupplier<T> implements Supplier<T>, SourceSupplier<T>
{
    private final List<T> data;
    private       int     index = 0;
    private final boolean repeating;
    
    public FromFileSupplier(List<T> values, boolean repeating)
    {
        this.repeating = repeating;
        this.data = values;
    }
    
    public FromFileSupplier(String inputFile, Function<String, T> mapping, boolean repeating)
    {
        this.repeating = repeating;
        this.data = Arrays.stream(Utils.readFile(inputFile).split("\n"))
                          .map(mapping)
                          .collect(Collectors.toList());
    }
    
    public FromFileSupplier(String inputFile, String separator, Function<String, T> mapping, boolean repeating)
    {
        this.repeating = repeating;
        this.data = Arrays.stream(Utils.readFile(inputFile).split(separator))
                          .map(mapping)
                          .collect(Collectors.toList());
    }
    
    public static <T> FromFileSupplier createFromArray(String inputFile, Function<String, List<T>> mapping, boolean repeating)
    {
        return new FromFileSupplier(
                Arrays.stream(Utils.readFile(inputFile).split("\n"))
                      .flatMap(a -> Stream.of(mapping.apply(a)))
                      .flatMap(Collection::stream)
                      .collect(Collectors.toList()),
                repeating);
    }
    
    public static <T> FromFileSupplier create(String inputFile, String separator, Function<String, T> mapping, boolean infinite)
    {
        return new FromFileSupplier(inputFile, separator, mapping, infinite);
    }
    
    public static <T> FromFileSupplier create(String inputFile, Function<String, T> mapping, boolean infinite)
    {
        return new FromFileSupplier(inputFile, mapping, infinite);
    }
    
    @Override
    public List<T> getDataSource()
    {
        return data;
    }
    
    public T firstLineAsString()
    {
        return data.get(0);
    }
    
    public String toString()
    {
        return data.stream().map(a -> (String) a).collect(Collectors.joining("\n"));
    }
    
    @Override
    public T get()
    {
        if (repeating)
        {
            return data.get(index++ % data.size());
        }
        
        return index > data.size() - 1 ? null : data.get(index++);
    }
}
