package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileHandler is a utility class which is used to handle reading, writing files and displaying the contents of a file folder.
 * @author ryangordon
 *
 */
public class FileHandler {
	
	/**
	 * Takes in a provided filename and attempts to read the text inside
	 * Returns a string of all text inside and closes the connection after
	 * @param fileName
	 * @return
	 * @throws Throwable
	 */
	public String readFile(String fileName) throws Throwable  {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	/**
	 * Takes in a provided filename and a text and attempts to write the text to a file 
	 * The new file should have the provided filename
	 * @param name
	 * @param text
	 */
	public void writeToFile(String name, String text) {
		try {
			System.out.println("Writing to file...");
			Files.write(Paths.get("./" + name), text.getBytes());
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
	}
	
	/**
	 * Takes in a provided path and lists all the files at that directory
	 * @param path
	 * @return
	 */
	public List<String> listFiles(String path) {
		List<String> tmpLst = new ArrayList<String>();
		File curDir = new File("./");
		File[] filesList = curDir.listFiles();
        for(File f : filesList){
        	//Check if f is a directory or file; might be useful later
            if(f.isDirectory())
            	tmpLst.add(f.getName());
            if(f.isFile()){
            	tmpLst.add(f.getName());
            }
        }
        return tmpLst;
		
	}

}
