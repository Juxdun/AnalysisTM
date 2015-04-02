package com.juxdun.analysisTM.analysis.entities;

/**
 * 商品
 * @author Juxdun
 *
 */
public class Product {
	
	private String clueid;

	/**
	 * 商品名
	 */
	private String name;
	
	/**
	 * 价格
	 */
	private String price;
	
	/**
	 * 原链接
	 */
	private String page;
	
	/**
	 * 图片链接
	 */
	private String img;
	
	/**
	 * 商店名
	 */
	private String shopName;
	
	/**
	 * 商店链接
	 */
	private String shopUrl;

	public String getClueid() {
		return clueid;
	}

	public void setClueid(String clueid) {
		this.clueid = clueid;
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

	public String getShopName() {
		return shopName;
	}

	public String getShopUrl() {
		return shopUrl;
	}
	
}
