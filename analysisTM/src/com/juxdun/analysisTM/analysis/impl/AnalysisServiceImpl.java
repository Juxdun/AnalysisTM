package com.juxdun.analysisTM.analysis.impl;

import java.util.List;

import com.juxdun.analysisTM.analysis.AnalysisService;
import com.juxdun.analysisTM.analysis.Brand;
import com.juxdun.analysisTM.analysis.Detail;
import com.juxdun.analysisTM.analysis.Product;

public class AnalysisServiceImpl implements AnalysisService {

	@Override
	public void analyse() {
		// 1.拿到所有Extraction对象。
		// 2.合并相同商品的Extraction对象，主要合并其列表
		// 3.去除水军评论
	}

	@Override
	public List<Brand> listBrands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listProductByBrand(String cludid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Detail getDetailById(String cludid) {
		// TODO Auto-generated method stub
		return null;
	}

}
