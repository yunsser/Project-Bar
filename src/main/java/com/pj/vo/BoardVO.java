package com.pj.vo;

import java.util.ArrayList;
import java.util.List;

public class BoardVO {
	private int num;
	private String author, title, contents;
	private java.sql.Date date;
//	============================================================================================
	public List<AttachVO> attach = new ArrayList<>();
	
//	============================================================================================

	public BoardVO() {}

	public BoardVO(int num) { // 글번호만 있어도 객체생성 가능
		this.setNum(num);
	}
	
//	============================================================================================

	@Override
	public String toString() {
		return num + " " + title + " " + contents + " " + date;
	}
	
	@Override
	public boolean equals(Object obj) { // 글번호가 같으면 같은 글이라고 인정
		BoardVO other = (BoardVO) obj;
		return this.num==other.num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public List<AttachVO> getAttach() {
		return attach;
	}

	public void setAttach(List<AttachVO> attach) {
		this.attach = attach;
	}
	
//	============================================================================================
	
	
	
	
	
}
