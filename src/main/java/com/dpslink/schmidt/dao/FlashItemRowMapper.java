package com.dpslink.schmidt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dpslink.schmidt.models.FlashItem;

public class FlashItemRowMapper implements RowMapper<FlashItem>{

	@Override
	public FlashItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("Inside RowMapper");
		FlashItem flashItem = new FlashItem();
		flashItem.setCode(rs.getString("CODE"));
		flashItem.setDescription(rs.getString("DESCRIPTION"));
		flashItem.setCategoryCode(rs.getString("CATEGORY_CODE"));
		return flashItem;
	}

}
