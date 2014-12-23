/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CritterPackage;

/** @author Joseph Zhong
 *  Assignment 10
 *  Critter Project - Ant Critter
 *  Version 1.13
 *  24 Feb 2013
 */

import java.awt.Color; // Color 

public class Ant extends Critter
{
    /**
     * boolean @initialMove is a unique field for Ant which determines whether 
     *  it alternates between North and East or South and East
     */
    private boolean initialMove;
    /**
     * integer @steps is a unique field for Ant which determines how many "steps" it
     * has already taken
     * This is useful for calculating whether the method getMove() is supposed
     * to @return the initial direction, or east.
     */
    private int steps;
    
    /**
     * This is a constructor method for Ant
     * integer @steps is initialized as zero
     * @param walkSouth is a boolean value that determines whether the Ant's 
     *  initial move is South or North, true for South, and false for North
     */
    public Ant(boolean walkSouth)
    {
        steps = 0;
        initialMove = walkSouth;
    }
    /**
     * This method acts as a getter for the Ant's color
     * @return Color.RED, as the color for all ants is Red
     */
    
    public Color getColor()
    {
        return Color.RED;
    }
    
    /**
     * This method is the eat behavior of the ant
     *  It specifies the ant's behavior whenever it is prompted to eat a food
     * @return true, the ant always will eat a food when prompted
     */
    
    public boolean eat()
    {
        return true;
    }
    
    /**
     * This method is the attack behavior of the ant
     *  It specifies what type of attack it will use when it encounters an enemy
     * @param opponent The ant will accept a String parameter to know which type 
     *  of enemy it is fighting against
     * @return Attack.SCRATCH, this type of attack will beat all Attack.POUNCE 
     *  attacks, but lose to all Attack.ROAR, and randomly win or lose against itself
     */
    
    public Attack fight(String opponent)
    {
        return Attack.SCRATCH;
    }
    
    /**
     * This method is the getMove behavior of the ant
     *  It specifies which direction will its next move be
     * Ant alternates between North and East or South and East depending on its 
     *  initial construction
     * @return a direction depending on whether it has taken an even or odd 
     *  amount of steps, where north or south will always be even 
     *  (starting from 0) and East will always be uneven 
     */
    
    public Direction getMove() 
    {
        // Works now...optimize maybe later? instead of int, steps could 
        // change to bool
        // oh wow this is really poorly done T__T 
        // should have just changed "initialMove" upon moving LOL 
        // enums op here I guess
        if(initialMove && (steps % 2 == 0))
        {
            steps++;
            return Direction.SOUTH;
        }
        else if(!initialMove && (steps % 2 == 0))
        {
            steps++;
            return Direction.NORTH;
        }
        steps++;
        return Direction.EAST;
    }
    
    /**
     * This method is the toString method
     * @return a module sign that represents the ant
     */
    
    public String toString()
    {
        return "%";
    }
    
} // end class
