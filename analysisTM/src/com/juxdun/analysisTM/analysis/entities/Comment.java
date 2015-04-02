package com.juxdun.analysisTM.analysis.entities;

/**
 * 商品评论
 * @author Juxdun
 *
 */
public class Comment {
	
	private int id;
	
	private String clueid;

	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 评论人
	 */
	private String person;

	public int getId() {
		return id;
	}


	public String getContent() {
		return content;
	}
	
	
	public String getDate() {
		return date;
	}

	public String getPerson() {
		return person;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getClueid() {
		return clueid;
	}


	public void setClueid(String clueid) {
		this.clueid = clueid;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", clueid=" + clueid + ", content="
				+ content + ", date=" + date + ", person=" + person + "]";
	}


	// 如果date和person相同，评论相同。
	// 通过重写两个方法，在HashMap达到key相同
	@Override
	public boolean equals(Object obj) {
		Comment c = (Comment) obj;
		if (c.getDate().trim().equals(date.trim()) && c.getPerson().trim().equals(person.trim())) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return date.trim().hashCode() + person.trim().hashCode();
	}


	public Comment(String clueid, String content, String date, String person) {
		super();
		this.clueid = clueid;
		this.content = content;
		this.date = date;
		this.person = person;
	}
	
	public Comment() {
	}
	
}
