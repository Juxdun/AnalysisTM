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

//修改商品信息模块接口
	/**
	 * 更新商品信息接口
	 * @param id
	 * @param name
	 * @param resume
	 * @param price
	 * @param img
	 * @param star
	 * @return 是否成功
	 */
	Boolean updateProductInfo(Integer id, String name, String resume,
	String price, String img, Integer star);

//导入模块的接口
	/**
	 * 插入Brands表
	 * @return
	 */
	Boolean insertBrands();

	/**
	 * 插入Product表
	 * @return
	 */
	Boolean insertProducts();

	/**
	 * 插入Comment表
	 * @return
	 */
	Boolean insertComments();

	/**
	 * 连接品牌和商品表
	 * @return
	 */
	Boolean linkB2P();

	/**
	 * 连接商品和评论表
	 * @return
	 */
	Boolean linkP2C();

	/**
	 * 一键插入和连接
	 * @return
	 */
	Boolean insertAndLink();

// 分析模块的接口
	/**
	 * 标记水军
	 * @return
	 */
	Boolean signWater();

	/**
	 * 分析好评度
	 * @return
	 */
	Boolean analysePositive();

	/**
	 * 分析差评度
	 * @return
	 */
	Boolean analyseNegative();

	/**
	 * 统计评论数
	 * @return
	 */
	Boolean countComment();

	/**
	 * 统计水军数
	 * @return
	 */
	Boolean countWater();

	/**
	 * 统计好评数
	 * @return
	 */
	Boolean countGood();

	/**
	 * 统计差评数
	 * @return
	 */
	Boolean countBad();

	/**
	 * 统计推荐度Star
	 * @return
	 */
	Boolean countStar();

	/**
	 * 一键分析并统计
	 * @return
	 */
	Boolean analyseAndCount();

	/**
	 * 一键插入和分析
	 * @return
	 */
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