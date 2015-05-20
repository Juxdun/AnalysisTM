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
	 * 更新p_count字段（该品牌的产品数）
	 * 条件：先执行ProductDao.updateBrandId()更新外键，完成后才有用。
	 */
	void updateP_Count();

	/**
	 * 获取所有的品牌
	 * @return 品牌列表
	 */
	List<Brand> getAllBrands();
}
