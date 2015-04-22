package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Brand;

public interface BrandDao {
	/**
	 * 保存品牌
	 * @param brands
	 */
	void batchInsertBrands(List<Brand> brands);
	
	/**
	 * 获取所有的品牌
	 * @return 品牌列表
	 */
	List<Brand> getAllBrands();
	
	/**
	 * 更新数据库
	 * 1.建立product的索引
	 */
	void updateBrandTable();
}
