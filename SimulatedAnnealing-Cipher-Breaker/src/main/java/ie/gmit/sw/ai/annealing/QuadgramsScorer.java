package ie.gmit.sw.ai.annealing;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * QuadGramsScorer is used to parse a text files called 4grams.txt containing QuadGrams and their scores for use
 * A map of quadgrams and their scores is stored in quadGramMap 
 * 
 * The data is loaded in from the file and streamed in a map
 * @author ryangordon
 *
 */
public class QuadgramsScorer {
	private static final String TEXTFILE = "4grams.txt"; 
	private Map<String, Integer> quadGramMap;
	private long totalQuadgrams;
	
	public QuadgramsScorer() {
		//Try to load in the quadgrams from the file at root
		//Throw a message to user if theres a problem
		try {
			this.quadGramMap = loadNGrams(TEXTFILE);
			this.totalQuadgrams = getTotalQuadgrams();
		} catch (Exception e) {
			System.out.println("Error occurred while creating QuadGrams, do you have the 4grams.txt at the root of the project? Message: " + TEXTFILE);
		}	
	}
	/**
	 * Takes in a filepath and opens a stream on that path
	 * 
	 * Maps the lines in the textfile to a Map with the quadram as the key and its score as the value
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private Map<String, Integer> loadNGrams(String fileName) throws IOException {		
		Stream<String> lines = Files.lines(Paths.get(fileName));
		
		Map<String, Integer> quadgrams = 
				lines.map(line -> line.split(" "))
				.collect(Collectors.toMap(line -> line[0], line -> Integer.parseInt(line[1])));
		
		lines.close();

		return quadgrams;
	}
	
	private long getTotalQuadgrams() {
		return quadGramMap.values().stream().mapToLong(i->i).sum();
	}
	/**
	 * scoreNGram takes a key and breaks it into quadgrams.
	 * Each quadgram is queried from the map and a score retrieved or a default score of 1
	 * The result is then divided by the totalQuadgrams and added to the score
	 * 
	 * A score is returned for the provided key
	 * @param text
	 * @return
	 */
	public double scoreNGram(String text) {
		double score = 0; //Initilise score
		//Break the key into a number of diagrams (4 length strings)
		for (int i = 0; i < text.length() - 4; i++) {
			score += Math.log10((double) (((quadGramMap.get(text.substring(i, i + 4)) != null)
					? quadGramMap.get(text.substring(i, i + 4)) //Consequent 
					: 1) //Alternative
					) / this.totalQuadgrams); //Divide the result then log10
		}
		return score;
	}
	
}
