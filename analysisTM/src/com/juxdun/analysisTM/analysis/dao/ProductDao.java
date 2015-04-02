package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Product;

public interface ProductDao {

	/**
	 * 保存商品
	 * @param products
	 */
	void batchInsertProducts(List<Product> products);
	
	List<Product> getProductsByBrand(Brand brand);
}
