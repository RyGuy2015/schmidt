package com.dpslink.schmidt;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class UpcHandlerTest {

	@Test
	public void whenReadLargeFileJava7_thenCorrect()
	  throws IOException {
	    Path path = Paths.get("/Users/ryaningram/Development/DPS/Duncan/Duncan_COPELAND/Export.txt");
	 
	    BufferedReader reader = Files.newBufferedReader(path,Charset.forName("ISO-8859-1"));
	    reader.readLine(); // this will read the first line
	    String line=null;
	    while ((line = reader.readLine()) != null){ //loop will run from 2nd line
	    	String[] fieldArray = line.split("\\|");
	    	if(fieldArray[9].length() > 1)
	    		System.out.println(fieldArray[5] + " " + fieldArray[9]);
	    }
	}

}
