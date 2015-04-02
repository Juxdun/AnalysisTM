package com.juxdun.analysisTM.analysis.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.juxdun.analysisTM.analysis.dao.ProductDao;
import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Product;

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
				ps.setString(1, c.getClueid());
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
	public List<Product> getProductsByBrand(Brand brand) {
		String sql = "SELECT * FROM `tm_products` WHERE fullpath LIKE '%"+brand.getPage()+"%'";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
		List<Product> list = jdbcTemplate.query(sql, rowMapper);
		
		return list;
	}

}
