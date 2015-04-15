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

import com.juxdun.analysisTM.analysis.dao.BrandDao;
import com.juxdun.analysisTM.analysis.entities.Brand;

@Repository
public class BrandDaoImpl implements BrandDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void batchInsertBrands(final List<Brand> brands) {
		String sql = "INSERT tm_brands(NAME, PAGE, IMG) values(?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				Brand c = brands.get(index);
				ps.setString(1, c.getName());
				ps.setString(2, c.getPage());
				ps.setString(3, c.getImg());
			}
			
			@Override
			public int getBatchSize() {
				return brands.size();
			}
		});
	}
	
	public List<Brand> getAllBrands() {
		String sql = "SELECT * FROM `tm_brands`";
		RowMapper<Brand> rowMapper = new BeanPropertyRowMapper<>(Brand.class);
		List<Brand> list = jdbcTemplate.query(sql, rowMapper);
		
		return list;
	}
	
	public void updateBrandTable() {
		List<Brand> list = this.getAllBrands();
		// 连接tm_products表的clueId
		String sql1 = "SELECT CLUEID FROM tm_products WHERE FULLPATH LIKE ? GROUP BY CLUEID";
		// 更新tm_products表
		String sql = "UPDATE tm_brands SET PRODUCT_CLUEID=? WHERE ID = ?";
		String clueid = null;
		
		for (Brand p : list) {
			try {				
				clueid = jdbcTemplate.queryForObject(sql1, String.class, "%"+p.getPage()+"%");
			} catch (Exception e) {
//				System.out.println("1");
				continue;
			}
			jdbcTemplate.update(sql, clueid, p.getId());
			
		}
		
	}

}
