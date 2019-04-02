package com.dpslink.schmidt.business;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

import com.dpslink.schmidt.models.ItemUPC;


public class UserExceptionReportHandler {
	
    final private Path path = Paths.get("item_exception_report.txt");
    
    
	public void deleteFile() {
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void writeExceptionReport(ArrayList<ItemUPC> itemData) throws IOException {
		for (ItemUPC item : itemData) {
		    try {
		        Files.write(path, Arrays.asList(createExceptionLine(item)), StandardCharsets.UTF_8,
		            Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		    } catch (final IOException ioe) {
	
		    	
		    }
		}
	}
	
    public String createExceptionLine(ItemUPC itemData) {
    	
    	String reasonMessage = "";
    	switch (itemData.getResultCode()) {
    		case "0": 
    			reasonMessage = "Success";
    			break;
    			
    		case "1": 
    			reasonMessage = "Item from Schmidt is too long";
    			break;
    			
    		case "2": 
    			reasonMessage = "UPC Code is too long";
    			break;
    			
    		case "3": 
    			reasonMessage = "Invalid Item Number";
    			break;
    			
    		case "4": 
    			reasonMessage = "More than one Unit of Measure";
    			break;
    			
    		case "5": 
    			reasonMessage = "UPC exists but does not match";
    			break;
    			
    		case "6": 
    			reasonMessage = "UPC matches. No update required.";
    			break;
    			
    		case "7": 
    			reasonMessage = "Invalid or blank UPC";
    			break;
    			
    		case "8": 
    			reasonMessage = "Invalid or blank company number";
    			break;
    			
    		default: 
    			reasonMessage = "Unexpected Error. Contact DPS.";
    	}  	
    	return "Item: " + itemData.getItem() + "   UPC: " + itemData.getUpc() + "   Result: " + reasonMessage;
    }

}
