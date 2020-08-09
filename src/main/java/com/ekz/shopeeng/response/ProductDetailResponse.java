package com.ekz.shopeeng.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductDetailResponse extends BaseResponse {

	private ProductItem detail;

	public ProductItem getDetail() {
		return detail;
	}
	public void setDetail(ProductItem detail) {
		this.detail = detail;
	}

}
