package com.dpslink.schmidt;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dpslink.schmidt.business.ExtendUpcHandler;
import com.dpslink.schmidt.dao.ExtendUpcDao;
import com.dpslink.schmidt.models.ItemUPC;

public class UpcHandlerTest {

//	@Test
//	public void whenReadLargeFileJava7_thenCorrect()
//	  throws IOException {
//	    Path path = Paths.get("/Users/ryaningram/Development/DPS/Duncan/Duncan_COPELAND/Export.txt");
//	 
//	    BufferedReader reader = Files.newBufferedReader(path,Charset.forName("ISO-8859-1"));
//	    reader.readLine(); // this will read the first line
//	    String line=null;
//	    while ((line = reader.readLine()) != null){ //loop will run from 2nd line
//	    	String[] fieldArray = line.split("\\|");
//	    	if(fieldArray[9].length() > 1)
//	    		System.out.println(fieldArray[5] + " " + fieldArray[9]);
//	    }
//	}
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	@Test
//	public void updateUpcCode() {
//		ItemUPC item = new ItemUPC();
//		item.setItem("AITEM");
//		item.setUpc("2234234");
//		ExtendUpcDao daoObject = new ExtendUpcDao(dataSource);
//		System.out.println(daoObject.updateUpcCode("001", item, "Y"));
//		
//	}
	
//	@Test
//	public void testUpcHandler() throws IOException {
//		ExtendUpcHandler upcObjects = new ExtendUpcHandler("/Users/ryaningram/Development/DPS/Duncan/Test_Data/Export.txt");
//		ArrayList<ItemUPC> itemData = upcObjects.getItemDataFromSchmidt();
//		itemData = upcObjects.getItemDataFromSchmidt();
//		upcObjects.setDataSource(dataSource);
//		upcObjects.updateUpcCodes(itemData);
//
//		itemData.forEach((n) -> System.out.println("Item: " + n.getItem() + " UPC: " + n.getUpc()));
//	}

}
