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
import com.juxdun.analysisTM.analysis.util.XSteamUtil.OnInsertDB;

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
//		commentDao.analyseLevel();
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
	public Detail getDetailById(String id) {
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
		return commentDao.getWaCommentsForPage(1, 100);
	}

	@Override
	public List<Comment> getKeywordComments(Integer clueid, String keyword) {
		return commentDao.getKeywordComments(clueid, keyword);
	}

	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public Boolean updateProductInfo(Integer id, String name, String resume,
			String price, String img, Integer star) {
		return productDao.updateProductInfo(id, name, resume, price, img, star);
	}

	@Override
	public Boolean insertBrands() {
		List<Brand> brands = xSteamUtil.getBrands();
		brandDao.batchInsertBrands(brands);
		return true;
	}

	@Override
	public Boolean insertProducts() {
		// 使用读一个文件调用一次插入方法。
		xSteamUtil.prooductsToDb(new OnInsertDB() {
			@Override
			public void onInsert(List<?> list) {
				productDao.batchInsertProducts((List<Product>) list);
			}
		});
		return true;
	}

	@Override
	public Boolean insertComments() {
		xSteamUtil.commentsToDb(new OnInsertDB() {
			@Override
			public void onInsert(List<?> list) {
				commentDao.batchInsertComments((List<Comment>) list);
			}
		});
		return true;
	}

	@Override
	public Boolean linkB2P() {
		return productDao.updateBrandId();
	}

	@Override
	public Boolean linkP2C() {
		return commentDao.updateProductId();
	}

	@Override
	public Boolean insertAndLink() {
		this.insertBrands();
		this.insertProducts();
		this.insertComments();
		this.linkB2P();
		this.linkP2C();
		return true;
	}

	@Override
	public Boolean signWater() {
		return commentDao.signWaterArmy();
	}

	@Override
	public Boolean analysePositive() {
		return commentDao.analysePositive();
	}

	@Override
	public Boolean analyseNegative() {
		return commentDao.analyseNegative();
	}

	@Override
	public Boolean countWater() {
		return productDao.countWater();
	}

	@Override
	public Boolean countGood() {
		return productDao.countGood();
	}

	@Override
	public Boolean countBad() {
		return productDao.countBad();
	}

	@Override
	public Boolean analyseAndCount() {
		this.countComment();
		this.countWater();
		this.countGood();
		this.countBad();
		this.countStar();
		return true;
	}

	@Override
	public Boolean countComment() {
		return productDao.countComment();
	}

	@Override
	public Boolean countStar() {
		return productDao.countStar();
	}

	@Override
	public Boolean insertAndAnalyse() {
		this.insertBrands();
		this.insertProducts();
		this.insertComments();
		this.linkB2P();
		this.linkP2C();
		this.countComment();
		this.countWater();
		this.countGood();
		this.countBad();
		this.countStar();
		return true;
	}

}
