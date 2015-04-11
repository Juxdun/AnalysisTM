package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Product;

public interface ProductDao {

	/**
	 * 取商品
	 * @param brand 根据品牌
	 * @return 列表
	 */
	List<Product> getProductsByBrand(Brand brand);

	/**
	 * 取全部商品
	 * @return 列表
	 */
	List<Product> getAllProducts();

	/**
	 * 保存商品
	 * @param products
	 */
	void batchInsertProducts(List<Product> products);
	
	/**
	 * 连接数据库tm_products和tm_comments的索引id
	 */
	void updateProductTable();
	
	void countCommentOfEveryProduct();
}
