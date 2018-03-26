package ie.gmit.sw.ai.crypto;


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
	private String key;
	
	public PlayfairCipher(String text) {
		super();
		
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
		
	}

	public String encrypt(String key) {
		
	}
	}
