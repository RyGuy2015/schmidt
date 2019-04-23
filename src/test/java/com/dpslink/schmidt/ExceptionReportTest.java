package com.dpslink.schmidt;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.dpslink.schmidt.business.UserExceptionReportHandler;
import com.dpslink.schmidt.models.ItemUPC;

public class ExceptionReportTest {
	
//	@Test
//	public void createTextFile() 
//	  throws IOException {
//	    try {
//	        final Path path = Paths.get("myTestFile.txt");
//	        Files.write(path, Arrays.asList("New line to append 2"), StandardCharsets.UTF_8,
//	            Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
//	    } catch (final IOException ioe) {
//
//	    }
//	}
	
//	@Test
//	public void testReport() throws IOException {
//		ArrayList<ItemUPC> itemData = new ArrayList<ItemUPC>();
//		ItemUPC item = new ItemUPC();
//		ItemUPC item2 = new ItemUPC();
//		
//		item.setItem("AITEM");
//		item.setUpc("ABC123");
//		item.setResultCode("2");
//		
//		item2.setItem("BITEM");
//		item2.setUpc("UPCQ2223323");
//		item2.setResultCode("8");
//		
//		itemData.add(item);
//		itemData.add(item2);
//		
//		UserExceptionReportHandler myReport = new UserExceptionReportHandler();
//		myReport.writeExceptionReport(itemData);
//	}
	
	
//	@Test
//	public void testDelete() {
//		UserExceptionReportHandler myReport = new UserExceptionReportHandler();
//		myReport.deleteFile();
//	}
	 
}
