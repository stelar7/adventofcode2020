package utils;

import java.util.*;

public class CircularQueue<T>
{
    private final int     size;
    private       List<T> items = new ArrayList<>();
    
    public CircularQueue(int size)
    {
        this.size = size;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public T get(int index)
    {
        return items.get(index);
    }
    
    public List<T> getItems()
    {
        return items;
    }
    
    public void add(T item)
    {
        if (items.size() >= size)
        {
            T result = items.remove(0);
            System.out.println("removed item " + result);
            
        }
        
        items.add(item);
    }
}
