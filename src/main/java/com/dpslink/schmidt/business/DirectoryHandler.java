package com.dpslink.schmidt.business;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.dpslink.schmidt.models.ToDirectoryPaths;

public class DirectoryHandler {
	

	private ToDirectoryPaths directoryPaths = new ToDirectoryPaths();
	
	public void setDirectoryPaths(String htmlDirectory, String txtDirectory, String imageDirectory) {
		directoryPaths.setHtmlDirectory(htmlDirectory);
		directoryPaths.setImageDirectory(imageDirectory);
		directoryPaths.setTxtDirectory(txtDirectory);
	}


	// THIS IS NOT USED. COOL JAVA 8 SYNTAX ... WILL REMOVE LATER
	public void getFileListing() throws IOException {
		ArrayList<Path> files = new ArrayList<Path>();
		// use the java 8 Files.walk  (NOT USED BUT STUDY THIS!!!)
		try (Stream<Path> paths = Files.walk(Paths.get("/Users/ryaningram/Development/DPS/Duncan/Duncan_COPELAND/Images"))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(System.out::println);
		} 
	}
	
	// Process all files in the passed folder and create 
	// a File array 
	public ArrayList<File> retrieveFiles(final File folder) {
		ArrayList<File> filesToConvert = new ArrayList<File>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            retrieveFiles(fileEntry);
	        } else {
//	            System.out.println(fileEntry.getName());
//	            filesToConvert.add(fileEntry.getName());
//	        	System.out.println(fileEntry.getAbsolutePath());
	        	filesToConvert.add(fileEntry);
//	        	System.out.println(getFileExtension(fileEntry));
	        }
	    }
	    return filesToConvert;
	}
	
	// The final directory will be based on the file exension
	// This method determines the extension and returns it as a string
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
    
    // Write the current file being processed to the 
    // Appropriate directory
    public static void copyFilesToDesitnation(String origin, String destination) throws IOException {
        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        // overwrite the destination file if it exists, and copy
        // the file attributes, including the rwx permissions
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        }; 
        Files.copy(FROM, TO, options);
    }
    
    // determine which folder to write to based on 
    // the file extension being processed
    public String getDestinationFolder(File file) {
    	String destinationFolder;
    	switch (getFileExtension(file).toLowerCase()) {
    		case "txt": 
    			destinationFolder = directoryPaths.getTxtDirectory();
    			break;
    		
    		case "html":
    			destinationFolder = directoryPaths.getHtmlDirectory();
    			break;
    			
    		case "png" :
    			destinationFolder = directoryPaths.getImageDirectory();
    			break;
    			
    		default: 
    			destinationFolder = "";
    	}
    	return destinationFolder;
    }
    
    public void copyFiles(ArrayList<File> files) {
    	for (File file : files) {
    		// TODO: Compare File with Database
    		
    	}
    	
    }
    
    public static void renameFileToMatchItem() {
    	
    }
    
	public ToDirectoryPaths getDirectoryPaths() {
		return directoryPaths;
	}
}
