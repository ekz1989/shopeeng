package com.ekz.shopeeng.constant.enu;

public enum PaymentMethod {

	CREDIT_CARD("CC"),
	CASH("CA"),
	DIRECT_DEBIT("DD");
	
	private final String code;
	
	private PaymentMethod(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public boolean codeEquals(String code){
		return this.code.equals(code);
	}
	
}
