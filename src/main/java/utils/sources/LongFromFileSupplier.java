package utils.sources;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LongFromFileSupplier extends FromFileSupplier<Long>
{
    public LongFromFileSupplier(String inputFile, boolean repeating)
    {
        super(inputFile, Long::parseLong, repeating);
    }
    
    public LongFromFileSupplier(String inputFile, Function<String, Long> func, boolean repeating)
    {
        super(inputFile, func, repeating);
    }
    
    public LongFromFileSupplier(String inputFile, String separator, boolean repeating)
    {
        super(inputFile, separator, Long::parseLong, repeating);
    }
    
    public LongFromFileSupplier(String inputFile, String separator, Function<String, Long> func, boolean repeating)
    {
        super(inputFile, separator, func, repeating);
    }
    
    public static LongFromFileSupplier create(String inputFile, String separator, boolean infinite)
    {
        return new LongFromFileSupplier(inputFile, separator, infinite);
    }
    
    public static LongFromFileSupplier create(String inputFile, boolean infinite)
    {
        return new LongFromFileSupplier(inputFile, infinite);
    }
    
    public static <T> FromFileSupplier<T> createFromCommaFile(String inputFile, boolean infinite)
    {
        return FromFileSupplier.<T>createFromArray(inputFile, s -> Arrays.stream(s.split(",")).filter(a -> !a.isEmpty()).map(Long::parseLong).map(a -> (T) a).collect(Collectors.toList()), false);
    }
    
    public static <T> FromFileSupplier<T> longCreateFromCommaFile(String inputFile, boolean infinite)
    {
        return FromFileSupplier.<T>createFromArray(inputFile, s -> Arrays.stream(s.split(",")).filter(a -> !a.isEmpty()).map(Long::parseLong).map(a -> (T) a).collect(Collectors.toList()), false);
    }
}
