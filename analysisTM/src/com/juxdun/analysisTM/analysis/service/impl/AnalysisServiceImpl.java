package com.juxdun.analysisTM.analysis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juxdun.analysisTM.analysis.dao.BrandDao;
import com.juxdun.analysisTM.analysis.dao.CommentDao;
import com.juxdun.analysisTM.analysis.dao.ProductDao;
import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.entities.Detail;
import com.juxdun.analysisTM.analysis.entities.Product;
import com.juxdun.analysisTM.analysis.service.AnalysisService;
import com.juxdun.analysisTM.analysis.util.XSteamUtil;

@Service
public class AnalysisServiceImpl implements AnalysisService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private XSteamUtil xSteamUtil;
	
	/**
	 * 去除水军分析
	 * 1.拿到所有的comment
	 * 2.存到数据库
	 * 3.数据库去除水军
	 */
	@Override
	public void analyse() {
//		brandDao.batchInsertBrands(xSteamUtil.getBrands());
//		commentDao.batchInsertComments(xSteamUtil.getCommentsFromXml());
//		productDao.batchInsertProducts(xSteamUtil.getProducts());
//		
//		commentDao.signWaterArmy();
//		productDao.updateProductTable();
//		brandDao.updateBrandTable();
		
//		// 4.hash收集有用评论
	}

	@Override
	public List<Brand> listBrands() {
		return brandDao.getAllBrands();
	}

	@Override
	public List<Product> listAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public List<Product> listProductByProductClueid(Integer productClueid) {
		return productDao.getProductsByProductClueid(productClueid);
	}

	@Override
	public Detail getDetailById(String cludid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> listCommentByClueid(Integer clueid) {
		return commentDao.getCommentsByClueid(clueid);
	}

}
