/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CritterPackage;

/**
 *
 * @author Joseph Zhong
 *  Assignment 10
 *  Critter Project - Vulture Critter
 *  Version 2.2
 *  1 March 2013
 *
 **/

import java.awt.Color;


public class Vulture extends Bird
{
    /**
     * @hungry is a boolean that determines whether or not the vulture is hungry
     */
    private boolean hungry;
    /**
     * @temp is a temporary boolean that helps change the state of the vulture
     */
    private boolean temp;
    
    public Vulture()
    {
        super();
        hungry = true;
    }
    
    /**
     * Color getter method.
     * @return the color black
     */
    
    public Color getColor()
    {
        return Color.BLACK;
    }
    
    /**
     * Boolean method for eat. Vultures eat once, unless it fights 
     * @return true if it is hungry, false otherwise
     */
    
    public boolean eat()
    {
        if(hungry)
        {
            temp = hungry;
            hungry = false;
            return temp;
        }
        return hungry;
    }
     

}
