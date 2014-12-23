/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CritterPackage;

/** @author Joseph Zhong
 *  Assignment 10
 *  Critter Project - Bird Critter
 *  Version 3.2
 *  24 Feb 2013
 */

import java.awt.Color;

public class Bird extends Critter
{
    /**
     * @step3 is an integer that counts the bird's step progress in the cycle
     */
    private int step3;
    
    /**
     * @Current is a Direction that keeps track of the bird's current move in the cycle
     */
    private Direction current;
    
    /**
     * Constructor. Restarts the Cycle
     */
    public Bird()
    {
        step3 = 0;
        current = Direction.WEST;
    }
    
    /**
     * Color getter method.
     * @return Color blue
     */
    
    public Color getColor()
    {
        return Color.BLUE;
    }
    
    /**
     * Eat getter method
     * @return always false
     */
    
    public boolean eat()
    {
        return false;
    }
    
    /**
     * Fighter Attack Method
     * @param o String to represent the opponent
     * @return a ROAR if opponent is an Ant, otherwise POUNCE
     */
    
    public Attack fight(String o)
    {
        if(o.equals("%"))
        {
            return Attack.ROAR;
        }
        return Attack.POUNCE;
    }
    
    /**
     * getMove method that finds the object's next move
     * @return a direction, based on what part of the cycle 
     */
    
    public Direction getMove()
    {
        if(step3 < 3)
        {
            if(current.equals(Direction.WEST))
            {
                current = Direction.NORTH;
            }
            step3++;
            
            return current;
        }
        else if(step3 < 6)
        {
            if(current.equals(Direction.NORTH))
            {
                current = Direction.EAST;
            }
            step3++;
            return current;
        }
        else if(step3 < 9)
        {
            if(current.equals(Direction.EAST))
            {
                current = Direction.SOUTH;
            }
            step3++;
            return current;
        }
        else if(step3 < 12)
        {
            if(current.equals(Direction.SOUTH))
            {
                current = Direction.WEST;
            }
            step3++;
            return current;
        }
        else
        {
            step3 = 1;
            current = Direction.NORTH;
            return current;
        }
    }
    
     /**
      * toString method 
      * @return a v shape in the direction of the move that the Bird previously
      *  moved in
      */
    
    public String toString()
    {
        if(current.equals(Direction.NORTH))
        {
            return "^";
        }
        else if(current.equals(Direction.EAST))
        {
            return ">";
        }
        else if(current.equals(Direction.SOUTH))
        {
            return "v";
        }
        else
        {
            return "<";
        }
    }
}
