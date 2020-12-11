package utils;

import org.joml.Vector2i;

import java.util.*;

public class Cell
{
    private final Vector2i location;
    
    private List<Long>     states;
    private List<Vector2i> neighbourLocations;
    private long           state;
    
    public Cell(Vector2i location, long state)
    {
        this.location = location;
        this.state = state;
    }
    
    public Cell(Vector2i location, long state, List<Long> states, List<Vector2i> neighbours)
    {
        this.location = location;
        this.state = state;
        this.states = states;
        this.neighbourLocations = neighbours;
    }
    
    public Cell(Cell cell)
    {
        this(cell.location, cell.state, cell.states, cell.neighbourLocations);
    }
    
    public Vector2i getLocation()
    {
        return location;
    }
    
    public long getState()
    {
        return state;
    }
    
    public void toggleState()
    {
        this.state = this.states.get((this.states.indexOf(this.state) + 1) % this.states.size());
    }
    
    public List<Vector2i> getNeighbourLocations()
    {
        return neighbourLocations;
    }
    
    public void setNeighbours(List<Vector2i> neighbours)
    {
        this.neighbourLocations = neighbours;
    }
    
    public void setStates(List<Long> states)
    {
        this.states = states;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Cell cell = (Cell) o;
        return state == cell.state &&
               Objects.equals(location, cell.location);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(location, state);
    }
}
