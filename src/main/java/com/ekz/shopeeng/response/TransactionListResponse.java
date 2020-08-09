package com.ekz.shopeeng.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransactionListResponse extends BaseResponse {

	private List<TransactionItem> transactions;

	public List<TransactionItem> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionItem> transactions) {
		this.transactions = transactions;
	}
}
