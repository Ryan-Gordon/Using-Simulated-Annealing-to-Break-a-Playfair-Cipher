package ie.gmit.sw.ai.crypto;

public abstract class AbstractCipher implements CipherEncryptor, CipherDecryptor{

	public abstract String encrypt(String key); //Implemented from CipherEncryptor
	
	public abstract String decrypt(String key); // Implemented from CipherDecryptor

}
