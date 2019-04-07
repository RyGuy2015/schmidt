package com.dpslink.schmidt.business;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	public void writeExceptionReport(ArrayList<FlashItem> itemData) throws IOException {
		for (FlashItem item : itemData) {
		    try {
		        Files.write(path, Arrays.asList(item.toString()), StandardCharsets.UTF_8,
		            Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
		    } catch (final IOException ioe) {
		    	
		    }
		}
	}
	
}
