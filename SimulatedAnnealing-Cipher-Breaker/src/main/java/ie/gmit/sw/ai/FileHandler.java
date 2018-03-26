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
