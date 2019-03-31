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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class ExtendUpcDao {
	
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
    public String updateUpcCode(String company, String item, 
    		String upc, String update) {
    	String returnCode = "";
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
    			.withProcedureName("SCHMTUPCS")
    			.declareParameters(
					new SqlParameter("COMPANY", Types.VARCHAR),
					new SqlParameter("ITEM", Types.VARCHAR),
					new SqlParameter("UPC", Types.VARCHAR),
					new SqlParameter("UPDATE", Types.VARCHAR),
					new SqlOutParameter("RESULT", Types.VARCHAR))
    			.withoutProcedureColumnMetaDataAccess();
    	
//    	SqlParameterSource in = new MapSqlParameterSource()
//    			.addValue("first_name", firstName)
//    			.addValue("last_name", lastName);
    	MapSqlParameterSource paraMap = new MapSqlParameterSource()
    			.addValue("COMPANY", company)
    			.addValue("ITEM", item)
    			.addValue("UPC", upc)
    			.addValue("UPDATE", update);
    	
        Map<String, Object> out = jdbcCall.execute(paraMap);
        System.out.println(out);
        
        returnCode = out.toString();
    	
    	return returnCode;
    }
    
    public String getNameStoredProc(String firstName, String lastName) {
    	String myNameIs = "";
    	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
    			.withProcedureName("MYNAMEQ2")
    			.declareParameters(
					new SqlParameter("first_name", Types.VARCHAR),
					new SqlParameter("last_name", Types.VARCHAR))
    			.withoutProcedureColumnMetaDataAccess();
    	
//    	SqlParameterSource in = new MapSqlParameterSource()
//    			.addValue("first_name", firstName)
//    			.addValue("last_name", lastName);
    	MapSqlParameterSource paraMap = new MapSqlParameterSource()
    			.addValue("first_name", firstName)
    			.addValue("last_name", lastName);
    	
        Map<String, Object> out = jdbcCall.execute(paraMap);
        System.out.println(out);
    	
    	return myNameIs;
    }
    

}
