package com.juxdun.analysisTM.analysis.entities;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

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
	private BaseURI baseURI;

	/**
	 * 列表
	 */
	private List list;

	public String getClueid() {
		return clueid;
	}

	public String getFullpath() {
		return fullpath;
	}

	public String getTheme() {
		return theme;
	}

	public String getMiddle() {
		return middle;
	}

	public BaseURI getBaseURI() {
		return baseURI;
	}

	public List getList() {
		return list;
	}

	@Override
	public String toString() {
		return "Extraction [baseURI=" + baseURI + "]";
	}

	/**
	 * 移除水军评论
	 * @throws IOException 
	 */
	public void removeWaterArmyComment(){
		if (list.size()>0 && list.get(0) instanceof Comment) {// 如果list不空，并且里面是Comment对象


			try {

				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Comment comment = (Comment) iterator.next();
					if (comment.getContent().length() < 6) {// 如果评论内容少于6个字符，认为是水军
						iterator.remove();
						continue;
					}else {
						String content = comment.getContent();
						StringReader sr = new StringReader(content);

						//使用独立于Lucene的分词器。
						IKSegmenter ik = new IKSegmenter(sr, true);
						Lexeme lexeme = null;
						while (null!=(lexeme=ik.next())) {
							//logging
						}
					}
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
