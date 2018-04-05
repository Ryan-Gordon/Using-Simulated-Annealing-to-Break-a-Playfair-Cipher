package ie.gmit.sw.ai.crypto;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class KeyHandler {
	//The alphabet minus J
	private static final String PLAYFAIR_ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	
	
	public KeyHandler() {
		super();
		this.parentNode.setKey(shuffleKey(PLAYFAIR_ALPHABET));
	}
	
	public KeyHandler(PlayfairKey parentNode) {
		super();
		this.parentNode = parentNode;
	}
	private PlayfairKey parentNode = new PlayfairKey();
	
	// Generate a key from a provided keyTable
	public String generateKey(char[][] keyTable) {
		String s = "";
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				s+= keyTable[i][j];
			}
		}
		return s;
	}		
	// Shuffling with simmulated annealing
	public String shuffleKey(String key) {	
		int random = (int)Math.floor(Math.random() * 100);
		this.setParentTable(key);		
		switch (random) {
			case 1:
			case 2:
				// Flip all rows (2%).
				this.flipRows();
				break;
			case 3:
			case 4:
				// Flip all columns (2%). 
				this.flipColumns();
				break;
			case 5:
			case 6:
				// Reverse the whole key (2%).
				this.reverseKey();	
				break;
			case 7:
			case 8:
				// Swap columns (2%).
				this.swapRandomColumns();
				break;
			case 9:
			case 10:
				// Swap random rows (2%).
				this.swapRandomRows();
				break;
			default:
				// The majority case; Swap Random characters (90%)
				this.swapRandomChars();			
				break;
		}
		String shuffledKey = this.generateKey(this.getParentTable());
		return shuffledKey;
	}
	// Swap around random rows in the current key node to set as the next child node.
	private void swapRandomRows() {
		int r1 = 0, r2 = 0;
		// Select two unique columns.
		while(r1 == r2){
			r1 = (int)Math.floor(Math.random()*5);
			r2 = (int)Math.floor(Math.random()*5);
		}
		// Swap.
		char[][] table = this.getParentTable();
		char[] tmpMatrix = table[r1];
		table[r1] = table[r2];
		table[r2] = tmpMatrix;
		this.setParentTable(table);
	}
	// Swap around random columns in the current key node to set as the next child node.
	private void swapRandomColumns() {		
		int c1 = 0, c2 = 0;
		// Select two unique columns.
		while(c1 == c2){
			c1 = (int)Math.floor(Math.random()*5);
			c2 = (int)Math.floor(Math.random()*5);
		}
		// Swap.
		for (int i = 0; i < 5; i++) {
			char[][] table = this.getParentTable();
			char tmpMatrix = table[i][c1];
			table[i][c1] = table[i][c2];
			table[i][c2] = tmpMatrix;
			this.setParentTable(table);
		}		
	}
	// Flip all rows in the current key node to set as the next child node.
	private void flipRows() {
		char[][] table = this.getParentTable();
		for (int i = 0; i < 5; i++) {
			char[] tmpMatrix = table[i];
			for(int j = 0; j < tmpMatrix.length / 2; j++) {
			    char temp2 = tmpMatrix[j];
			    tmpMatrix[j] = tmpMatrix[tmpMatrix.length - j - 1];
			    tmpMatrix[tmpMatrix.length - j - 1] = temp2;
			}
			table[i] = tmpMatrix;			
		}
		this.setParentTable(table);
	}
	// Flip all columns in the current key node to set as the next child node.
	private void flipColumns() {
		char[][] table = this.getParentTable();
		for (int i = 0; i < 5/2; i++) {
			for (int j = 0; j < 5; j++) {
				char tmpMatrix = table[i][j];
				table[i][j] = table[5 - i - 1][j];
				table[5 - i - 1][j] = tmpMatrix;
			}
		}
		this.setParentTable(table);
	}
	
	private void reverseKey() {
		this.flipRows();
		this.flipColumns();
	}
	// Swap random single chars and set parent key
	private void swapRandomChars() {
		int r1 = 0, c1 = 0, r2 = 0, c2 = 0;
		// Select unique coordinates to swap.
		while ((r1 == r2) && (c1 == c2)){
			r1 = (int) Math.floor((Math.random()*5));
			c1 = (int) Math.floor((Math.random()*5));
			r2 = (int) Math.floor((Math.random()*5));
			c2 = (int) Math.floor((Math.random()*5));	
		}
		// Swap.
		char[][] table = this.getParentTable();
		char tmpMatrix = table[r1][c1];
		table[r1][c1] = table[r2][c2];
		table[r2][c2] = tmpMatrix;
		this.setParentTable(table);
	}
	// Getters and setters.
	public char[][] getParentTable() {
		return this.parentNode.getKeyTable();
	}
	public void setParentTable(String key) {
		char[][] tmpMatrix = new char[5][5];
		int run = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				tmpMatrix[i][j] = key.charAt(run);
				run++;
			}	
		}
		this.parentNode.setKeyTable(tmpMatrix);
	}
	public void setParentTable(char[][] keyTable) {
		this.parentNode.setKeyTable(keyTable);
	}
	public String getParentNode() {
		return this.parentNode.getKey();
	}
	public void setParentNode(String key) {
		char[] k = key.toCharArray();
		int i;
		Random random = ThreadLocalRandom.current();
		// Original shuffle.
		for (int j = k.length - 1; j > 0; j--) {
			i = random.nextInt(j + 1);
			if (i != j) {
				k[i] ^= k[j];
				k[j] ^= k[i];
				k[i] ^= k[j];
			 }
		}
		this.parentNode.setKey(String.valueOf(k));
		this.setParentTable(this.parentNode.getKey());
	}
}
