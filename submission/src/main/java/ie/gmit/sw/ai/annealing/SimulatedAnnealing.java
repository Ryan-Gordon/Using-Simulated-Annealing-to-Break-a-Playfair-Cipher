package ie.gmit.sw.ai.annealing;

import java.security.SecureRandom;
import java.util.Random;

import ie.gmit.sw.ai.crypto.PlayfairCipher;
import ie.gmit.sw.ai.crypto.KeyHandler;


/**
 * The SimulatedAnnealing class is responsible for attempting to solve a cipher using SA.
 * 
 * Taking in a starting temperature and a starting key , 
 * a number of iterations (50000 right now) are done during which
 * the key is shuffled and this shuffled variant is used to decrypt the text.
 * This attempted decrpytion is then scored using the QuadGramScorer
 * 
 * The delta of the score is gathered and a comparison is done with the current best key
 * If a new bestScore is found the key, text and score are printed and saved for the next iteration
 * 
 * After the 50k iterations the temperature cools by 1
 * @author ryangordon
 *
 */
public class SimulatedAnnealing {
	private QuadgramsScorer nGramScore;
	private Random rand;
	private KeyHandler keyhandler;
	private String bestKey;
	private String bestText;
	private double bestScore;
	
	public SimulatedAnnealing() {
		super();
		this.nGramScore = new QuadgramsScorer();
		this.rand = new SecureRandom();
		this.keyhandler = new KeyHandler();
	}
	
	public String solve(PlayfairCipher cipher, int temperature) {
		//Set up our initial best values
		String parentKey = keyhandler.getParentNode();
		bestKey = parentKey;
		String decipheredText = cipher.decrypt(parentKey);
		bestText = decipheredText;
		double parentScore = nGramScore.scoreNGram(decipheredText);
		bestScore = parentScore;
		
		//Iterate over for X transitions reducing the temperature as we go 
		for (int temp = temperature; temp > 0; temp--) {
			for (int trans = 50000; trans > 0; trans--) {
				String childKey = keyhandler.shuffleKey(parentKey);
				decipheredText = cipher.decrypt(childKey);
				double childScore = nGramScore.scoreNGram(decipheredText);

				double delta = childScore - parentScore;

				if (delta > 0) {
					parentScore = childScore;
					parentKey = childKey;
				} else {
					double prob = (Math.exp((delta / temp)));
					if (prob > rand.nextDouble()) {
						parentScore = childScore;
						parentKey = childKey;
					}
				}

				if (parentScore > bestScore) {
					bestScore = parentScore;
					bestKey = parentKey;
					bestText = decipheredText;
					
					System.out.println("Better score found with temp "+ temp+" and at iteration "+ trans);
					System.out.println("Better Score: "+ bestScore+" found with key \n Key: "+bestKey);
					System.out.println("Message with this key : \n Message :"+bestText );
					
				}
				
			} // end transitions
			System.out.println("Temperature reducing from "+temp+ " to temp + "+(temp -1) );
			
		} // end temperature
		return new String("Best Score: " + bestScore + " For key: " + this.bestKey + "\nDeciphered message: " + this.bestText);
	}//solve

}
