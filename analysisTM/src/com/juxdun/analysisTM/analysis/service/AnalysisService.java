package com.juxdun.analysisTM.analysis.service;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Detail;
import com.juxdun.analysisTM.analysis.entities.Product;

public interface AnalysisService {
	
	/**
	 * 分析
	 */
	void analyse();
	
	/**
	 * 列出所有品牌
	 * @return 品牌列表
	 */
	List<Brand> listBrands();
	
	/**
	 * 列出所有商品
	 * @return 商品列表
	 */
	List<Product> listAllProducts();
	
	/**
	 * 列出某品牌的商品
	 * @param cludid 品牌索引
	 * @return 商品列表
	 */
	List<Product> listProductByBrand(Brand brand);
	
	/**
	 * 拿到某以商品详情
	 * @param cludid 商品索引
	 * @return 商品详情
	 */
	Detail getDetailById(String cludid);

}