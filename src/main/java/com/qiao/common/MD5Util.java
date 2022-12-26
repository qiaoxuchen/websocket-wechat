package com.qiao.common;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	public static final String MD5_KEY = "QWERTYUIOP123456789";

	/**
	 * @param text明文
	 * @param key密钥
	 * @return 密文
	 */
	// 带秘钥加密
	public static String encodeMD5(String text, String key) throws Exception {
		// 加密后的字符串
		String md5str = DigestUtils.md5Hex(text + key);
		return md5str;
	}

	// 不带秘钥加密
	public static String encodeMD52(String text) throws Exception {
		// 加密后的字符串
		String md5str = DigestUtils.md5Hex(text);
		return md5str;
	}

	/**
	 * MD5验证方法
	 *
	 * @param text明文
	 * @param key密钥
	 * @param md5密文
	 */
	// 根据传入的密钥进行验证
	public static boolean verify(String text, String key, String md5) throws Exception {
		String md5str = encodeMD5(text, key);
		if (md5str.equalsIgnoreCase(md5)) {
			System.out.println("MD5验证通过");
			return true;
		}
		return false;
	}
}
