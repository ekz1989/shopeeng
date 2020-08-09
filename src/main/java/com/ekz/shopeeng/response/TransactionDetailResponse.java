package com.ekz.shopeeng.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransactionDetailResponse extends BaseResponse {

	private TransactionItem detail;

	public TransactionItem getDetail() {
		return detail;
	}

	public void setDetail(TransactionItem detail) {
		this.detail = detail;
	}
}
