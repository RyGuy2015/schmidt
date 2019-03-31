package com.dpslink.schmidt;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.dpslink.schmidt.business.FileManipulationHandler;
import com.dpslink.schmidt.dao.ExtendUpcDao;
import com.dpslink.schmidt.dao.FlashDatabaseDao;
import com.dpslink.schmidt.dao.TestJdbcDao;
import com.dpslink.schmidt.models.DirectoryPaths;
import com.dpslink.schmidt.models.FlashItem;
import com.dpslink.schmidt.models.ItemUPC;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchmidtApplicationTests {
	private String originPath = "/Users/ryaningram/Development/DPS/Duncan/Duncan_COPELAND/Images/COPELAND/";
	private String imageDestinationPath = "/Users/ryaningram/Development/DPS/Duncan/Test/";
	DirectoryPaths testDirectoryPaths = new DirectoryPaths();
	
//	@Test
//	public void testJdbc() throws Exception {
//		TestJdbcDao myTestConnection = new TestJdbcDao();
//		Map<String, String> myTest = myTestConnection.testConnection();
//		
//	}
//	
//	@Test
//	public void contextLoads() throws IOException {
//		testDirectoryPaths.setFlashDirectory(imageDestinationPath);
//		testDirectoryPaths.setFromDirectory(originPath);
//		FileManipulationHandler myFileManipulatorTest = new FileManipulationHandler(testDirectoryPaths);
//		
////		final File fromFolder = new File(originPath);
////		ArrayList<File> directoryFiles = myFileManipulatorTest.retrieveFiles(fromFolder);
//		ArrayList<File> directoryFiles = myFileManipulatorTest.retrieveFiles();
//
//		//directoryFiles.forEach(System.out::println);
//		
////		for (File file : directoryFiles) {
////			System.out.println(myFileManipulatorTest.getImageSize(file));
////		}
//		
////		for (File file : directoryFiles) {
////			System.out.println(file.getName() + " and without " + myFileManipulatorTest.renameImageWithoutSize(file).getName());
////		}
//		
////		for (File file : directoryFiles) {
////			if(FileManipulationHandler.getFileExtension(file).equals("htm")) {
////				System.out.println(file.getName() + " and with an l " + myFileManipulatorTest.fixHtmFile(file));
////			} else {
////				System.out.println(file.getName() + " ... " + FileManipulationHandler.getFileExtension(file) );
////			}
////		}
//		
////		for (File file : directoryFiles) {
////			System.out.println(myFileManipulatorTest.getDestinationFolder(file));
////		}
//		
//		// myFileManipulatorTest.copyFiles(directoryFiles);
//	};
	

	

	
//	@Test
//	public void testItemData() {
//		FlashDatabaseDao flashItemDao = new FlashDatabaseDao();
//		 flashItemDao.getItemsAsString();
//	}
//	
//	@Test
//	public void methodTest() {
//		FlashDatabaseDao flashItemDao = new FlashDatabaseDao();
//		System.out.println(flashItemDao.willThisWork());
//	}
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//	@Test
//	  public void example() {
//	  List<String> list = new ArrayList<>();
//	  list.add("Table data...");
//	  jdbcTemplate.query(
//	          "SELECT CODE, DESCRIPTION FROM PRODUCT", new Object[]{},
//	          (rs,rowNum) -> new FlashItem(rs.getString("code"), rs.getString("description")))
//	          .forEach(thing -> list.add(thing.toString()));
//	  System.out.println(list);
//	}
//	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	@Test
//	public void getUpcItems() {
//		ExtendUpcDao daoObject = new ExtendUpcDao(dataSource);
//		System.out.println(daoObject.getNameStoredProc("Ry", "Ing"));
//		
//	}
	
	@Test
	public void updateUpcCode() {
		ExtendUpcDao daoObject = new ExtendUpcDao(dataSource);
		System.out.println(daoObject.updateUpcCode("001", "B0025", "ABC123454321", "Y"));
		
	}
	
//	@Test
//	public void testItemMaster() {
//		FlashDatabaseDao testItem = new FlashDatabaseDao(jdbcTemplate);
//		List<ItemUPC> itemHit = testItem.testItemMaster();
//		System.out.println(itemHit.toString());
//		
//	}
	

	
	
}
