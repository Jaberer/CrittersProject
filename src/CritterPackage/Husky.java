/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CritterPackage;

/**
 *
 *  Joseph Zhong
 *  Assignment 10
 *  Critter Project-Husky Object
 *  Version 3.0
 *  2 March 2014
 *
 **/

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Husky extends Critter
{
    //density
    private static int huskies;
            
    //neighbors... 
    private static final int _EAST = 0;
    private static final int _SOUTH = 1;
    private static final int _WEST = 2;
    private static final int _NORTH = 3;
    
    //pseudo counter for density LOL
    private static int moves;
    
    //uh. Density/movement/hunting/protection/planning?
    private String[] adjacentObjects;
    private int x;
    private int y;
    private int moveGoal;
    
    
    //goals
    private static int goal;
    private static final int EAT = 1;
    private static final int HUNT = 2;
    private static final int LEARN = 3;
    
    private static int roar = 0;
    private static int pounce = 0;
    private static int scratch = 0;
    
    private static ArrayList<Attack> previous;
    
    private String randomReturn;
    
    private static Attack adjustable;

    private Random gen;
    
    public Husky()
    {   
        gen = new Random();
        randomReturn = "";
    }
    
    
    public Color getColor()
    {
        return Color.PINK;
    }
   
    
    public boolean eat()
    {
        if(goal == EAT || goal == LEARN)
        {
            if(adjacentObjects[0].equals(" ") && adjacentObjects[1].equals(" ") 
                && adjacentObjects[2].equals(" ") && adjacentObjects[3].equals(" "))
            {
                return true;
            }
        }
        return false;
    }
    
    
    public Attack fight(String o)
    {
        calculateAttack(o);
        return adjustable;
    }
    
    public void calculateAttack(String o)
    {
        if(goal == HUNT)
        {
            if(o.equals("%"))
            {
                adjustable = Attack.ROAR;
            }
            else if((o.equals("^")) || (o.equals(">")) 
                    || (o.equals("v")) ||(o.equals("<")))
            {
                if(randomReturn.equals("%"))
                {
                    adjustable = Attack.POUNCE;
                }
                else
                {
                    adjustable = Attack.SCRATCH;
                }
            }
            else if(Integer.parseInt(o) > 0)
            {
                adjustable = Attack.SCRATCH;
            }
            else if(Integer.parseInt(o) == 0)
            {
                adjustable = Attack.POUNCE;
            }
            else if(o.equals("S"))
            {
                adjustable = Attack.POUNCE;
            }
            else 
            {
                adjustable = randomAttack();
            }
        }
        else if(goal > HUNT)
        {

            for(int i = 0; i <= previous.size(); i++)
            {
                if(previous.get(i).equals(Attack.POUNCE))
                {
                    pounce++;
                }
                if(previous.get(i).equals(Attack.ROAR))
                {
                    roar++;
                }
                else if(previous.get(i).equals(Attack.SCRATCH))
                {
                    scratch++;
                }
            }
            if(roar > pounce && roar > scratch) // roar is most possible
            {
                adjustable = Attack.POUNCE;
            }
            else if(pounce > scratch && pounce > roar)
            {
                adjustable = Attack.SCRATCH;
            }
            else if(scratch > roar && scratch > pounce)
            {
                adjustable = Attack.ROAR;
            }
            else 
            {
                adjustable = randomAttack();
            }
        }
    }
    
    public Attack randomAttack()
    {
        int temp = gen.nextInt(3);
        if(temp == 0)
        {
            return Attack.ROAR;
        }
        else if(temp == 1)
        {
            return Attack.POUNCE;
        }
        else
        {
            return Attack.FORFEIT;
        }
    }
    
    
    public Direction getMove()
    {
        return calculateMove();
    }
    
    public Direction calculateMove()
    {
        x = getX();
        y = getY();
        // mate goal
        if(goal == HUNT)
        {
            if(moveGoal == 0) // 0,0
            {
                if(x>3)
                {
                    return Direction.WEST;
                }
                if(y>3)
                {
                    return Direction.NORTH;
                }
            }
            if(moveGoal == 1) // 0 , width
            {
                if(x<getWidth()-3)
                {
                    return Direction.EAST;
                }
                if(y>3)
                {
                    return Direction.NORTH;
                }
            }
            if(moveGoal == 2) // height, 0
            {
                if(x>3)
                {
                    return Direction.WEST;
                }
                if(y<getHeight()-3)
                {
                    return Direction.SOUTH;
                }
            }
            if(moveGoal == 3)
            {
                if(x<getWidth()-3)
                {
                    return Direction.EAST;
                }
                if(y<getHeight()-3)
                {
                    return Direction.SOUTH;
                }
            }
        }
        else if(goal == EAT)
        {
            adjacentObjects[_EAST] = getNeighbor(Direction.EAST);
            adjacentObjects[_SOUTH] = getNeighbor(Direction.SOUTH);
            adjacentObjects[_WEST] = getNeighbor(Direction.WEST);
            adjacentObjects[_NORTH] = getNeighbor(Direction.NORTH);
            if(adjacentObjects[_EAST].equals("."))
            {
                return Direction.EAST;
            }
            else if(adjacentObjects[_SOUTH].equals("."))
            {
                return Direction.SOUTH;
            }
            else if(adjacentObjects[_WEST].equals("."))
            {
                return Direction.WEST;
            }
            else if(adjacentObjects[_NORTH].equals("."))
            {
                return Direction.NORTH;
            }           
            
        }
        
        
        int temp = gen.nextInt(5);
        if(temp == 0)
        {
            return Direction.CENTER;
        }
        else if(temp == 1)
        {
            return Direction.EAST;
        }
        else if(temp == 2)
        {
            return Direction.NORTH;
        }
        else if(temp == 3)
        {
            return Direction.WEST;
        }
        else 
        {
            return Direction.SOUTH;
        }
    }
    
    public static void calculateGoal()
    {
        if(moves<100)
        {
            goal = HUNT;
        }
        else if(moves<400)
        {
            goal = EAT;
        }
        else
        {
            goal = LEARN;
        }
    }
    
    
    public String toString()
    {
        calculateString();
        return randomReturn;
    }
    
    public void calculateString()
    {
        int temp = gen.nextInt(3);
        if(temp == 0)
        {
            randomReturn = "%";
        }
        else if(temp == 1)
        {
            if(calculateMove().equals(Direction.NORTH))
            {
                randomReturn = "^";
            }
            else if(calculateMove().equals(Direction.EAST))
            {
                randomReturn = ">";
            }
            else if(calculateMove().equals(Direction.SOUTH))
            {
                randomReturn = "v";
            }
            else
            {
                randomReturn = "<";
            }
        }
        else 
        {
            randomReturn =  "" + temp;
        }
    }
    
    
    public void win()
    {
        learnWin();
    }
    
    public void learnWin()
    {
        if(previous.size() > 7)
        {
            if(adjustable.equals(Attack.POUNCE))
            {
                previous.add(Attack.ROAR);
                previous.remove(0);
            }
            else if(adjustable.equals(Attack.ROAR))
            {
                previous.add(Attack.SCRATCH);
                previous.remove(0);
            }
            else if(adjustable.equals(Attack.SCRATCH))
            {
                previous.add(Attack.POUNCE);
                previous.remove(0);
            }
        }
        else
        {
            if(adjustable.equals(Attack.POUNCE))
            {
                previous.add(Attack.ROAR);
            }
            else if(adjustable.equals(Attack.ROAR))
            {
                previous.add(Attack.SCRATCH);
            }
            else if(adjustable.equals(Attack.SCRATCH))
            {
                previous.add(Attack.POUNCE);
            }
        }
    }
    
    
    public void lose()
    {
        huskies--;
    }
    
    public void learnLose()
    {
        if(previous.size() > 7)
        {
            if(adjustable.equals(Attack.POUNCE))
            {
                previous.add(Attack.SCRATCH);
                previous.remove(0);
            }
            else if(adjustable.equals(Attack.ROAR))
            {
                previous.add(Attack.POUNCE);
                previous.remove(0);
            }
            else if(adjustable.equals(Attack.SCRATCH))
            {
                previous.add(Attack.ROAR);
                previous.remove(0);
            }
        }
        else
        {
            if(adjustable.equals(Attack.POUNCE))
            {
                previous.add(Attack.SCRATCH);
            }
            else if(adjustable.equals(Attack.ROAR))
            {
                previous.add(Attack.POUNCE);
            }
            else if(adjustable.equals(Attack.SCRATCH))
            {
                previous.add(Attack.ROAR);
            }
        }
    }
    
    
    public void mateEnd()
    {
        huskies++;
    }
    
    
    public void reset()
    {
        huskies = 25;
    }
    
    // begin op strats
    private void findNeighbors()
    {
        adjacentObjects[_EAST] = getNeighbor(Direction.EAST);
        adjacentObjects[_SOUTH] = getNeighbor(Direction.SOUTH);
        adjacentObjects[_WEST] = getNeighbor(Direction.WEST);
        adjacentObjects[_NORTH] = getNeighbor(Direction.NORTH);
    }
}
