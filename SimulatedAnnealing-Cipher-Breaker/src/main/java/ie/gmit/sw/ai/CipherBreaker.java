package ie.gmit.sw.ai;

import java.util.Scanner;

import ie.gmit.sw.ai.annealing.SimulatedAnnealing;
import ie.gmit.sw.ai.crypto.PlayfairCipher;

/**
 * Hello world!
 *
 */
public class CipherBreaker 
{
	
    public static void main( String[] args ) throws Throwable
    {
    	FileHandler fh = new FileHandler();
        
        PlayfairCipher playDecryption = new PlayfairCipher(fh.readFile("./files/the-hobbit-cp1.txt"));
        
        SimulatedAnnealing sa = new SimulatedAnnealing();
        sa.solve(playDecryption, 20);
        
        //Present UI to user 
        Menu menu = new Menu();
        menu.showMenu();
        
        
    }
}
    

