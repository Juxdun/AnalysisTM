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
	 * 分析
	 * 1. 存数据到数据库
	 * 2. sql标记水军
	 * 3. sql建立商品和评论的索引，通过clueid连接 
	 * 4. sql建立品牌和商品的索引，通过clueid连接
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
		commentDao.analyseLevel();
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
	public List<Product> listProductByBrandId(Integer productClueid) {
		return productDao.getProductsByBrandId(productClueid);
	}

	@Override
	public Detail getDetailById(String cludid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> listCommentByProductId(Integer clueid) {
		return commentDao.getCommentsByProductId(clueid);
	}

	@Override
	public List<Product> searchProduct(String wd) {
		
		return productDao.searchProduct(wd);
	}

	@Override
	public Integer getWaCount() {
		return commentDao.getWaCount();
	}

	@Override
	public Integer getAllCount() {
		return commentDao.getAllCount();
	}

	@Override
	public List<Comment> getWaComments() {
		return commentDao.getWaComments();
	}

	@Override
	public List<Comment> getKeywordComments(Integer clueid, String keyword) {
		return commentDao.getKeywordComments(clueid, keyword);
	}

	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

}
