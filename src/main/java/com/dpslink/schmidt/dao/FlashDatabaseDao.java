package com.dpslink.schmidt.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.dpslink.schmidt.models.FlashItem;
import com.dpslink.schmidt.models.ItemUPC;



public class FlashDatabaseDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FlashDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
//	private static final Logger log = LoggerFactory.getLogger(FlashDatabaseDao.class);
//	private ArrayList<String> flashItems = new ArrayList<String>();

    // This Works
	public List<FlashItem> getAllItems() {

	   String sql = "SELECT CODE, DESCRIPTION FROM PRODUCT";
	   RowMapper<FlashItem> rowMapper = new BeanPropertyRowMapper<FlashItem>(FlashItem.class);		
	   return this.jdbcTemplate.query(sql, new Object[] {}, rowMapper);
    }   
	
	// This Works
    public List<String> getItemsAsString() {
    	List<String> list = new ArrayList<>();
        list.add("Table data...");
        jdbcTemplate.query(
                "SELECT CODE, DESCRIPTION FROM PRODUCT", new Object[]{},
                (rs,rowNum) -> new FlashItem(rs.getString("code"), rs.getString("description")))
                .forEach(flashItem -> list.add(flashItem.toString()));
        		for(String myList : list) {
        			System.out.println(myList);
        		}
        return list;
    }
    
    
    // This Works
	public List<ItemUPC> testItemMaster() {

	   String sql = "SELECT ITCONO, ITITEM FROM DPS80F.ICITEMP where ITITEM = 'B0025'";
	   RowMapper<ItemUPC> rowMapper = new BeanPropertyRowMapper<ItemUPC>(ItemUPC.class);		
//   	   RowMapper<FlashItem> rowMapper = new FlashItemRowMapper();
	   return this.jdbcTemplate.query(sql, new Object[] {}, rowMapper);
    }   
    
}
