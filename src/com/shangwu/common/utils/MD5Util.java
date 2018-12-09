package com.shangwu.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static void main(String[] args) {
    	String strA = "page_no=1&page_size=500&start_time="+DateUtils.getBeforeYesterday()+"&type=bossAccXmlFile&key=ERJEOJFDKFJlkdjflkdjfjkdfjjsjfksd5675fd";
        System.out.println(getMD5(strA));
    }
    
	public static String getMD5(String url) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(url.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				String str = Integer.toHexString(b & 0xFF);
				if (str.length() == 1) {
					sb.append("0");
				}
				sb.append(str);
			}
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

}
