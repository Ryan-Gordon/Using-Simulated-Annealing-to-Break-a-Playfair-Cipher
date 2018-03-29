package ie.gmit.sw.ai;

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
        
        PlayfairCipher play = new PlayfairCipher(fh.readFile("./files/the-hobbit-cp1.txt"));
       

        
        
        System.out.println(fh.readFile("./files/the-hobbit-cp1.txt"));
        System.out.println(play.decrypt("THEQUICKBROWNFXMPDVLAZYGS"));
        
    }
}
    

