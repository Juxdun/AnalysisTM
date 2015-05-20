package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Product;

public interface ProductDao {

	/**
	 * 保存商品
	 * @param products
	 */
	void batchInsertProducts(List<Product> products);
	
	/**
	 * 更新外键字段brand_id
	 */
	void updateBrandId();
	
	/**
	 * 更新字段comment_count
	 */
	void updateCommentCount();
	
	/**
	 * 更新字段water_count
	 */
	void updateWaterCount();

	/**
	 * 向tm_products写入统计数据
	 */
	@Deprecated
	void updateProductTable();

	/**
	 * 取商品
	 * @param brandId 根据品牌
	 * @return 列表
	 */
	List<Product> getProductsByBrandId(Integer brandId);

	/**
	 * 取全部商品
	 * @return 列表
	 */
	List<Product> getAllProducts();
	
	/**
	 * 取某一页商品
	 * @param page 第几页
	 * @param pageSize 每一页数量
	 * @return
	 */
	List<Product> getProductsForPage(Integer page, Integer pageSize);

	/**
	 * 搜索
	 * 根据关键字搜索Product
	 * @param wd 关键字
	 * @return Product的集合
	 */
	List<Product> searchProduct(String wd);

	Product getProductById(Integer productId);
	
}
