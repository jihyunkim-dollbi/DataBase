package com.sist.vo;

public class MenuCategoryVO {

	/*
	 
	CATENO NOT NULL NUMBER         
	TITLE  NOT NULL VARCHAR2(200)  
	LINK   NOT NULL VARCHAR2(1000) 

	 */
		
	  private int cateno;
	  private String title;
	  private String link;
	  
	  
	  
	public int getCateno() {
		return cateno;
	}
	public void setCateno(int cateno) {
		this.cateno = cateno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	
	
	
	
}
