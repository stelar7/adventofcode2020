package utils;

public enum Direction
{
    NORTH(1L), SOUTH(2L), WEST(3L), EAST(4L);
    
    public long val;
    
    Direction(long l)
    {
        this.val = l;
    }
    
    public Direction left()
    {
        switch (this)
        {
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            case NORTH:
                return WEST;
            default:
                throw new RuntimeException("er");
        }
    }
    
    public Direction right()
    {
        switch (this)
        {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                throw new RuntimeException("er");
        }
    }
    
    public Direction opposite()
    {
        switch (this)
        {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                throw new RuntimeException("er");
        }
    }
}
