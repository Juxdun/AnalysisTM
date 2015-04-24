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

	Integer getWaCount();

	Integer getAllCount();

	List<Comment> getWaComments();

}
