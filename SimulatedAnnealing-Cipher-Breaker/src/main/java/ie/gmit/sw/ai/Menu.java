package ie.gmit.sw.ai;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import ie.gmit.sw.ai.annealing.SimulatedAnnealing;
import ie.gmit.sw.ai.crypto.PlayfairCipher;

public class Menu {
	
	private boolean menuFlag = false;
	private PlayfairCipher cipher;
	private SimulatedAnnealing cipherSolver;
    private FileHandler fh;
    
    private String key;
    
    
   


	
	public Menu() {
		super();
		
		this.fh = new FileHandler();
		this.cipherSolver = new SimulatedAnnealing();
		this.menuFlag = true;
	}


	public void showMenu() throws Throwable {
		Scanner sc = new Scanner(System.in);
		while(menuFlag != false) {
			// Main menu for user
			System.out.println("--------------------------------------------");
			System.out.println("Enter a number to select an option:");
			System.out.println("1. Break cipher text with simulated annealing (Hobbit Chapter 1)?");
			System.out.println("2. Break cipher text with simulated annealing (Choose a file)?");
			System.out.println("3. Decrypt cipher text from command prompt?");
			System.out.println("4. Decrypt cipher text from a file?");
			System.out.println("5. Quit?");
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
			case 2:
					//Decrypting custom file
				 	System.out.print("Enter the path of the txt file you want to decrypt: ");
					sc.nextLine();
					String fileName = sc.nextLine();
					
					try {
						cipher = new PlayfairCipher(fh.readFile(fileName));
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						System.out.println("Error reading the file specified : "+ fileName);
					}
					cipherSolver = new SimulatedAnnealing();
					
					System.out.print("Enter the temperature: ");
					System.out.println(cipherSolver.solve(cipher, sc.nextInt()));
		        break;
			case 3:
				
				sc.nextLine();
		        System.out.println("Enter a key:");
		        key = sc.next();
				System.out.print("Enter the cipher text to decrypt: ");
				sc.nextLine();
				String cipherText = sc.nextLine();
				
				try {
					cipher = new PlayfairCipher(cipherText);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					System.out.println("Error reading the text specified : "+ cipherText);
				}
				//System.out.println(text);
				String result = cipher.decrypt(key);
				
				
				System.out.println("Result " + result);
				break;
				
			case 4 :
				sc.nextLine();
		        System.out.println("Enter a key:");
		        key = sc.next();
				System.out.print("Enter the name of the txt file you want to decrypt: ");
				sc.nextLine();
				
				try {
					cipher = new PlayfairCipher(fh.readFile(sc.nextLine()));
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					System.out.println("Error reading the file specified ");
				}
			
				System.out.println("Result " + cipher.decrypt(key));
				break;
			
			case 5:
				System.out.println("Quitting application");
				sc.close();
				menuFlag = false;
				break;
			}//switch
		}
	}
}