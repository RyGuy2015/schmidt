package com.dpslink.schmidt.dao;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.dpslink.schmidt.models.ItemUPC;

@Repository
public class ExtendUpcDao {
	
   Logger logger = LoggerFactory.getLogger(ExtendUpcDao.class);
	
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplate;
   
   public ExtendUpcDao(DataSource dataSource) {
	   this.dataSource = dataSource;
   }
   
   @Autowired	
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplate = new JdbcTemplate(dataSource);
   }

   // This method calls the SCHMTUPCS stored procedure on 
   // the IBMi once for each item in the Schmidt Database
    public String updateUpcCode(String company, ItemUPC item, String updte) {
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
    			.withProcedureName("SCHMTUPCS")
    			.declareParameters(
					new SqlParameter("COMPANY", Types.VARCHAR),
					new SqlParameter("ITEM", Types.VARCHAR),
					new SqlParameter("UPC", Types.VARCHAR),
					new SqlParameter("UPDTE", Types.VARCHAR),
					new SqlOutParameter("DISPOSITION", Types.VARCHAR))
    			.withoutProcedureColumnMetaDataAccess();
    	
    	MapSqlParameterSource paraMap = new MapSqlParameterSource()
    			.addValue("COMPANY", company)
    			.addValue("ITEM", item.getItem())
    			.addValue("UPC", item.getUpc())
    			.addValue("UPDTE", updte);
    	
        Map<String, Object> out = jdbcCall.execute(paraMap);
        item.setResultCode(out.get("DISPOSITION").toString().trim());     
        logReturnCodes(out, item.getItem(), item.getUpc());
        
    	return out.get("DISPOSITION").toString().trim();
    }
    
    public void logReturnCodes(Map<String, Object> out, String item, String upc) {
    	
    	String reasonMessage = "";
    	switch (out.get("DISPOSITION").toString().trim()) {
    		case "0": 
    			reasonMessage = "Success";
    			break;
    			
    		case "1": 
    			reasonMessage = "Item from Schmidt is too long";
    			break;
    			
    		case "2": 
    			reasonMessage = "UPC Code is too long";
    			break;
    			
    		case "3": 
    			reasonMessage = "Invalid Item Number";
    			break;
    			
    		case "4": 
    			reasonMessage = "More than one Unit of Measure";
    			break;
    			
    		case "5": 
    			reasonMessage = "UPC exists but does not match";
    			break;
    			
    		case "6": 
    			reasonMessage = "UPC matches. No update required.";
    			break;
    			
    		case "7": 
    			reasonMessage = "Invalid or blank UPC";
    			break;
    			
    		case "8": 
    			reasonMessage = "Invalid or blank company number";
    			break;
    			
    		default: 
    			reasonMessage = "Unexpected Error. Contact DPS.";
    	}
    	
    	logger.warn("Item: " + item + " UPC: " + upc + " Return Message: " + reasonMessage);
    }
       

}
