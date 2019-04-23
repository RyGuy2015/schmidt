package com.dpslink.schmidt.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dpslink.schmidt.dao.ExtendUpcDao;
import com.dpslink.schmidt.models.DirectoryPaths;
import com.dpslink.schmidt.models.FlashItem;
import com.dpslink.schmidt.models.ItemUPC;

import org.apache.commons.io.FilenameUtils;

public class FileManipulationHandler {
	
	private DirectoryPaths directoryPaths = new DirectoryPaths();
	private File fromPath;
	private int unmatchedCounter = 0;
	private int matchedCounter = 0;
	private boolean updateProduction;
	private static ArrayList<String> appendedHtmlItems = new ArrayList<String>();

	// Receive the Schmidt and Flash directories
	public FileManipulationHandler(DirectoryPaths directoryPaths) {
		super();
		this.directoryPaths = directoryPaths;
		this.setFromPath(new File(directoryPaths.getFromDirectory()));
		this.updateProduction = directoryPaths.getUpdateFlag();
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
    public String getDestinationFolder(File file)  {
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
    
    //TODO - check for mfgItemData
    // removes the size and extension for comparison purposes
    public String createComparisonString(File file) {
    	String fileName = FilenameUtils.removeExtension(file.getName());
        if(fileName.lastIndexOf("_") != -1 && fileName.lastIndexOf("_") != 0) 
        return fileName.substring(0, fileName.lastIndexOf("_"));
        else return fileName;

    }
	
    // Schmidt data files have htm extension. Flash needs html.
    // This fixes the problem.
    public File fixHtmFile2(File file) {
    	return new File(file.getPath() + "l");
    }
    
    public File fixHtmFile3(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        Stream<String> lines = null;
		try {
			lines = Files.lines(path, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List <String> replaced = lines.map(line -> line.replaceAll("background-color:white", "background-color:rgba(0,0,0,.5)")).collect(Collectors.toList());
        try {
			Files.write(path, replaced);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        lines.close();
        System.out.println("Find and Replace done!!!");

    	return new File(file.getPath() + "l");
    }
    
    public File fixHtmFile(File file) {
		File fileToBeModified = file;
		String oldContent = "";
		BufferedReader reader = null;
		FileWriter writer = null;
		
		String oldString = "background-color:white";
		String newString = "background-color:rgba(0,0,0,0)";
		
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			// Reading all the lines of input text file into oldContent
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			// Replacing oldString with newString in the oldContent
			String newContent = oldContent.replaceAll(oldString, newString);
			// Rewriting the input text file with newContent
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new File(file.getPath() + "l");
	}
    
    // If Duncan's item number is in mfg_item format then replace the image name in UPC format with 
    // mfg_item number
    public File replaceItemNumber(File file, ArrayList<ItemUPC> mfgItemData) {
    	
    	File replacementFile = file;
    	String imageSize = getImageSize(file);
    	if (imageSize.length() > 0) {
    		imageSize = "_" + imageSize;
    	}
    	for (ItemUPC mfgItem : mfgItemData) {
    		if(mfgItem.getItem().equals(createComparisonString(file))) {
//    			System.out.println(mfgItem.getItem() + " is eqaul to " + createComparisonString(file));
//    			System.out.println("File Extension of: " + file + " is " + getFileExtension(file));
//    			System.out.println("File path: " + file.getParent());
    			replacementFile = new File(file.getParent() + "/" + mfgItem.getMfg_item() 
    			+ imageSize + "." + getFileExtension(file));
//    			System.out.println("New file will be: " + replacementFile);
    			file.renameTo(replacementFile);

    		}
    	}
    	
    	return replacementFile;
    }
    
    
	// Accept an ArrayList of Files to copy to the Flash database
    public void copyFiles(ArrayList<File> files, List<FlashItem> flashItems, ArrayList<ItemUPC> mfgItemData) throws IOException {
    	

    	for (File file : files) {
    		if (this.directoryPaths.isUseMfgItem()) {
    			file = this.replaceItemNumber(file, mfgItemData);
    		}
    		System.out.println(file);
        	for (FlashItem item : flashItems) {
        		if (item.getCode()
        				.trim()
        				.equals(createComparisonString(file))) 
        		{
        			System.out.println(item.getCode() + " matches " + createComparisonString(file));
        			if (updateProduction) { 
        				this.copyFilesToDesitnation(file.getAbsolutePath(), getDestinationFolder(file));
        			}
        			item.setItemCopied(true);
        			this.matchedCounter ++;
        		} else {
        			this.unmatchedCounter ++;
        		}
//        		if (item.getCode().equals("B0016")) {
//        			System.out.println("Item: " + item.getCode() + "    file: " + createComparisonString(file));
//        		}
        	}
    		// TODO: Compare File with Database
//    		this.copyFilesToDesitnation(file.getAbsolutePath(), getDestinationFolder(file));
//    		System.out.print(file.getName());
    	}
    	System.out.println("Total Matched compares: " + this.matchedCounter);
    	System.out.println("Total Unmatched compares: " + this.unmatchedCounter);
    	
    }
    
    // Write the current file being processed to the 
    // Appropriate directory
    public static void copyFilesToDesitnation(String origin, String destination) throws IOException {
        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        File testFile = new File(destination);
        if (testFile.exists() && getFileExtension(testFile).equals("html")) {
        	// if the file exists and if it has an html extension then 
        	// append the data of the FROM path to the data of the TO path.
        	System.out.println("File Exists!!!");
        	String tempFromData = new String(Files.readAllBytes(FROM));
        	Files.write(TO, tempFromData.getBytes(), StandardOpenOption.APPEND);
        	appendedHtmlItems.add(testFile.getName());
        } else {
            // overwrite the destination file if it exists, and copy
            // the file attributes, including the rwx permissions
            CopyOption[] options = new CopyOption[]{
              StandardCopyOption.REPLACE_EXISTING,
              StandardCopyOption.COPY_ATTRIBUTES
            }; 
            Files.copy(FROM, TO, options);
        }
        System.out.println("To: " + TO);
    }

    //This is the path as a File rather than as a String
	public File getFromPath() {
		return fromPath;
	}

	public void setFromPath(File fromPath) {
		this.fromPath = fromPath;
	}
	
	public int getUnmatchedCounter() {
		return unmatchedCounter;
	}

	public void setUnmatchedCounter(int unmatchedCounter) {
		this.unmatchedCounter = unmatchedCounter;
	}

	public int getMatchedCounter() {
		return matchedCounter;
	}

	public void setMatchedCounter(int matchedCounter) {
		this.matchedCounter = matchedCounter;
	}
	
	public ArrayList<String> getAppendedHtmlItems() {
		return appendedHtmlItems;
	}

	
	// TODO: Log the items written
	
	// TODO: Log any exceptions

}
