package com.dpslink.schmidt.business;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import com.dpslink.schmidt.models.DirectoryPaths;
import org.apache.commons.io.FilenameUtils;

public class FileManipulationHandler {
	
	private DirectoryPaths directoryPaths = new DirectoryPaths();
	private File fromPath;

	// Receive the Schmidt and Flash directories
	public FileManipulationHandler(DirectoryPaths directoryPaths) {
		super();
		this.directoryPaths = directoryPaths;
		this.setFromPath(new File(directoryPaths.getFromDirectory()));
	}
	
	// Retrieve files from the Schmidt directory and create 
	// a File array to process (Empty parameter list overload)
	public ArrayList<File> retrieveFiles() {
		File folder = fromPath;
		ArrayList<File> filesToConvert = new ArrayList<File>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            retrieveFiles(fileEntry);
	        } else {
	        	String fileExtension = getFileExtension(fileEntry);
	        	String imageSize = getImageSize(fileEntry);
	        	if(fileExtension.equals("htm"))
	        		filesToConvert.add(fileEntry);
	        	if(fileExtension.equals("PNG") && (imageSize.equals("lg") || imageSize.equals("md")))
	        		filesToConvert.add(fileEntry);
	        }
	    }
	    return filesToConvert;
	}
		
	// Retrieve files from the Schmidt directory and create 
	// a File array to process
	public ArrayList<File> retrieveFiles(final File folder) {
		ArrayList<File> filesToConvert = new ArrayList<File>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            retrieveFiles(fileEntry);
	        } else {
	        	String fileExtension = getFileExtension(fileEntry);
	        	if(fileExtension.equals("htm")||fileExtension.equals("PNG"))
	        		filesToConvert.add(fileEntry);
	        }
	    }
	    return filesToConvert;
	}
	
	// TODO: Test if file matches item in Flash database
	
	// TODO: Rename Files if necessary
	
	// The final directory will be based on the file extension and image size
	// This method determines the extension and returns it as a string
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	// Determine the size of the image file and return _lg, _sm or _md
    public String getImageSize(File file) {
    	String fileName = FilenameUtils.removeExtension(file.getName());
        if(fileName.lastIndexOf("_") != -1 && fileName.lastIndexOf("_") != 0)
        return fileName.substring(fileName.lastIndexOf("_")+1);
        else return "";
    }
	
    // determine which folder to write to based on 
    // the file extension being processed and the size of the image
    public String getDestinationFolder(File file) {
    	String destinationFolder;
    	switch (getFileExtension(file).toLowerCase()) {
    		case "htm":
    			destinationFolder = directoryPaths.getHtmlDirectory();
    			break;
    			
    		case "png":
    			String size = getImageSize(file);
    			if (size.equals("lg")) {
    				destinationFolder = directoryPaths.getLargeImageDirectory();
    			} else if (size.equals("md")) {
    				destinationFolder = directoryPaths.getSmallImageDirectory();
    			} else {
    				destinationFolder = "";
    			}
    			break;
    			
    		default: 
    			destinationFolder = "";
    	}
    	if (getFileExtension(file).equals("htm"))
    		return destinationFolder + fixHtmFile(file).getName();
    		else return destinationFolder + renameImageWithoutSize(file).getName();
    }
	
	// removes the size _lg, _md etc from the file name
    // and creates a new file object
    public File renameImageWithoutSize(File file) {
    	String fileName = FilenameUtils.removeExtension(file.getName());
        if(fileName.lastIndexOf("_") != -1 && fileName.lastIndexOf("_") != 0) 
        return new File(fileName.substring(0, fileName.lastIndexOf("_")) + "." + getFileExtension(file));
        else return file;
    }
	
    // Schmidt data files have htm extension. Flash needs html.
    // This fixes the problem.
    public File fixHtmFile(File file) {
    	return new File(file.getPath() + "l");
    }
    
    
	// Accept an ArrayList of Files to copy to the Flash database
    public void copyFiles(ArrayList<File> files) throws IOException {
    	for (File file : files) {
    		// TODO: Compare File with Database
    		this.copyFilesToDesitnation(file.getAbsolutePath(), getDestinationFolder(file));
    		System.out.print(file.getName());
    	}
    	
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

    //This is the path as a File rather than as a String
	public File getFromPath() {
		return fromPath;
	}

	public void setFromPath(File fromPath) {
		this.fromPath = fromPath;
	}
	
	// TODO: Log the items written
	
	// TODO: Log any exceptions

}
