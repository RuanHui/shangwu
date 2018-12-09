package com.shangwu.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * 3DES加密工具类
 * 
 * @author liuyq
 * @date 2012-10-11
 */
public class Des3Util {
	// 密钥
	private final static String secretKey = "liuyunqiang@lx100$#365#$";
	// 向量
	private final static String iv = "01234567";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param plainText 普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText) {
		Key deskey = null;
		byte[] encryptData = null;
		try {
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);

			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			encryptData = cipher.doFinal(plainText.getBytes(encoding));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Base64.encode(encryptData);

	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText 加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText) {
		Key deskey = null;
		byte[] decryptData = null;
		String result = "";
		if (null != encryptText && !"".equals(encryptText)) {
			try {
				DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
				SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
				deskey = keyfactory.generateSecret(spec);
				Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
				IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

				decryptData = cipher.doFinal(Base64.decode(encryptText));
				result = new String(decryptData, encoding);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	

}
