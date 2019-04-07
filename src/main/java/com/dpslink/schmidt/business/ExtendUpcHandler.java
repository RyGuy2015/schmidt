package com.dpslink.schmidt.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.dpslink.schmidt.dao.ExtendUpcDao;
import com.dpslink.schmidt.models.ItemUPC;

public class ExtendUpcHandler {
	DataSource dataSource;
	
	@Autowired	
	public void setDataSource(DataSource dataSource) {
	   this.dataSource = dataSource;
	}

	private final String UPDATE = "Y";
	private final String COMPANY = "001";
	private String documentPath = "/Users/ryaningram/Development/DPS/Duncan/Test_Data/Export.txt";
	
	public ExtendUpcHandler(String documentPath) {
		super();
		this.documentPath = documentPath;
	}
	
	// TODO: parse item# and upc from Schmidt text file	
	public ArrayList<ItemUPC> getItemDataFromSchmidt() throws IOException {
		ArrayList<ItemUPC> itemData = new ArrayList<ItemUPC>();
	    Path path = Paths.get(documentPath);
	 
	    BufferedReader reader = Files.newBufferedReader(path,Charset.forName("ISO-8859-1"));
	    reader.readLine(); // this will read the first line
	    String line=null;
	    while ((line = reader.readLine()) != null){ //loop will run from 2nd line
	    	String[] fieldArray = line.split("\\|");
	    	itemData.add(createItemsFromSchmidtData(fieldArray));
	    }

	    return itemData;
	}
	
	
	// TODO: Create an ArrayList of items from Schmidt
	public ItemUPC createItemsFromSchmidtData(String[] fieldArray) {
		ItemUPC currentItem = new ItemUPC();
		currentItem.setCono(COMPANY);
		currentItem.setItem(fieldArray[5]);
		currentItem.setUpc(fieldArray[9]);
		return currentItem;
	}
	
	// TODO: Iterate over the ArrayList of items from Schmidt and 
	//       attempt to update Extend
	public void updateUpcCodes(ArrayList<ItemUPC> itemData) {
		ExtendUpcDao extendUpcDao = new ExtendUpcDao(dataSource);
		
		itemData.forEach((item) -> extendUpcDao.updateUpcCode(COMPANY, item, UPDATE));
		
	}
	
	
	//TODO: Write Exception Report for User
	
	
	
}
