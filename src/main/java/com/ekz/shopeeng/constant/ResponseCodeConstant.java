package com.ekz.shopeeng.constant;

public class ResponseCodeConstant {
	public final static String SUCCESS = "200-000";
	public final static String SYSTEM_ERROR = "400-001";
	
	public final static String ERR_LOGIN = "401-001";
	public final static String ERR_REG_EMAIL_EXIST = "402-001";
	public final static String ERR_PRODUCT_NOT_EXIST = "403-001";
	public final static String ERR_PRODUCT_QTY_EXCEEDED = "403-002";
	public final static String ERR_TRANS_NOT_EXIST = "404-001";
	public final static String ERR_TRANS_CART_EMPTY = "404-002";
	public final static String ERR_TRANS_TOKEN_EXIST = "404-003";
	public final static String ERR_TRANS_PAYMENT_METHOD_INVALID = "404-004";
	public final static String ERR_TRANS_AMOUNT_INVALID = "404-005";
}
