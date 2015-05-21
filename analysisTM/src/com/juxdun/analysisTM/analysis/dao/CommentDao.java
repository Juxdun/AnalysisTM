package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Comment;

public interface CommentDao {
	
	/**
	 * 保存评论
	 * @param comments
	 */
	void batchInsertComments(List<Comment> comments);
//	void batchInsertComments(List<Object[]> batchArgs);
	
	Boolean updateProductId();
	
	/**
	 * 标记水军评论
	 * @return 
	 */
	Boolean signWaterArmy();
	
	/**
	 * 分析评论的好评度和差评度
	 */
	@Deprecated
	void analyseLevel();
	
	/**
	 * 分析计算好评度
	 */
	void analysePositveLevel();

	/**
	 * 分析计算差评度
	 */
	void analyseNegativeLevel();
	
	/**
	 * 取评论
	 * @param productId 根据商品
	 * @return 列表
	 */
	List<Comment> getCommentsByProductId(Integer productId);
	
	/**
	 * 分页取评论
	 * @param productId 商品id
	 * @param page 第几页
	 * @param pageSize 每一页数量
	 * @return 列表
	 */
	List<Comment> getCommentsByProductIdForPage(Integer productId, Integer page, Integer pageSize);
	
	/**
	 * 关键字取评论
	 * @param clueid 
	 * @param keyword
	 * @return 列表
	 */
	List<Comment> getKeywordComments(Integer clueid, String keyword);
	
	/**
	 * 取水军数
	 * @return 数量
	 */
	Integer getWaCount();

	/**
	 * 取评论数
	 * @return 数量
	 */
	Integer getAllCount();

	/**
	 * 取水军评论列表
	 * 按人名排序
	 * @return 列表
	 */
	List<Comment> getWaComments();
	
	/**
	 * 分页取水军评论
	 * @param page 第几页
	 * @param pageSize 每一页数量
	 * @return 列表
	 */
	List<Comment> getWaCommentsForPage(Integer page, Integer pageSize);

	Boolean analysePositive();

	Boolean analyseNegative();

}
