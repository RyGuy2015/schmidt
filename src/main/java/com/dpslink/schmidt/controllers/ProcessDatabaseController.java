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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	/********************/
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;
		
		
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
	    
	    
//	    @RequestMapping("updateUPC")
//		public void testUpcHandler() throws IOException {
//			UserExceptionReportHandler reportHandler = new UserExceptionReportHandler();
//			ExtendUpcHandler upcObjects = new ExtendUpcHandler("/Users/ryaningram/Development/DPS/Duncan/Test_Data/Export.txt", "Y");
//			ArrayList<ItemUPC> itemData = upcObjects.getItemDataFromSchmidt();
//			itemData = upcObjects.getItemDataFromSchmidt();
//			upcObjects.setDataSource(dataSource);
//			upcObjects.updateUpcCodes(itemData);
//			reportHandler.deleteFile();
//			reportHandler.writeExceptionReport(itemData);
//			itemData.forEach((n) -> System.out.println("Item: " + n.getItem() + " UPC: " + n.getUpc() + " Code: " + n.getResultCode()));
//		}
	    
	    @RequestMapping(path = "/download", method = RequestMethod.GET)
	    public ResponseEntity<Resource> download(String param) throws IOException {
	    	
	    	File file = new File("item_exception_report.txt");
	    	
	        Path path = Paths.get(file.getAbsolutePath());
	        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=upc_results.txt");
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        headers.add("Pragma", "no-cache");
	        headers.add("Expires", "0");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(file.length())
	                .contentType(MediaType.parseMediaType("application/octet-stream"))
	                .body(resource);
	    }
	    
	    @RequestMapping(path = "/flash-download", method = RequestMethod.GET)
	    public ResponseEntity<Resource> flashDownload(String param) throws IOException {
	    	
	    	File file = new File("flash_update_report.txt");
	    	
	        Path path = Paths.get(file.getAbsolutePath());
	        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=migration_results.txt");
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        headers.add("Pragma", "no-cache");
	        headers.add("Expires", "0");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(file.length())
	                .contentType(MediaType.parseMediaType("application/octet-stream"))
	                .body(resource);
	    }
	    
	    
}