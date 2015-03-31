package com.juxdun.analysisTM.analysis.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.juxdun.analysisTM.analysis.Comment;

public class CommentDaoImpl implements CommentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Comment get(int id) {
		String sql = "SELECT * FROM tm_comments WHERE id = ?";
		RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
		Comment comment = jdbcTemplate.queryForObject(sql, rowMapper, id);
		
		return comment;
	}

	@Override
	public void saveComments(List<Object[]> batchArgs) {
		String sql = "INSERT tm_comments(cluid, content, date, person) value(?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	@Override
	public void deleteWaterArmy() {
		// TODO Auto-generated method stub
		
	}

}
