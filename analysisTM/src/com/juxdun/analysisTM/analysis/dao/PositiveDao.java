package com.juxdun.analysisTM.analysis.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 对应keyword_positive表的操作
 * @author Juxdun
 *
 */
@Repository
public class PositiveDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<String> read(){
		String sql = "SELECT keyword FROM keyword_positive";
		RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);
		List<String> list = jdbcTemplate.query(sql, rowMapper);
		
		return list;
	}
	
	public void write(final List<String> list){
		String sql = "INSERT keyword_positive(keyword) values(?)";
		int[] retVals = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				ps.setString(1, list.get(index));
			}
			
			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
		
		for(int i = 0; i < retVals.length; i++){
			if(retVals[i] < 0)
				System.out.println("处理第" + (i + 1) + "条记录失败");
		}
	}
}
