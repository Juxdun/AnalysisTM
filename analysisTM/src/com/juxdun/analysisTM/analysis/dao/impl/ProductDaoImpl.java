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

import com.juxdun.analysisTM.analysis.dao.ProductDao;
import com.juxdun.analysisTM.analysis.entities.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void batchInsertProducts(final List<Product> products) {
		String sql = "INSERT `tm_products`(clueid, fullpath, name, price, page, img, shop_name, shop_url) values(?,?,?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				Product c = products.get(index);
//				ps.setString(1, c.getClueid());
				ps.setInt(1, c.getClueid());
				ps.setString(2, c.getFullpath());
				ps.setString(3, c.getName());
				ps.setString(4, c.getPrice());
				ps.setString(5, c.getPage());
				ps.setString(6, c.getImg());
				ps.setString(7, c.getShopName());
				ps.setString(8, c.getShopUrl());
			}
			
			@Override
			public int getBatchSize() {
				return products.size();
			}
		});
		
	}

	@Override
	public List<Product> getProductsByProductClueid(Integer productClueid) {
		String sql = "SELECT * FROM `tm_products` WHERE clueid = ?";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
		List<Product> list = jdbcTemplate.query(sql, rowMapper, productClueid);
		
		return list;
	}

	@Override
	public List<Product> getAllProducts() {
		String sql = "SELECT * FROM `tm_products`";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
		List<Product> list = jdbcTemplate.query(sql, rowMapper);
		
		return list;
	}

	@Override
	public void updateProductTable() {
		List<Product> list = this.getAllProducts();
		// 连接tm_comments表的clueId
		String sql1 = "SELECT CLUEID FROM tm_comments WHERE BASE_URI LIKE ? GROUP BY CLUEID";
		// 该商品的评论数
		String sql2 = "SELECT COUNT(*) FROM tm_comments WHERE CLUEID=?";
		// 水军数
		String sql3 = "SELECT COUNT(*) FROM tm_comments WHERE CLUEID=? AND IS_WATERARMY=1";
		// 更新tm_products表
		String sql = "UPDATE tm_products SET COMMENT_CLUEID=?, COMMENT_COUNT=?, WATER_COUNT=? WHERE ID = ?";
		String clueid = null;
		Integer commentCount = null;
		Integer waterCount = null;
		
		for (Product p : list) {
			try {				
				clueid = jdbcTemplate.queryForObject(sql1, String.class, "%"+p.getPage()+"%");
			} catch (Exception e) {
//				System.out.println("1");
				continue;
			}
			commentCount = jdbcTemplate.queryForObject(sql2, Integer.class, clueid);
			waterCount = jdbcTemplate.queryForObject(sql3, Integer.class, clueid);
			jdbcTemplate.update(sql, clueid, commentCount, waterCount, p.getId());
			
		}
		
	}


}
