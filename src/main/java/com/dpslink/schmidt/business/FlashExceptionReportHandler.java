package com.dpslink.schmidt.business;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dpslink.schmidt.models.FlashItem;

public class FlashExceptionReportHandler {
    final private Path path = Paths.get("flash_update_report.txt");
    
    
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
	
	public void writeExceptionReport(List<FlashItem> itemList, int copied, int notCopied, ArrayList<String> appendedItems) throws IOException {
		String heading = "UPC Exception Report \n \n";
		String totalCopied = "Total Items copied: " + copied/3 + "\n\n";
		String itemsCopiedHeading = "Items Copied:";
		String appendedHeading = "\n \nItems With Appended Descriptions: \n";

		Files.write(path, heading.getBytes(), Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		Files.write(path, totalCopied.getBytes(), Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		Files.write(path, itemsCopiedHeading.getBytes(), Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);

		for (FlashItem item : itemList) {
			if (item.getItemCopied()) {
			    try {
			        Files.write(path, Arrays.asList(item.getCode() + " " + item.getDescription()), StandardCharsets.UTF_8,
			            Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
			    } catch (final IOException ioe) {
			    	
			    }
			}
		}
		
		Files.write(path, appendedHeading.getBytes(), Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		
		for (String item : appendedItems) {
		    try {
		        Files.write(path, Arrays.asList(item), StandardCharsets.UTF_8,
		            Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		    } catch (final IOException ioe) {
		    	
		    }
		}

		
	}
	
}
