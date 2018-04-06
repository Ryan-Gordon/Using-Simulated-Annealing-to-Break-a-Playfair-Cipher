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
        System.out.println( "Hello World!" );
        
        FileHandler fh = new FileHandler();
        System.out.println(fh.listFiles("./files"));
        
        PlayfairCipher playDecryption = new PlayfairCipher(fh.readFile("./files/the-hobbit-cp1.txt"));
        
        System.out.println(fh.readFile("./files/the-hobbit-cp1.txt"));
        System.out.println(playDecryption.decrypt("THEQUICKBROWNFXMPDVLAZYGS"));
        
        PlayfairCipher playEncryption = new PlayfairCipher("Happy Days");
        System.out.println(playEncryption.encrypt("THEQUICKBROWNFXMPDVLAZYGS"));
        PlayfairCipher playEncryption2 = new PlayfairCipher(playEncryption.encrypt("THEQUICKBROWNFXMPDVLAZYGS"));
        System.out.println(playEncryption2.decrypt("THEQUICKBROWNFXMPDVLAZYGS"));
        
        SimulatedAnnealing sa = new SimulatedAnnealing();
        sa.solve(playDecryption, 20);
        
        
        
    }
}
    

