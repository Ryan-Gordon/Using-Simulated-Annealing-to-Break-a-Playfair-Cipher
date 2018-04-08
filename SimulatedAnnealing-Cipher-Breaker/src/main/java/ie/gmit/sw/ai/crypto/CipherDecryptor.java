package ie.gmit.sw.ai.crypto;

/**
 * Functional Interface CipherDecryptor is an abstraction of one side of the encryption process.
 * 
 * @author ryangordon
 *
 */
@FunctionalInterface
public interface CipherDecryptor {
	//Used to decrypt with a provided key
	public String decrypt(String key);
	
}
