package com.dpslink.schmidt.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dpslink.schmidt.models.FlashItem;


@Service
public class TestJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
	
	  public void example() {
		  List<String> list = new ArrayList<>();
		  list.add("Table data...");
		  jdbcTemplate.query(
		          "SELECT CODE, DESCRIPTION FROM PRODUCT", new Object[]{},
		          (rs,rowNum) -> new FlashItem(rs.getString("code"), rs.getString("description")))
		          .forEach(thing -> list.add(thing.toString()));
		  System.out.println(list);
	  }
	
}
