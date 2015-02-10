package com.juxdun.analysisTM.analysis;

import java.util.Iterator;
import java.util.List;

public class Extraction {

	/**
	 * 索引id
	 */
	private String clueid;

	/**
	 * 路径
	 */
	private String fullpath;

	/**
	 * 主题名
	 */
	private String theme;

	/**
	 * 
	 */
	private String middle;

	/**
	 * 
	 */
	private String baseURI;

	/**
	 * 列表
	 */
	private List list;

	public String getClueid() {
		return clueid;
	}

	public List getList() {
		return list;
	}

	/**
	 * 移除水军评论
	 */
	public void removeWaterArmyComment() {
		if (list.size()>0 && list.get(0) instanceof Comment) {// 如果list不空，并且里面是Comment对象
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Comment comment = (Comment) iterator.next();
				if (comment.getContent().length() < 6) {// 如果评论内容少于6个字符，认为是水军
					iterator.remove();
				}
			}
		}
	}

}
