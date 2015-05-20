package com.juxdun.analysisTM.analysis.dao.impl;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.juxdun.analysisTM.analysis.dao.CommentDao;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.util.ReadTxt;

@Repository("commentDao")
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void batchInsertComments(final List<Comment> comments) {
		String sql = "INSERT tm_comments(base_uri, content, append, date, person) values(?,?,?,?,?)";
		int[] retVals = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				Comment c = comments.get(index);
				ps.setString(1, c.getBaseURI());
				ps.setString(2, c.getContent());
				ps.setString(3, c.getAppend());
				ps.setString(4, c.getDate());
				ps.setString(5, c.getPerson());
			}
			
			@Override
			public int getBatchSize() {
				return comments.size();
			}
		});
		
		for(int i = 0; i < retVals.length; i++){
			if(retVals[i] < 0)
				System.out.println("处理第" + (i + 1) + "条记录失败");
		}

	}

	@Override
	public void updateProductId() {
		String sql = "UPDATE tm_comments AS c /"
				+ "INNER JOIN tm_products AS p /"
				+ "ON c.base_uri = CONCAT('http:',p.page) /"
				+ "SET c.product_id = p.id;";
		jdbcTemplate.update(sql);
	}

	@Override
	public void signWaterArmy() {
		String sql = "UPDATE tm_comments SET is_waterarmy=1 "
				+ "WHERE id IN ( SELECT a.id FROM( "
				+ "SELECT t1.id FROM tm_comments AS t1,( "
				+ "SELECT date,person FROM tm_comments "
				+ "GROUP BY date, person HAVING COUNT(1)>2 ) AS t2 "
				+ "WHERE t1.person=t2.person AND t1.date=t2.date )AS a)";
		System.out.println(sql);
		jdbcTemplate.update(sql);
	}
	
	@Override
	public void analyseLevel() {
		// 初始化 positive_level 与 negative_level
		jdbcTemplate.update("UPDATE tm_comments SET positive_level = 0, negative_level = 0");
		
		List<String> sqls = new ArrayList<String>();
		// 差评度
		File negativeFile = new File("D:\\Bussiniss analysis\\sentiment.dict.v1.0\\sentiment.dict.v1.0\\tsinghua.negative.gb.txt");
		List<String> nList = ReadTxt.readTxtToList(negativeFile);
		if (null != nList) {
			for (String s : nList) {
				sqls.add("UPDATE tm_comments SET negative_level = negative_level + (CHAR_LENGTH(CONTENT) - CHAR_LENGTH(REPLACE(CONTENT, '" + s + "', '')))/" + s.length() + "");
			}
		}
		
		// 好评度
		File positiveFile = new File("D:\\Bussiniss analysis\\sentiment.dict.v1.0\\sentiment.dict.v1.0\\tsinghua.positive.gb.txt");
		nList = ReadTxt.readTxtToList(positiveFile);
		if (null != nList) {
			for (String s : nList) {
				sqls.add("UPDATE tm_comments /"
						+ "SET positive_level = positive_level + (CHAR_LENGTH(CONTENT) - CHAR_LENGTH(REPLACE(CONTENT, '" + s + "', '')))/" + s.length() + "");
			}
		}
		String[] sql = (String[])sqls.toArray(new String[sqls.size()]);
		if (sql.length > 0) {
			jdbcTemplate.batchUpdate(sql);
		}
		
	}

	@Override
	public List<Comment> getCommentsByProductId(Integer productId) {
		String sql = "SELECT * FROM `tm_comments` "
				+ "WHERE product_id=? AND char_length(content) > 10 "
				+ "AND IS_WATERARMY=0 "
				+ "ORDER BY positive_level - negative_level DESC";
		RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
		List<Comment> list = jdbcTemplate.query(sql, rowMapper, productId);
		return list;
	}

	@Override
	public List<Comment> getCommentsByProductIdForPage(Integer productId,
			Integer page, Integer pageSize) {
		if (page - 1 < 0) {
			return null;
		}
		String sql = "SELECT * FROM tm_comments WHERE clueid=? AND char_length(content) > 10 AND IS_WATERARMY=0 LIMIT ?, ? ORDER BY positive_level - negative_level DESC";
		RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
		List<Comment> list = jdbcTemplate.query(sql, rowMapper, productId, page - 1, pageSize);
		return list;
	}

	@Override
	public Integer getWaCount() {
		String sql = "SELECT COUNT(*) FROM tm_comments WHERE IS_WATERARMY=1";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Integer getAllCount() {
		String sql = "SELECT COUNT(*) FROM tm_comments";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<Comment> getWaComments() {
		String sql = "SELECT * FROM tm_comments WHERE IS_WATERARMY=1 ORDER BY PERSON";
		RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
		List<Comment> list = jdbcTemplate.query(sql, rowMapper);
		return list;
	}

	@Override
	public List<Comment> getWaCommentsForPage(Integer page, Integer pageSize) {
		if (page - 1 < 0) {
			return null;
		}
		String sql = "SELECT * FROM tm_comments WHERE IS_WATERARMY=1 ORDER BY PERSON LIMIT ?, ?";
		RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
		List<Comment> list = jdbcTemplate.query(sql, rowMapper, page - 1, pageSize);
		return null;
	}

	@Override
	public List<Comment> getKeywordComments(Integer clueid, String keyword) {
		String sql = "SELECT * FROM tm_comments WHERE IS_WATERARMY=0 AND  product_id=?  AND CONTENT LIKE ? ORDER BY positive_level - negative_level DESC";
		RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
		List<Comment> list = jdbcTemplate.query(sql, rowMapper, clueid, "%" + keyword + "%");
		return list;
	}

}
