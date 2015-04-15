package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Brand;

public interface BrandDao {
	/**
	 * 保存品牌
	 * @param brands
	 */
	void batchInsertBrands(List<Brand> brands);
	
	
	List<Brand> getAllBrands();
	
	void updateBrandTable();
}
