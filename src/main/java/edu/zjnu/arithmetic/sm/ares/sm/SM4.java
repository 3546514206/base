/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 *
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:SM2.java</p>
 * <p>创建时间	:2021-10-08 19:00:42 </p>
 */

package edu.zjnu.arithmetic.sm.ares.sm;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 国密SM4对称算法.
 *
 * @author zwb
 */
public class SM4 {

	/**
	 * The Num 16.
	 */
	static final int NUM_16 = 16;

	/**
	 * The Constant DEFAULT_ALGORITHM.
	 */
	public static final String DEFAULT_ALGORITHM = "SM4";

	/**
	 * The Constant ALGORITHM_CBC_NAME.
	 */
	public static final String ALGORITHM_CBC_NAME = "SM4/CBC/NoPadding";

	/**
	 * The Constant ALGORITHM_ECB_NAME.
	 */
	public static final String ALGORITHM_ECB_NAME = "SM4/ECB/PKCS5Padding";

	static {
		if (Security.getProvider("BC") == null) {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		}
	}

	/**
	 * Instantiates a new Sm 4.
	 */
	public SM4() {
	}

	/**
	 * 生成随机加密key.
	 *
	 * @return the string
	 */
	public static String generateKey() {
		return StringUtil.generateRandom(NUM_16);
	}

	/**
	 * 加密，算法：{@value #ALGORITHM_ECB_NAME}，加密key及明文字符串使用UTF-8解码.
	 *
	 * @param key   加密key字符串
	 * @param plain 明文字符串数据
	 * @return the string
	 */
	public static String encrypt(String key, String plain) {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		byte[] plainBytes = plain.getBytes(StandardCharsets.UTF_8);
		byte[] cipherBytes = encrypt(keyBytes, plainBytes);
		return HexUtil.byte2HexStr(cipherBytes);
	}

	/**
	 * 加密，算法：{@value #ALGORITHM_ECB_NAME}
	 *
	 * @param keyBytes key字节数据
	 * @param plain    明文字节数据
	 * @return 16进制加密字符串 byte []
	 */
	public static byte[] encrypt(byte[] keyBytes, byte[] plain) {
		return encrypt(keyBytes, plain, ALGORITHM_ECB_NAME);
	}

	/**
	 * 加密
	 *
	 * @param keyBytes  key字节数据
	 * @param plain     明文字节数据
	 * @param algorithm 算法名称
	 * @return 16进制加密字符串 byte []
	 */
	public static byte[] encrypt(byte[] keyBytes, byte[] plain, String algorithm) {
		if (keyBytes.length != 16) {
			throw new RuntimeException("err key length");
		}
		try {
			Key key = new SecretKeySpec(keyBytes, DEFAULT_ALGORITHM);
			Cipher out = Cipher.getInstance(algorithm, "BC");
			out.init(Cipher.ENCRYPT_MODE, key);
			return out.doFinal(plain);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加密，算法：{@value #ALGORITHM_CBC_NAME}
	 *
	 * @param key   the key
	 * @param plain the plain
	 * @param iv    the iv
	 * @return the string
	 */
	public static String encryptCBC(String key, String plain, String iv) {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		if (iv == null || iv.isEmpty()) {
			iv = key;
		}
		byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);
		byte[] plainBytes = plain.getBytes(StandardCharsets.UTF_8);
		byte[] cipherBytes = encryptCBC(keyBytes, plainBytes, ivBytes);
		return HexUtil.byte2HexStr(cipherBytes);
	}

	/**
	 * 加密，算法：{@value #ALGORITHM_CBC_NAME}
	 *
	 * @param keyBytes the key bytes
	 * @param plain    the plain
	 * @param ivBytes  the iv bytes
	 * @return the byte [ ]
	 */
	public static byte[] encryptCBC(byte[] keyBytes, byte[] plain, byte[] ivBytes) {
		if (keyBytes.length != 16) {
			throw new RuntimeException("err key length");
		}
		try {
			plain = PaddingUtil.pkcs7(plain);
			if (ivBytes == null) {
				ivBytes = keyBytes;
			}
			Key key = new SecretKeySpec(keyBytes, DEFAULT_ALGORITHM);
			Cipher out = Cipher.getInstance(ALGORITHM_CBC_NAME, "BC");
			out.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivBytes));
			return out.doFinal(plain);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密，算法：{@value #ALGORITHM_ECB_NAME}
	 *
	 * @param key    加密key字符串
	 * @param cipher 密文16进制字符串
	 * @return the string，默认使用UTF-8编码
	 */
	public static String decrypt(String key, String cipher) {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		byte[] cipherBytes = HexUtil.hexStr2Bytes(cipher);
		byte[] plainBytes = decrypt(keyBytes, cipherBytes);
		return new String(plainBytes, StandardCharsets.UTF_8);
	}

	/**
	 * 解密，算法：{@value #ALGORITHM_ECB_NAME}
	 *
	 * @param keyBytes the key bytes
	 * @param cipher   the cipher
	 * @return 明文字节数据 byte [ ]
	 */
	public static byte[] decrypt(byte[] keyBytes, byte[] cipher) {
		return decrypt(keyBytes, cipher, ALGORITHM_ECB_NAME);
	}

	/**
	 * 解密，算法：SM4.
	 *
	 * @param keyBytes  the key bytes
	 * @param cipher    the cipher
	 * @param algorithm 算法
	 * @return 明文字节数据 byte [ ]
	 */
	public static byte[] decrypt(byte[] keyBytes, byte[] cipher, String algorithm) {
		if (keyBytes.length != 16) {
			throw new RuntimeException("err key length");
		}
		if (cipher.length % 16 != 0) {
			throw new RuntimeException("err data length");
		}
		try {
			Key key = new SecretKeySpec(keyBytes, DEFAULT_ALGORITHM);
			Cipher in = Cipher.getInstance(algorithm, "BC");
			in.init(Cipher.DECRYPT_MODE, key);
			return in.doFinal(cipher);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密，算法：{@value #ALGORITHM_CBC_NAME}
	 *
	 * @param key    加密key字符串
	 * @param cipher 密文16进制字符串
	 * @param iv     key字符串
	 * @return the string，默认使用UTF-8编码
	 */
	public static String decryptCBC(String key, String cipher, String iv) {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		if (iv == null || iv.isEmpty()) {
			iv = key;
		}
		byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);
		byte[] cipherBytes = HexUtil.hexStr2Bytes(cipher);
		byte[] plainBytes = decryptCBC(keyBytes, cipherBytes, ivBytes);
		return new String(plainBytes, StandardCharsets.UTF_8);
	}

	/**
	 * 解密，算法：{@value #ALGORITHM_CBC_NAME}
	 *
	 * @param keyBytes the key bytes
	 * @param cipher   the cipher bytes
	 * @param ivBytes  the iv bytes
	 * @return the byte [ ]
	 */
	public static byte[] decryptCBC(byte[] keyBytes, byte[] cipher, byte[] ivBytes) {
		if (keyBytes.length != 16) {
			throw new RuntimeException("err key length");
		}
		if (cipher.length % 16 != 0) {
			throw new RuntimeException("err data length");
		}
		try {
			if (ivBytes == null) {
				ivBytes = keyBytes;
			}
			Key key = new SecretKeySpec(keyBytes, DEFAULT_ALGORITHM);
			Cipher out = Cipher.getInstance(ALGORITHM_CBC_NAME, "BC");
			out.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivBytes));
			byte[] outData = out.doFinal(cipher);
			if (outData != null) {
				outData = PaddingUtil.unPkcs7(outData);
			}
			return outData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
