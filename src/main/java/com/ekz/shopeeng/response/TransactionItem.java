package com.ekz.shopeeng.response;

import java.util.Date;

import com.ekz.shopeeng.entity.Transaction;

public class TransactionItem {
	
	private int transactionId;

	private int amount;

	private int fee;

	private String paymentMethod;

	private String status;

	private Date updateDate;
	
	public TransactionItem() {
		// TODO Auto-generated constructor stub
	}
	
	public TransactionItem(Transaction transaction){
		this.setTransactionId(transaction.getTransactionId());
		this.setAmount(transaction.getAmount());
		this.setFee(transaction.getFee());
		this.setPaymentMethod(transaction.getPaymentMethod());
		this.setStatus(transaction.getStatus());
		this.setUpdateDate(transaction.getUpdateDate());
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
