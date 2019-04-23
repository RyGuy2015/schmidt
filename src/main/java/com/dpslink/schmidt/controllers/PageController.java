
package com.dpslink.schmidt.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dpslink.schmidt.business.ExtendUpcHandler;
import com.dpslink.schmidt.business.FileManipulationHandler;
import com.dpslink.schmidt.business.FlashExceptionReportHandler;
import com.dpslink.schmidt.business.UserExceptionReportHandler;
import com.dpslink.schmidt.dao.ExtendUpcDao;
import com.dpslink.schmidt.dao.FlashDatabaseDao;
import com.dpslink.schmidt.models.DirectoryPaths;
import com.dpslink.schmidt.models.FlashItem;
import com.dpslink.schmidt.models.ItemUPC;

@Controller
public class PageController {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping(value="/") 
	public String landingPage(Model model) {
		model.addAttribute("directoryPaths", new DirectoryPaths());
		return "index.html";
	}
	
    @PostMapping("/")
    public String submitDirectoryPaths(@ModelAttribute DirectoryPaths directoryPaths) {
		try {
	    	FlashExceptionReportHandler flashExceptionReport = new FlashExceptionReportHandler();
	    	FlashDatabaseDao flashItems = new FlashDatabaseDao(jdbcTemplate);
	    	FileManipulationHandler schmidtFileManipulationHandler = new FileManipulationHandler(directoryPaths); 
	    	ExtendUpcHandler upcObjects = new ExtendUpcHandler(directoryPaths);
	    	ArrayList<ItemUPC> mfgItemData = upcObjects.getItemDataFromSchmidt();
    		ArrayList<File> directoryFiles = schmidtFileManipulationHandler.retrieveFiles();
    		List<FlashItem>  itemList = flashItems.getAllItems();
			schmidtFileManipulationHandler.copyFiles(directoryFiles, itemList, mfgItemData);
			flashExceptionReport.deleteFile();
			flashExceptionReport.writeExceptionReport(itemList, schmidtFileManipulationHandler.getMatchedCounter(), schmidtFileManipulationHandler.getUnmatchedCounter(),  schmidtFileManipulationHandler.getAppendedHtmlItems());
			return "complete";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "flashDirectoryError";
		}
    }
    
	@GetMapping(value="/upc") 
	public String upcPage(Model model) {
		model.addAttribute("directoryPaths", new DirectoryPaths());
		return "upc";
	}
	
	
    @PostMapping("/upc")
    public String updateUpcCodes(@ModelAttribute DirectoryPaths directoryPaths)  {
    	UserExceptionReportHandler reportHandler = new UserExceptionReportHandler();
		ExtendUpcHandler upcObjects = new ExtendUpcHandler(directoryPaths);
		ArrayList<ItemUPC> itemData;
		try {
			itemData = upcObjects.getItemDataFromSchmidt();
			upcObjects.setDataSource(dataSource);
			upcObjects.updateUpcCodes(itemData);
			reportHandler.deleteFile();
			reportHandler.writeExceptionReport(itemData);
	//		itemData.forEach((n) -> System.out.println("Item: " + n.getItem() + " UPC: " + n.getUpc() + " Code: " + n.getResultCode()));
	        return "upcComplete";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "upcDirectoryError";
		}
    }
    
    
}
