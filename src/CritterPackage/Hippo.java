/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CritterPackage;

/** 
 * @author Joseph Zhong
 * Assignment 10
 * Critter Project - Hippo Critter
 * Version 1.14
 * 1 March 2013
 */


import java.awt.Color;
import java.util.Random;

public class Hippo extends Critter
{
    /**
     * @hungryCount is a counter of how many foods the hippo must eat
     */
    private int hungryCount;
    /**
     * @steps is a counter of how many steps the hippo has moved
     */
    private int steps;
    /**
     * @move is the current direction the hippo takes
     */
    private Direction move;
    /**
     * @gen is a random object to produce a new direction after each cycle
     */
    private Random gen;
    
    /**
     * Constructor. 
     * @param hunger is an integer value that sets the value of hungryCount, 
     *  that determines how many times the hippo must eat before it becomes 
     *  "full". 
     * @steps is an integer that increments every time the hippo moves, but 
     *  resets at 5
     * @move is a variable that keeps track of its current direction to move
     */
    public Hippo(int hunger)
    {
        gen = new Random(4);
        hungryCount = hunger;
        steps = 5;
        move = generateMove();
    }
     
    /**
     * Get method for color. 
     * @return Gray if the hippo is still hungry, otherwise white.
     */
    
    public Color getColor()
    {
        if(hungryCount > 0)
        {
            return Color.GRAY;
        }
        return Color.WHITE;
    }
    
    /**
     * Boolean method that determines if the hippo will eat. 
     * @return true if hungryCount, the times the hippo will eat, 
     *  is not zero. Otherwise false
     */
    
    public boolean eat()
    {
        if(hungryCount > 0)
        {
            hungryCount--;
            return true;
        }
        return false;
    }
    
    /**
     * Attack method that determines which attack the hippo
     *  will use.
     * @param o A string that marks what type of opponent 
     *  the hippo is fighting with
     * @return an enumerated Attack value, Scratch if the 
     *  hippo is hungry, otherwise pounce.
     */
    
    public Attack fight(String o)
    {
        if(hungryCount > 0)
        {
            return Attack.SCRATCH;
        }
        return Attack.POUNCE;
    }
    
    /**
     * Getter method for the hippo's next move. Moves five 
     *  steps in a random direction, and another five in 
     *  another random direction.
     * @return a randomly generated direction at the 
     *  beginning of the loop, otherwise the previous direction
     */
    
    public Direction getMove()
    {
        if(steps < 5)
        {
            steps++;
            return move;
        }
        else 
        {
            move = generateMove();
            steps = 0;
            return move;
        }
    }
    
    /**
     * Direction method that uses the Random object to generate
     *  a random direction.
     * @return North, East, South, or West based on the value of 
     *  the randomly generated number from the Random Object @gen
     */
    private Direction generateMove()
    {
        if(gen.nextInt() == 0)
        {
            return Direction.EAST;
        }
        else if(gen.nextInt() == 1)
        {
            return Direction.NORTH;
        }
        else if(gen.nextInt() == 2)
        {
            return Direction.SOUTH;
        }
        else 
        {
            return Direction.WEST;
        }
    }
    
    /**
     * The String toString method. 
     * @return a String of @hungryCount, or how many more times 
     *  the Hippo will still need to eat
     */
    
    public String toString()
    {
        return Integer.toString(hungryCount);
    }
}
