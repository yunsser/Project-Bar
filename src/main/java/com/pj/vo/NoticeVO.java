package com.pj.vo;

import java.util.ArrayList;
import java.util.List;

public class NoticeVO {
	private int num_no;
	private String master, title_no, content_no;
	private java.sql.Date ndate;
	
	public List<Notice_attachVO> notice_attach = new ArrayList<>();
	
	public NoticeVO() {}

	public NoticeVO(int num_no) { // 글번호만 있어도 객체생성 가능
		this.setNum_no(num_no);
	}
	
	public int getNum_no() {
		return num_no;
	}
	public void setNum_no(int num_no) {
		this.num_no = num_no;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getTitle_no() {
		return title_no;
	}
	public void setTitle_no(String title_no) {
		this.title_no = title_no;
	}
	public String getContent_no() {
		return content_no;
	}
	public void setContent_no(String content_no) {
		this.content_no = content_no;
	}
	public java.sql.Date getNdate() {
		return ndate;
	}
	public void setNdate(java.sql.Date ndate) {
		this.ndate = ndate;
	}

	public List<Notice_attachVO> getNotice_attach() {
		return notice_attach;
	}

	public void setNotice_attach(List<Notice_attachVO> notice_attach) {
		this.notice_attach = notice_attach;
	}

	
	
}
