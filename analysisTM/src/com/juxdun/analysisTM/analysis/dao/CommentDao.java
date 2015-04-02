package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Comment;

public interface CommentDao {
	
	Comment get(int id);
	
	/**
	 * 保存评论
	 * @param comments
	 */
	void batchInsertComments(List<Comment> comments);
//	void batchInsertComments(List<Object[]> batchArgs);
	
	/**
	 * 移除水军评论
	 */
	void deleteWaterArmy();
	
	/**
	 * 移除<10 个字符评论
	 */
	void deleteLess10Char();

}
