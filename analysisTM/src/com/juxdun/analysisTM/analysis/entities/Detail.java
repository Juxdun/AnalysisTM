package com.juxdun.analysisTM.analysis.entities;

/**
 * 商品详细
 * @author Juxdun
 *
 */
public class Detail {

	/**
	 * 商品名
	 */
	private String name;
	
	/**
	 * 描述
	 */
	private String resume;

	/**
	 * 月售量
	 */
	private String monSaleVol;
	
	/**
	 * 累计评论
	 */
	private String cumuComment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getMonSaleVol() {
		return monSaleVol;
	}

	public void setMonSaleVol(String monSaleVol) {
		this.monSaleVol = monSaleVol;
	}

	public String getCumuComment() {
		return cumuComment;
	}

	public void setCumuComment(String cumuComment) {
		this.cumuComment = cumuComment;
	}
	
}
