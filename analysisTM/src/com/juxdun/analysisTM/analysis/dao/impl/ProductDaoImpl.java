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
import org.springframework.test.context.jdbc.Sql;

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
		String sql = "INSERT tm_products(name, resume, price, page, img) values(?,?,?,?,?)";
		int[] retVals = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				Product c = products.get(index);
				ps.setString(1, c.getName());
				ps.setString(2, c.getResume());
				ps.setString(3, c.getPrice());
				ps.setString(4, c.getPage());
				ps.setString(5, c.getImg());
			}
			
			@Override
			public int getBatchSize() {
				return products.size();
			}
		});
		
		for(int i = 0; i < retVals.length; i++){
			if(retVals[i] < 0)
				System.out.println("处理第" + (i + 1) + "条记录失败");
		}
	}

	@Override
	public Boolean updateBrandId() {
		String sql = "UPDATE tm_products AS p " +
						"RIGHT JOIN tm_brands AS b " +
						"ON p.`name` LIKE CONCAT('%',b.name,'%') "+
						"SET p.brand_id = b.id";
		int i = jdbcTemplate.update(sql);
		return i<0 ? false : true;
	}

	@Override
	public Boolean countComment() {
		String sql = "UPDATE tm_products AS p "
				+ "INNER JOIN "
				+ "(SELECT product_id, COUNT(1) AS cou "
				+ "FROM tm_comments WHERE product_id IS NOT NULL " 
				+ "GROUP BY product_id) AS c "
				+ "ON p.id = c.product_id "
				+ "SET p.comment_count=c.cou";
		int i = jdbcTemplate.update(sql);
		return i<0 ? false : true;
	}

	@Override
	public Boolean countWater() {
		String sql = "UPDATE tm_products AS p "
				+ "INNER JOIN (SELECT product_id, COUNT(1) AS cou FROM tm_comments "
				+ "WHERE product_id IS NOT NULL AND is_waterarmy=1 "
				+ "GROUP BY product_id) AS c "
				+ "ON p.id = c.product_id "
				+ "SET p.water_count=c.cou";
		int i = jdbcTemplate.update(sql);
		return i<0 ? false : true;
	}

	@Override
	public Boolean countGood() {
		String sql = "UPDATE tm_products AS p "
				+ "INNER JOIN ( "
				+ "SELECT product_id, COUNT(*) AS cou "
				+ "FROM tm_comments "
				+ "WHERE positive_level - negative_level >= 0 AND is_waterarmy = 0 "
				+ "GROUP BY product_id "
				+ ") AS c ON p.id = c.product_id "
				+ "SET p.good_count = c.cou";
		int i = jdbcTemplate.update(sql);
		return i<0 ? false : true;
	}

	@Override
	public Boolean countBad() {
		String sql = "UPDATE tm_products AS p "
				+ "INNER JOIN ( "
				+ "SELECT product_id, COUNT(1) AS cou "
				+ "FROM tm_comments "
				+ "WHERE positive_level - negative_level < 0 AND is_waterarmy = 0 "
				+ "GROUP BY product_id "
				+ ") AS c ON p.id = c.product_id "
				+ "SET p.bad_count = c.cou;";
		int i = jdbcTemplate.update(sql);
		return i<0 ? false : true;
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

	@Override
	public List<Product> getProductsByBrandId(Integer brandId) {
		String sql = "SELECT * FROM tm_products WHERE brand_id = ? ORDER BY star, comment_count DESC";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
		List<Product> list = jdbcTemplate.query(sql, rowMapper, brandId);
		
		return list;
	}

	@Override
	public List<Product> getAllProducts() {
		String sql = "SELECT * FROM tm_products WHERE brand_id IS NOT NULL ORDER BY comment_count DESC";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
		List<Product> list = jdbcTemplate.query(sql, rowMapper);
		
		return list;
	}

	@Override
	public List<Product> getProductsForPage(Integer page, Integer pageSize) {
		if (page - 1 < 0) {
			return null;
		}
		String sql = "SELECT * FROM tm_products LIMIT ?, ? ORDER BY comment_count DESC";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
		List<Product> list = jdbcTemplate.query(sql, rowMapper, page - 1, pageSize);
		
		return list;
	}

	@Override
	public List<Product> searchProduct(String wd) {
		String sql = "SELECT * FROM tm_products WHERE NAME LIKE ? AND brand_id IS NOT NULL ORDER BY comment_count DESC";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);
		List<Product> list = jdbcTemplate.query(sql, rowMapper, "%"+wd+"%");
		
		return list;
	}

	@Override
	public Product getProductById(Integer productId) {
		
		return null;
	}

	@Override
	public Boolean updateProductInfo(Integer id, String name, String resume,
			String price, String img, Integer star) {
		String sql = "UPDATE tm_products SET `name` =?, resume =?, price =?, img =?, star =? WHERE id =?";
		return jdbcTemplate.update(sql, name, resume, price, img, star, id) >= 0 ? true : false;
	}


}
