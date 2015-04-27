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
	
	/**
	 * 标记水军评论
	 */
	void signWaterArmy();
	
	/**
	 * 移除<10 个字符评论
	 */
	void deleteLess10Char();
	
	
	/**
	 * 取评论
	 * @param product 根据商品
	 * @return 列表
	 */
	List<Comment> getCommentsByClueid(Integer clueid);
	
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

}
