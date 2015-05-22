package com.juxdun.analysisTM.analysis.service;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Brand;
import com.juxdun.analysisTM.analysis.entities.Comment;
import com.juxdun.analysisTM.analysis.entities.Detail;
import com.juxdun.analysisTM.analysis.entities.Product;

public interface AnalysisService {
	
// ------------后台功能
	/**
	 * 分析
	 * 1. 存数据到数据库
	 * 2. sql标记水军
	 * 3. sql建立商品和评论的索引，通过clueid连接 
	 * 4. sql建立品牌和商品的索引，通过clueid连接
	 */
	@Deprecated
	void analyse();
	
	Boolean updateProductInfo(Integer id, String name, String resume,
	String price, String img, Integer star);

	Boolean insertBrands();

	Boolean insertProducts();

	Boolean insertComments();

	Boolean linkB2P();

	Boolean linkP2C();

	Boolean insertAndLink();

	Boolean signWater();

	Boolean analysePositive();

	Boolean analyseNegative();

	Boolean countComment();

	Boolean countWater();

	Boolean countGood();

	Boolean countBad();

	Boolean countStar();

	Boolean analyseAndCount();

	Boolean insertAndAnalyse();

	List<Comment> getWaComments();

	Integer getWaCount();

	Integer getAllCount();

	
// -----------前台功能-------------------------------------------
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
	List<Product> listProductByBrandId(Integer brandId);
	
	/**
	 * 列出某品牌的商品
	 * @param cludid 品牌索引
	 * @return 商品列表
	 */
	List<Comment> listCommentByProductId(Integer proId);
	
	/**
	 * 拿到某以商品详情
	 * @param cludid 商品索引
	 * @return 商品详情
	 */
	Detail getDetailById(String cludid);

	List<Comment> getKeywordComments(Integer clueid, String keyword);

	Product getProductById(Integer productId);

	/**
	 * 搜索商品
	 * @param wd 关键字
	 * @return 列表
	 */
	List<Product> searchProduct(String wd);

}