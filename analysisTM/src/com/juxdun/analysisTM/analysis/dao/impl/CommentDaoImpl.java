package com.juxdun.analysisTM.analysis.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.juxdun.analysisTM.analysis.dao.CommentDao;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.entities.Product;

@Repository("commentDao")
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
	public void batchInsertComments(final List<Comment> comments) {
		String sql = "INSERT tm_comments(clueid, base_uri, content, date, person) values(?,?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				Comment c = comments.get(index);
				ps.setString(1, c.getClueid());
				ps.setString(2, c.getBaseURI());
				ps.setString(3, c.getContent());
				ps.setString(4, c.getDate());
				ps.setString(5, c.getPerson());
			}
			
			@Override
			public int getBatchSize() {
				return comments.size();
			}
		});
	}
	/*@Override
	public void batchInsertComments(List<Object[]> batchArgs) {
		String sql = "INSERT tm_comments(clueid, content, date, person) values(?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}*/

	@Override
	public void deleteWaterArmy() {
//		String sql = "DELETE FROM `tm_comments` USING `tm_comments`,(SELECT * FROM `tm_comments` GROUP BY `date`,`person` HAVING COUNT(1) > 1 ) AS `t2` WHERE `tm_comments`.`date` = `t2`.`date` AND `tm_comments`.`person` = `t2`.`person`";
		String sql = "UPDATE FROM `tm_comments` USING `tm_comments`,(SELECT * FROM `tm_comments` GROUP BY `date`,`person` HAVING COUNT(1) > 1 ) AS `t2` WHERE `tm_comments`.`date` = `t2`.`date` AND `tm_comments`.`person` = `t2`.`person`";
		jdbcTemplate.update(sql);
	}
	
	@Override
	public void deleteLess10Char(){
		String sql = "DELETE FROM `tm_comments` WHERE char_length(content) <= 10 ";
		jdbcTemplate.update(sql);
	}

	@Override
	public List<Comment> getCommentsByProduct(Product product) {
		String sql = "SELECT * FROM `tm_comments` WHERE BASE_URI LIKE '%"+product.getPage()+"%'";
		RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
		List<Comment> list = jdbcTemplate.query(sql, rowMapper);
		return list;
	}

}
