package com.ekz.shopeeng.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginResponse extends BaseResponse {
	
	private int userId;
	private String name;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
