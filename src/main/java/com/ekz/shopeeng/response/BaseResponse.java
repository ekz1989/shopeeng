package com.ekz.shopeeng.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.ekz.shopeeng.constant.ResponseCodeConstant;

@XmlRootElement
public class BaseResponse {
	private String statusCode;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String errCode) {
		this.statusCode = errCode;
	}
	
	public void setStatusSuccess(){
		this.statusCode = ResponseCodeConstant.SUCCESS;
	}
}
