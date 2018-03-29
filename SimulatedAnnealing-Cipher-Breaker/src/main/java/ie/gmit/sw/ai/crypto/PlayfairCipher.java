package ie.gmit.sw.ai.crypto;

import java.util.*;
/**
 * PlayfairCipher is a Java implementation of the famous Playfair Cipher.
 * 
 * 
 * The class Playfair cipher currently is an extension of AbstractCipher.
 * AbstractCipher itself is the abstract implementation of two FunctionalInterfaces 
 * Each interface performs one side of the encryption/decrpytion process. 
 * 
 * @author ryangordon
 *
 */
public class PlayfairCipher extends AbstractCipher {
	private static final String PLAYFAIR_ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

	private String key;
	private String[] diagraphs;
	
	public PlayfairCipher(String text) {
		super();
		this.diagraphs = createNGraphs(text,2);
	}
	
	public String[] createNGraphs(String text, int n) {
		text = removeDuplicates(text);
		//Break the text into n length diagraphs
		String digrams[] = new String[text.length() / n];
		int j = 0;
		
		for(int i = 0; i < text.length(); i = i + n) {
			digrams[j] = text.substring(i, i + n);
			j++;
		}
		
		return digrams;
	}	
	

	public String removeDuplicates(String text) {
		// Regex remove anything that isn't a word character also replace all instances of j with nothing
		text = text.replaceAll("\\W", "").replace("J", "");
		
		StringBuilder builder = new StringBuilder(text.toUpperCase());
		//For loop which removes recurring characters
		for(int i = 0; i < text.length() - 1; i += 2) {
			if(builder.charAt(i) == builder.charAt(i + 1)) {
				builder.replace((i + 1), (i + 2), "X"); 
			}
		}
		
		if(builder.length() % 2 == 1) {
			builder.append("X");
		}
	
		return builder.toString();
	}
	
	public String decrypt(String key) {
		StringBuilder plainText = new StringBuilder();

		for (String pair : this.diagraphs) {
			char ch1,ch2;
			//Set up the rows and columns to evaluate our rules on
			int r1 = key.indexOf(pair.charAt(0)) / 5;
			int c1 = key.indexOf(pair.charAt(0)) % 5; 
			int r2 = key.indexOf(pair.charAt(1)) / 5; 
			int c2 = key.indexOf(pair.charAt(1)) % 5;
			
			//There are three ways for the Playfair Cipher to shuffle characters 
			if (c1 == c2) { // Diagraph Letters in Same Column
				ch1 = key.charAt((r1 + 4) % 5 * 5 + c1);
				ch2 = key.charAt((r2 + 4) % 5 * 5 + c2);
			} else if (r1 == r2) { // Diagraph Letters in Same Row
				ch1 = key.charAt(r1 * 5 + (c1 + 4) % 5);
				ch2 = key.charAt(r2 * 5 + (c2 + 4) % 5);
			} else { // Diagraph Letters in Different Rows and Columns
				ch1 = key.charAt(r1 * 5 + c2); 
				ch2 = key.charAt(r2 * 5 + c1);
			}
			//Append result to SB
			plainText.append(Character.toString(ch1) + Character.toString(ch2));
		}

		return plainText.toString();
	}

	public String encrypt(String key) {
		return "";
	}

}
