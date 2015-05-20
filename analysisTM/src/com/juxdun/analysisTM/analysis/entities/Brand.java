package com.juxdun.analysisTM.analysis.entities;

/**
 * 手机品牌
 * @author Juxdun
 *
 */
public class Brand {

	private Integer id;

	/**
	 * 名
	 */
	private String name;

	/**
	 * 链接
	 */
	private String page;

	/**
	 * 图片链接
	 */
	private String img;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public String getPage() {
		return page;
	}

	public String getImg() {
		return img;
	}

	@Override
	public String toString() {
		return "Brand [name=" + name + ", page=" + page + ", img=" + img + "]";
	}
	
	
}
