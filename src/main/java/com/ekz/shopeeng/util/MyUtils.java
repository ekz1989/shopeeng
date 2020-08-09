package com.ekz.shopeeng.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class MyUtils {
	
	public static String getMD5(String data) throws NoSuchAlgorithmException{
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		messageDigest.update(data.getBytes());
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }
	

	public static String randomString( int len , boolean isAlphaNumeric){
		String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String num = "0123456789";
		
		String AB = new String(alph);
		if(isAlphaNumeric) AB.concat(num);
		
		SecureRandom rnd = new SecureRandom();
		
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}
	
	public static int randomNumber(int min, int max){
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
}
