package com.pj.vo;

import java.util.ArrayList;
import java.util.List;

public class ProductVO {
	
	private int num_pr, price;
	private String name, description;
	private java.sql.Date date_da;
//	============================================================================================
	public List<ImgVO> img = new ArrayList<>();
	
	
	public int getNum_pr() {
		return num_pr;
	}
	public void setNum_pr(int num_pr) {
		this.num_pr = num_pr;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.sql.Date getDate_da() {
		return date_da;
	}
	public void setDate_da(java.sql.Date date_da) {
		this.date_da = date_da;
	}
	public List<ImgVO> getImg() {
		return img;
	}
	public void setImg(List<ImgVO> img) {
		this.img = img;
	}
	
	
	
}
