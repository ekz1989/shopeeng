package com.ekz.shopeeng.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CheckoutResponse extends BaseResponse {
	
	private int transactionId;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
}
