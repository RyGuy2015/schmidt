package com.dpslink.schmidt.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.dpslink.schmidt.business.DirectoryHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ProcessDatabase {
	
// Image path for testing	/Users/ryaningram/Development/DPS/Duncan/Duncan_COPELAND/Images/COPELAND/COPE918004702.PNG
	private String originPath = "/Users/ryaningram/Development/DPS/Duncan/Duncan_COPELAND/Images/COPELAND/";
	private String imageDestinationPath = "/Users/ryaningram/Development/DPS/Duncan/Images/";
	private String htmlDestinationPath = "/Users/ryaningram/Development/DPS/Duncan/HTML/";
	private String txtDestinationPath = "/Users/ryaningram/Development/DPS/Duncan/textdocs/";
	private String destinationPath;
	

	@RequestMapping("/getDatabaseInfo")
	public String databaseInfo() throws IOException {
		DirectoryHandler directory = new DirectoryHandler();
		
		directory.setDirectoryPaths(htmlDestinationPath, txtDestinationPath, imageDestinationPath);
		 
		final File fromFolder = new File(originPath);
		String outputToBrowser = "Still a work in progress";
//		ArrayList<String> directoryFiles = directory.retrieveFiles(fromFolder);
		ArrayList<File> directoryFiles = directory.retrieveFiles(fromFolder);
//		System.out.println(directoryFiles.toString());

		//directoryFiles.forEach(System.out::println);
		destinationPath = imageDestinationPath + "COPE918004702.PNG";
		
		DirectoryHandler.copyFilesToDesitnation(originPath, destinationPath);
		
		return "Processing Directory Stuff...";
	}
}