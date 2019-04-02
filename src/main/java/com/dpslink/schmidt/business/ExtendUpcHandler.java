package com.dpslink.schmidt.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.dpslink.schmidt.models.ItemUPC;

public class ExtendUpcHandler {

	private final String COMPANY = "001";
	private String documentPath = "/Users/ryaningram/Development/DPS/Duncan/Duncan_COPELAND/Export.txt";
	ArrayList<ItemUPC> itemData = new ArrayList<ItemUPC>();
	
	
	public ExtendUpcHandler(String documentPath) {
		super();
		this.documentPath = documentPath;
	}
	
	// TODO: parse item# and upc from Schmidt text file
	
	public void getItemDataFromSchmidt()
			  throws IOException {
			    Path path = Paths.get(documentPath);
			 
			    BufferedReader reader = Files.newBufferedReader(path,Charset.forName("ISO-8859-1"));
			    reader.readLine(); // this will read the first line
			    String line=null;
			    while ((line = reader.readLine()) != null){ //loop will run from 2nd line
			    	String[] fieldArray = line.split("\\|");
			    	if(fieldArray[9].length() > 1)
			    		System.out.println(fieldArray[5] + " " + fieldArray[9]);
			    }
			}
	
	
	// TODO: Create an ArrayList of items from Schmidt
	public void createItemsFromSchmidtData() {
		
		
	}
	
	// TODO: Iterate over the ArrayList of items from Schmidt and 
	//       attempt to update Extend
	
	//TODO: Write Exception Report for User
	
	
	
}
