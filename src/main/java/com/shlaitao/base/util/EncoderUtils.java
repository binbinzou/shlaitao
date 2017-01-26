package com.shlaitao.base.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class EncoderUtils {
	public static String md5Encode(String origin){
		return md5Encode(origin,null);
	}
	public static String md5Encode(String origi,String salt){
		Md5PasswordEncoder md5=new Md5PasswordEncoder();
		String encryptedStr= md5.encodePassword(origi, salt);
		return encryptedStr;
	}
	public static boolean CheckEqualWithMd5(String origin,String salt,String encryptedStr){
		Md5PasswordEncoder md5=new Md5PasswordEncoder();
		return md5.isPasswordValid(encryptedStr, origin, salt);
	}
	
	public static boolean CheckEqualWithMd5(String origin,String encryptedStr){
		return CheckEqualWithMd5( origin, null,encryptedStr);
	}
	public static void main(String[] args){
		String pass="qqqqqq";
		System.out.println(md5Encode(pass));
		System.out.println(CheckEqualWithMd5(pass,"324adc638720836f98eb6924c69de5fb"));
	}
}
