package utils;

import org.joml.Vector2i;

public class Droid
{
    public int       x         = 0;
    public int       y         = 0;
    public Direction direction = Direction.NORTH;
    
    public void turn(Direction dir)
    {
        this.direction = dir;
    }
    
    public void turn(long turn)
    {
        if (turn == 0L)
        {
            turnLeft();
        } else
        {
            turnRight();
        }
    }
    
    public void move(long dir)
    {
        if (dir == 1L)
        {
            turn(Direction.NORTH);
            step();
        }
        if (dir == 2L)
        {
            turn(Direction.SOUTH);
            step();
        }
        if (dir == 3L)
        {
            turn(Direction.WEST);
            step();
        }
        if (dir == 4L)
        {
            turn(Direction.EAST);
            step();
        }
    }
    
    public void move(Direction dir, int count)
    {
        Direction current = this.direction;
        
        if (dir != Direction.FORWARD && dir != Direction.BACKWARD)
        {
            turn(dir);
        }
        
        for (int i = 0; i < count; i++)
        {
            step();
        }
        
        turn(current);
    }
    
    public void moveByVector(Vector2i position)
    {
        this.x += position.x;
        this.y += position.y;
    }
    
    public void turnLeft()
    {
        this.direction = direction.left();
    }
    
    public void turnRight()
    {
        this.direction = direction.right();
    }
    
    @SuppressWarnings("SuspiciousNameCombination")
    public void rotateLeftAboutZero()
    {
        int temp = this.x;
        this.x = this.y;
        this.y = -temp;
    }
    
    @SuppressWarnings("SuspiciousNameCombination")
    public void rotateRightAboutZero()
    {
        int temp = this.y;
        this.y = this.x;
        this.x = -temp;
    }
    
    public void step()
    {
        switch (direction)
        {
            case NORTH -> y--;
            case SOUTH -> y++;
            case WEST -> x--;
            case EAST -> x++;
        }
    }
    
    public Vector2i position()
    {
        return new Vector2i(x, y);
    }
    
    @Override
    public String toString()
    {
        return "Turtle{" +
               "x=" + x +
               ", y=" + y +
               ", direction=" + direction +
               '}';
    }
}
