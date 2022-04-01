/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:SM4Test.java</p>
 * <p>创建时间	:2021-10-19 16:40:52 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test.java.cn.com.yitong.util.sm;

import edu.zjnu.arithmetic.sm.ares.sm.HexUtil;
import edu.zjnu.arithmetic.sm.ares.sm.SM4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The type Sm 4 test.
 */
public class SM4Test {

	/**
	 * Test generate key.
	 */
	@Test
	public void testGenerateKey() {
		String key = SM4.generateKey();
		System.out.println(key);
	}

	/**
	 * Test encrypt.
	 */
	@Test
	public void testEncrypt() {
		// 加密密钥，密钥为16个字节长度的字符串
		String key = "1234567890abcdef";
		// 原文
		String data = "我是中国人abc123";
		// 数据加密
		String cipher = SM4.encrypt(key, data);
		System.out.println(cipher);
		// output:F143B2CB7A51EB8A43B2F6E45909880FDEA63016951EE0578BA39669B2D309BA

	}

	/**
	 * Test encrypt.
	 */
	@Test
	public void testEncryptSpace() {
		String msgString = "我是中国人abc123   ";
		String key = "1234567890abcdef";
		String ciphertxt = SM4.encrypt(key, msgString);
		System.out.println(ciphertxt);

		String plain = SM4.decrypt(key, ciphertxt);
		System.out.println(msgString.length() + "\t" + msgString);
		System.out.println(plain.length() + "\t" + plain);
	}

	/**
	 * Test decrypt.
	 */
	@Test
	public void testDecrypt() {
		// 加密密钥，密钥为16个字节长度的字符串
		String key = "1234567890abcdef";
		// 密文
		String cipher = "F143B2CB7A51EB8A43B2F6E45909880FDEA63016951EE0578BA39669B2D309BA";
		// 原文，默认使用UTF-8编码
		String plain = SM4.decrypt(key, cipher);
		System.out.println(plain);
		// output：我是中国人abc123
	}

	/**
	 * Test decrypt.
	 */
	@Test
	public void testEncryptAndDecrypt() {
		String key = SM4.generateKey();
		System.out.println(key);
		System.out.println(HexUtil.byte2HexStr(key.getBytes()));
		String data = "我是中国人abc123";
		String cipher = SM4.encrypt(key, data);
		String plain = SM4.decrypt(key, cipher);
		Assertions.assertEquals(data, plain);
	}

	/**
	 * Encrypt CBC.
	 */
	@Test
	public void encryptCBC() {
		// 明文
		String plain = "我是中国人abc123";
		// key 字符串，16个字节长度的字符串
		String key = "1234567890abcdef";
		// iv 字符串，16个字节长度的字符串
		String iv = "1234567890abcdef";
		// CBC模式加密
		String cipher = SM4.encryptCBC(key, plain, iv);
		System.out.println(cipher);
		// output:C4E5AA9937153D4A395F379588C4A149AD6FCBCB5F5A628EC03C17E01C75C16B
	}

	/**
	 * Test decrypt.
	 */
	@Test
	public void testDecryptCBC() {
		// 密文
		String cipher = "C4E5AA9937153D4A395F379588C4A149AD6FCBCB5F5A628EC03C17E01C75C16B";
		// key 字符串，16个字节长度的字符串
		String key = "1234567890abcdef";
		// iv 字符串，16个字节长度的字符串
		String iv = "1234567890abcdef";
		// CBC模式解密
		String plain = SM4.decryptCBC(key, cipher, iv);
		System.out.println(plain);
		// output:我是中国人abc123
	}
}