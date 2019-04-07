package com.dpslink.schmidt.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.dpslink.schmidt.business.DirectoryHandler;
import com.dpslink.schmidt.business.ExtendUpcHandler;
import com.dpslink.schmidt.business.UserExceptionReportHandler;
import com.dpslink.schmidt.dao.ExtendUpcDao;
import com.dpslink.schmidt.dao.FlashDatabaseDao;
import com.dpslink.schmidt.dao.TestJdbcDao;
import com.dpslink.schmidt.models.DirectoryPaths;
import com.dpslink.schmidt.models.FlashItem;
import com.dpslink.schmidt.models.ItemUPC;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
  
@RestController
public class ProcessDatabaseController {
	
//	private String originPath = "/Users/ryaningram/Development/DPS/Duncan/Duncan_COPELAND/Images/COPELAND/";
//	private String imageDestinationPath = "/Users/ryaningram/Development/DPS/Duncan/Images/";
//	private String htmlDestinationPath = "/Users/ryaningram/Development/DPS/Duncan/HTML/";
//	private String txtDestinationPath = "/Users/ryaningram/Development/DPS/Duncan/textdocs/";
//	private String flashDirectory = "/Users/ryaningram/Development/DPS/Duncan/";
//	private String destinationPath;
//	
//	@RequestMapping("/getDatabaseInfo")
//	public String databaseInfo() throws IOException {
//		DirectoryHandler directory = new DirectoryHandler();
//		
//		directory.setDirectoryPaths(flashDirectory);
//		 
//		final File fromFolder = new File(originPath);
//		String outputToBrowser = "Still a work in progress";
//		ArrayList<File> directoryFiles = directory.retrieveFiles(fromFolder);
////		System.out.println(directoryFiles.toString());
//
//		//directoryFiles.forEach(System.out::println);
//		//destinationPath = imageDestinationPath + "COPE918004702.PNG";
//		
//		//DirectoryHandler.copyFilesToDesitnation(originPath, destinationPath);
//		
//		directory.copyFiles(directoryFiles);
//		
//		return "Processing Directory Stuff...";
//	}
//	
//	@RequestMapping("/updateUPC")
//	public String updateUPC() throws IOException {
//		
//		return "Processing UPC Codes";
//	}
	
//	@RequestMapping("/runSQL")
//	public String runSQL() throws Exception {
//		TestJdbcDao myTestJdbc = new TestJdbcDao();
//		Map<String, String> result = myTestJdbc.testConnection();
//
//		return "Placeholder String";
//	}
	
	/********************/
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;
		
		
//	    private final JdbcTemplate jdbcTemplate;
//
//	    @Autowired
//	    public ProcessDatabaseController(JdbcTemplate jdbcTemplate) {
//	        this.jdbcTemplate=jdbcTemplate;
//	    }

	    @RequestMapping("/testConnection")
	    @ResponseBody
	    public boolean canConnectToDB() {
	        boolean result;
	        try {
	            jdbcTemplate.getDataSource().getConnection();
	            result = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = false;
	        }
	        return result;
	    }
	    
	    /****************************************************/
	    
	    @RequestMapping("test")
	    public @ResponseBody ResponseEntity<String> example() {
	        List<String> list = new ArrayList<>();
	        list.add("Table data...");
	        jdbcTemplate.query(
	                "SELECT CODE, DESCRIPTION FROM PRODUCT", new Object[]{},
	                (rs,rowNum) -> new FlashItem(rs.getString("code"), rs.getString("description")))
	                .forEach(thing -> list.add(thing.toString()));
	        return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
	    }
	    
	    @RequestMapping("fromDao")
	    public void fromDao() {
	    	FlashDatabaseDao myDataAccess = new FlashDatabaseDao(jdbcTemplate);
	    	myDataAccess.getItemsAsString();
	    }
	    
//	    @RequestMapping("storedProc")
//	    public void storedProc() {
//	    	ExtendUpcDao daoObject = new ExtendUpcDao(dataSource);
//	    	System.out.println(daoObject.updateUpcCode("001", ItemUPC item "N"));
//	    }
	    
	    @RequestMapping("updateUPC")
		public void testUpcHandler() throws IOException {
			UserExceptionReportHandler reportHandler = new UserExceptionReportHandler();
			ExtendUpcHandler upcObjects = new ExtendUpcHandler("/Users/ryaningram/Development/DPS/Duncan/Test_Data/Export.txt");
			ArrayList<ItemUPC> itemData = upcObjects.getItemDataFromSchmidt();
			itemData = upcObjects.getItemDataFromSchmidt();
			upcObjects.setDataSource(dataSource);
			upcObjects.updateUpcCodes(itemData);
			reportHandler.deleteFile();
			reportHandler.writeExceptionReport(itemData);
			itemData.forEach((n) -> System.out.println("Item: " + n.getItem() + " UPC: " + n.getUpc() + " Code: " + n.getResultCode()));
		}
	    
	    
}