package ie.gmit.sw.ai;

import java.util.Scanner;

import ie.gmit.sw.ai.annealing.SimulatedAnnealing;
import ie.gmit.sw.ai.crypto.PlayfairCipher;

/**
 * CipherBreaker is the 'main' class of this project and the one that should be run
 * 
 * When the app is started a Menu is shown and users may choose from 6 options 
 * Once an option is chosen the Menu object delagates the task to a composed Object and returns the result.
 * 
 * @author ryangordon
 */
public class CipherBreaker 
{
	
    public static void main( String[] args ) throws Throwable
    {   
        //Present UI to user 
        Menu menu = new Menu();
        menu.showMenu();
    }
}
    

