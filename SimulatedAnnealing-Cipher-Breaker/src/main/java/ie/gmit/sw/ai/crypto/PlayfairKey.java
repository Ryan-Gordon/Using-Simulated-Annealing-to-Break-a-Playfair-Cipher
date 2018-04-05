package ie.gmit.sw.ai.crypto;

/**
 * Playfair key is an object holding two instance variables :
 * 
 * A key which is a string representing the key for a cipher
 * A keyTable which is a 2D 5x5 array of characters resembling a key
 * @author ryangordon
 *
 */
public class PlayfairKey {
	private char [][] keyTable;	
	private String key;
	
	
	//Keytable getter and setter
	public char [][] getKeyTable() {
		return keyTable;
	}
	public void setKeyTable(char [][] keyTable) {
		this.keyTable = keyTable;
	}
	//Key getter and setter
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}

