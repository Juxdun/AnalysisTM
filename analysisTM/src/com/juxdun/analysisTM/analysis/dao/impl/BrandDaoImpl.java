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
import org.springframework.transaction.annotation.Transactional;

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
		String sql = "INSERT tm_brands(name, page, img) values(?,?,?)";
		int[] retVals = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
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
		
		for(int i = 0; i < retVals.length; i++){
			if(retVals[i] < 0)
				System.out.println("处理第" + (i + 1) + "条记录失败");
		}

	}
	
	@Override
	public void updateP_Count() {
		String sql = "UPDATE tm_brands AS b INNER JOIN " +
						"(SELECT COUNT(brand_id) AS a,brand_id "+
						"FROM tm_products " +
						"GROUP BY brand_id " +
						"HAVING brand_id IS NOT NULL) AS c " +
						"ON b.id = c.brand_id " +
						"SET p_count = a";
		jdbcTemplate.update(sql);
		
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
