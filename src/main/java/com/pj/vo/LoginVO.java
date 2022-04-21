package com.pj.vo;

import java.util.List;
import java.util.Objects;

public class LoginVO {
	private int num, yy, mm, dd;
	private String id, pw, name, gender, email,phone;
	
//	=========================================================================================================
	
	@Override
	public String toString() {
		return String.format("%d|%s|%s|%s|%d|%d|%d|%s|%s|%s",num,id,pw,name,yy,mm,dd,gender,phone,email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public boolean equals(Object obj) {
		LoginVO u = (LoginVO) obj;
		return this.id.equals(u.getId());
	}
	
	public String getLine() {
	      return String.format("%d|%s|%s|%s|%d|%d|%d|%s|%s|%s",num,id,pw,name,yy,mm,dd,gender,phone,email);
	   }

//	=========================================================================================================
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getYy() {
		return yy;
	}
	public void setYy(int yy) {
		this.yy = yy;
	}
	public int getMm() {
		return mm;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public int getDd() {
		return dd;
	}
	public void setDd(int dd) {
		this.dd = dd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}




	

}
