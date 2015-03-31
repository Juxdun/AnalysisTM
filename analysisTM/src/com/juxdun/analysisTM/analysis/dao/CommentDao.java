package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.Comment;

public interface CommentDao {
	
	Comment get(int id);
	
	/**
	 * 保存评论
	 * @param comments
	 */
	void saveComments(List<Object[]> batchArgs);
	
	/**
	 * 移除水军
	 */
	void deleteWaterArmy();

}
