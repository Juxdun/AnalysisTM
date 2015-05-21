package com.juxdun.analysisTM.analysis.entities;

/**
 * 商品
 * @author Juxdun
 *
 */
public class Product {
	
	private Integer id;

	/**
	 * 商品名
	 */
	private String name;
	
	/**
	 * 描述
	 */
	private String resume;
	
	/**
	 * 价格
	 */
	private String price;
	
	/**
	 * 图片链接
	 */
	private String img;
	
	/**
	 * 月成交量
	 */
	private String monTradeVol;
	
	/**
	 * 原链接
	 */
	private String page;
	
	private Integer star;
	
	private Integer commentCount;
	
	private Integer waterCount;
	
	private Integer goodCount;
	
	private Integer badCount;

	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}

	public Integer getBadCount() {
		return badCount;
	}

	public void setBadCount(Integer badCount) {
		this.badCount = badCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public String getPage() {
		return page;
	}

	public String getImg() {
		return img;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getWaterCount() {
		return waterCount;
	}

	public void setWaterCount(Integer waterCount) {
		this.waterCount = waterCount;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonTradeVol() {
		return monTradeVol;
	}

	public void setMonTradeVol(String monTradeVol) {
		this.monTradeVol = monTradeVol;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}
	
	
}
