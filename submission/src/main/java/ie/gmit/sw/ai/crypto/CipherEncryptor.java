package ie.gmit.sw.ai.crypto;
/**
 * Functional Interface CipherEncryptor is an abstraction of one side of the encryption process.
 * 
 * @author ryangordon
 *
 */
@FunctionalInterface
public interface CipherEncryptor {
	//Used to encrypt with a provided key
	public String encrypt(String key);
}
