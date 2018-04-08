package ie.gmit.sw.ai;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ie.gmit.sw.ai.annealing.SimulatedAnnealing;
import ie.gmit.sw.ai.crypto.PlayfairCipher;

public class Menu {
	
	private PlayfairCipher cipher;
	private SimulatedAnnealing cipherSolver;
    private FileHandler fh;
    
    private String key;
    
    
   


	
	public Menu() {
		super();
		
		this.fh = new FileHandler();
		this.cipherSolver = new SimulatedAnnealing();
	}


	public void showMenu() throws Throwable {
		Scanner sc = new Scanner(System.in);
			// Main menu for user
			System.out.println("--------------------------------------------");
			System.out.println("Enter a number to select an option:");
			System.out.println("1. Break cipher text with simulated annealing (Hobbit Chapter 1)?");
			
			int choice = sc.nextInt();
			System.out.println("--------------------------------------------");
			
			switch (choice) {
			case 1: 
					//Decrypting the hobbit chapter 1 
					cipher = new PlayfairCipher(fh.readFile("./files/the-hobbit-cp1.txt"));
					cipherSolver = new SimulatedAnnealing();
					System.out.print("Enter the temperature: ");
					System.out.println(cipherSolver.solve(cipher, sc.nextInt()));
		        break;
			}//switch
		
	}
}