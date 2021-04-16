package utils;

public enum Direction
{
    NORTH(1L), SOUTH(2L), WEST(3L), EAST(4L), FORWARD(5L), BACKWARD(6L);
    
    public long val;
    
    Direction(long l)
    {
        this.val = l;
    }
    
    public static Direction fromLetter(String letter)
    {
        if (letter.equalsIgnoreCase("N"))
        {
            return NORTH;
        }
        
        if (letter.equalsIgnoreCase("S"))
        {
            return SOUTH;
        }
        
        if (letter.equalsIgnoreCase("W"))
        {
            return WEST;
        }
        
        if (letter.equalsIgnoreCase("E"))
        {
            return EAST;
        }
        
        if (letter.equalsIgnoreCase("F"))
        {
            return FORWARD;
        }
        
        if (letter.equalsIgnoreCase("B"))
        {
            return BACKWARD;
        }
        
        return null;
    }
    
    public Direction left()
    {
        return switch (this)
                {
                    case WEST -> SOUTH;
                    case SOUTH -> EAST;
                    case EAST -> NORTH;
                    case NORTH -> WEST;
                    default -> throw new RuntimeException("er");
                };
    }
    
    public Direction right()
    {
        return switch (this)
                {
                    case NORTH -> EAST;
                    case EAST -> SOUTH;
                    case SOUTH -> WEST;
                    case WEST -> NORTH;
                    default -> throw new RuntimeException("er");
                };
    }
    
    public Direction opposite()
    {
        return switch (this)
                {
                    case NORTH -> SOUTH;
                    case SOUTH -> NORTH;
                    case EAST -> WEST;
                    case WEST -> EAST;
                    case FORWARD -> BACKWARD;
                    case BACKWARD -> FORWARD;
                };
    }
}
