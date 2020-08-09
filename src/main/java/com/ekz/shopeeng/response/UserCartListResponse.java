package com.ekz.shopeeng.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserCartListResponse extends BaseResponse {

	private List<UserCartItem> items;

	public List<UserCartItem> getItems() {
		return items;
	}

	public void setItems(List<UserCartItem> items) {
		this.items = items;
	}

	
}
