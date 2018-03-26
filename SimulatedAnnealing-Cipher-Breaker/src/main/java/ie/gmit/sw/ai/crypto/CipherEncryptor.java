package ie.gmit.sw.ai.crypto;

@FunctionalInterface
public interface CipherEncryptor {
	
	public String decrypt(String key);
}
