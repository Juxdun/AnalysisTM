package com.juxdun.analysisTM.analysis.entities;

/**
 * 商品
 * @author Juxdun
 *
 */
public class Product {
	private int id;
	private String clueid;
	private String fullpath;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClueid() {
		return clueid;
	}

	public void setClueid(String clueid) {
		this.clueid = clueid;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
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

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}
	
	
}
