/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 *
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:PncCrypto.java</p>
 * <p>创建时间	:2021-10-19 15:13:47 </p>
 */

package edu.zjnu.arithmetic.sm.ares.sm;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 三段式加解密算法
 *
 * <pre>
 * 1、整个加密算法包含3部分：
 *  1）消息密文
 *  2）密钥密文
 *  3）消息签名
 * 2、整个算法包含2套 SM2公私钥对A、B
 *  1）A公钥在客户端加密对称密钥，服务端用A私钥解密对称密钥
 *  2）B私钥在客户端签名消息明文，服务端用B公钥验证签名数据
 * 3、客户端加密过程
 *  1）、生成随机对称加密密钥K
 *  2）、使用对称算法SM4并使用密钥K 加密消息明文P,得到密文X
 *  3）、使用SM2 A公钥加密密钥，得到密文Y
 *  4）、使用SM2 B私钥签名（消息明文P+加密密钥K），得到签名Z 5）、使用分隔符拼接X+Y+Z，得到最终密文
 * 4、服务端解密过程
 *  1）、使用SM2 A私钥解密密文Y，获取对称密钥K
 *  2）、使用SM4 密钥K解密密文Y，获取消息明文P
 *  3）、使用SM2 B公钥验证对（消息明文P+加密密钥K）和签名Z 进行验签，验签成功后消息明文为合法明文.
 * </pre>
 *
 * @author zwb
 */
public class PncCrypto {

	/**
	 * The Num 16.
	 */
	static final int NUM_16 = 16;

	/**
	 * 分隔符.
	 */
	public static final String SPLIT_CHAR = (char) (29) + "";

	/**
	 * 混淆规则 0：无混淆；1：首尾对换；2、奇偶对换，默认值：{@value}
	 */
	public static final int CONFUSE_STATUS = 2;

	/**
	 * 自定义加密版本：{@value}.
	 */
	public static final String VERSION = "1";

	/**
	 * 加密算法版本号：{@value}.
	 */
	public static final String V3 = "#30";

	/**
	 * 填充字节倍数：{@value}.
	 */
	public static final int SIZE_16 = 16;

	/**
	 * 此公私钥对用于加解密 sm4对称密钥.
	 */
	private String aPublicKeyX = "";

	/**
	 * The a public key Y.
	 */
	private String aPublicKeyY = "";

	/**
	 * The a private key.
	 */
	private String aPrivateKey = "";

	/**
	 * 此公私钥对用于 明文数据签名验签.
	 */
	private String bPrivateKey = "";

	/**
	 * The b public key X.
	 */
	private String bPublicKeyX = "";

	/**
	 * The b public key Y.
	 */
	private String bPublicKeyY = "";

	/**
	 * 创建客户端加密实例.
	 *
	 * @param aPublicKeyX 加密对称密钥的 A公钥X
	 * @param aPublicKeyY 加密对称密钥的 A公钥Y
	 * @param bPrivateKey 签名明文 B私钥
	 * @return pnc crypto
	 */
	public static PncCrypto buildClientInstance(String aPublicKeyX, String aPublicKeyY, String bPrivateKey) {
		PncCrypto crypto = new PncCrypto();
		crypto.aPublicKeyX = aPublicKeyX;
		crypto.aPublicKeyY = aPublicKeyY;
		crypto.bPrivateKey = bPrivateKey;
		return crypto;
	}

	/**
	 * 创建服务端解密实例.
	 *
	 * @param aPrivateKey 解密对称密钥的 A私钥
	 * @param bPublicKeyX 验签 B公钥X
	 * @param bPublicKeyY 验签 B公钥Y
	 * @return pnc crypto
	 */
	public static PncCrypto buildServerInstance(String aPrivateKey, String bPublicKeyX, String bPublicKeyY) {
		PncCrypto crypto = new PncCrypto();
		crypto.aPrivateKey = aPrivateKey;
		crypto.bPublicKeyX = bPublicKeyX;
		crypto.bPublicKeyY = bPublicKeyY;
		return crypto;
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
	 * 请求报文加密，自定义加密key.
	 *
	 * @param key   加密key，使用UTF-8字节解码
	 * @param plain 明文数据，使用UTF-8字节解码
	 * @return the string
	 */
	public String reqEncrypt(String key, String plain) {
		if (plain == null) {
			return "";
		}

		// 1、使用SM4加密数据
		String s1 = PncCrypto.varietySm4Encrypt(key, plain);

		// 2、使用SM2 A公钥加密对称密钥
		String s2 = SM2.build(this.aPublicKeyX, this.aPublicKeyY).encrypt(key);

		// 3、SM2 B私钥对数据进行签名
		// 待签名数据=（原文+密钥）
		String source = plain + key;
		String s3 = SM2.build(this.bPrivateKey).sign(source);

		StringBuilder output = new StringBuilder();
		// 5、组合拼接
		output.append(V3).append(s1).append(SPLIT_CHAR).append(s2).append(SPLIT_CHAR).append(s3);
		return output.toString();
	}

	/**
	 * 请求报文加密，随机生成加密key.
	 *
	 * @param plain 明文数据，使用UTF-8字节解码
	 * @return the string
	 */
	public String reqEncrypt(String plain) {
		// 生成随机对称密钥
		String key = SM4.generateKey();

		return reqEncrypt(key, plain);
	}

	/**
	 * 请求报文解密.
	 *
	 * @param cipher the cipher
	 * @return the string
	 */
	public String reqDecrypt(String cipher) {
		if (cipher == null || cipher.length() < 3) {
			throw new RuntimeException("message length less than 3");
		}
		cipher = cipher.substring(3);

		String[] cipherArray = cipher.split(SPLIT_CHAR);
		if (cipherArray.length != 3) {
			throw new RuntimeException("illegal message format");
		}
		// 消息密文
		String s1 = cipherArray[0];
		// 密钥密文
		String s2 = cipherArray[1];
		// 明文签名
		String s3 = cipherArray[2];

		// 1、SM2 A私钥 解密获取对称密钥key
		String key = SM2.build(this.aPrivateKey).decryptToString(s2);
		// 2、使用对称密钥解密获取原文
		String plain = PncCrypto.varietySm4Decrypt(key, s1);

		// 待验签数据（原文+密钥）
		String source = plain + key;
		// 3、对原文进行验签
		boolean result = SM2.build(this.bPublicKeyX, this.bPublicKeyY).verify(source, s3);
		if (!result) {
			throw new RuntimeException("verify sign error");
		}
		return plain;
	}

	/**
	 * 请求报文-对称加密.
	 *
	 * @param key  the key
	 * @param data the data
	 * @return byte [ ]
	 */
	public static byte[] varietySm4Encrypt(byte[] key, byte[] data) {
		byte[] m1 = m1(data);
		byte[] m2 = m2();
		byte[] m = m3(m1, m2);
		return SM4.encrypt(key, m);
	}

	/**
	 * 请求报文-对称加密，算法：SM4，经过混淆和填充，加密key及明文字符串使用UTF-8解码.
	 *
	 * @param key   加密key字符串
	 * @param plain 明文字符串数据
	 * @return the string
	 */
	public static String varietySm4Encrypt(String key, String plain) {
		byte[] data = plain.getBytes(StandardCharsets.UTF_8);
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		return HexUtil.byte2HexStr(varietySm4Encrypt(keyBytes, data));
	}

	/**
	 * 响应报文加密.
	 *
	 * @param key   the key
	 * @param plain the plain
	 * @return the string
	 */
	public static String respEncrypt(String key, String plain) {
		try {
			Integer confuseRule = CONFUSE_STATUS;

			// 版本
			StringBuilder controlCode = new StringBuilder();
			controlCode.append(String.format("%X", Integer.valueOf(VERSION)));

			String encryptData = SM4.encrypt(key, plain);

			// 填充
			int dataLen = encryptData.length(); // 原长度
			// 填充约束为 （2 ~ 数据体长度） 之间的随机数
			String fillPos = StringUtil.randomInt(2, 2, 20);

			controlCode.append(fillPos);
			int fillPosNum = Integer.parseInt(fillPos, NUM_16);
			encryptData += StringUtil.randomString16(fillPosNum - (fillPosNum > 0 ? dataLen % fillPosNum : 0));// 填充

			// 混淆
			if (dataLen == 0) {
				confuseRule = 0; // 数据体为空，无混淆
			}

			// 混淆长度为 （2 ~ 数据体长度 ） 之间的随机数
			String confuseLen = StringUtil.randomInt(2, 2, encryptData.length());
			int confuseLenNum = Integer.parseInt(confuseLen, NUM_16);

			// 混淆起始值为 （0 ~ 数据体长度 ） 之间的随机数
			String confuseStartPos = StringUtil.randomInt(2, 0, encryptData.length() - confuseLenNum);
			Integer confuseStartPosNum = Integer.parseInt(confuseStartPos, NUM_16);

			controlCode.append(confuseStartPos);
			controlCode.append(confuseLen);
			controlCode.append(String.format("%X", confuseRule));

			encryptData = getConfuse(confuseRule, encryptData, confuseLenNum, confuseStartPosNum);

			controlCode.append(String.format("%06X", dataLen));

			// 组合控制码与数据体
			controlCode.append(encryptData);

			return controlCode.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the confuse.
	 *
	 * @param confuseRule        the confuse rule
	 * @param encryptData        the encrypt data
	 * @param confuseLenNum      the confuse len num
	 * @param confuseStartPosNum the confuse start pos num
	 * @return the confuse
	 */
	private static String getConfuse(Integer confuseRule, String encryptData, Integer confuseLenNum,
			Integer confuseStartPosNum) {
		StringBuilder confuseData = new StringBuilder();
		String confuseStr = encryptData.substring(confuseStartPosNum, confuseStartPosNum + confuseLenNum);
		int confuseStrLen = confuseStr.length(); // 混淆内容长度

		confuseData.append(encryptData.substring(0, confuseStartPosNum));// 追加未混淆部分

		switch (confuseRule) {
		case 1: // 首尾对换
			confuseData.append(confuseStr.charAt(confuseStrLen - 1));
			confuseData.append(confuseStr.substring(1, confuseStrLen - 1));
			confuseData.append(confuseStr.charAt(0));
			break;
		case 2: // 基偶对换
			for (int j = 2; j <= confuseStrLen; j++) {
				if (j % 2 == 0) {
					confuseData.append(confuseStr.charAt(j - 1));
					confuseData.append(confuseStr.charAt(j - 2));
				}
			}
			if (confuseStrLen % 2 != 0 && confuseStrLen > 0) {
				confuseData.append(confuseStr.charAt(confuseStrLen - 1));
			}
			break;
		default:
			break;
		}

		if (0 != confuseRule) {
			confuseData.append(encryptData.substring(confuseStartPosNum + confuseLenNum));
			encryptData = confuseData.toString();
		}
		return encryptData;
	}

	/**
	 * 响应报文加密.
	 *
	 * @param key    the key
	 * @param cipher 16进制密文数据
	 * @return byte [ ]
	 */
	public static String respDecrypt(String key, String cipher) {
		try {
			String encryptData = cipher.substring(14); // 取得数据体

			// 拆分控制码
			Integer confuseStartPosNum = Integer.parseInt(cipher.substring(3, 5), NUM_16);// 混淆起始点
			Integer confuseLenNum = Integer.parseInt(cipher.substring(5, 7), NUM_16); // 混淆长度
			Integer confuseRule = Integer.parseInt(cipher.substring(7, 8), NUM_16); // 混淆规则
			int originalLen = Integer.parseInt(cipher.substring(8, 14), NUM_16); // 数据原长度

			// 反混淆
			encryptData = getConfuse(confuseRule, encryptData, confuseLenNum, confuseStartPosNum);

			// 反填充
			encryptData = encryptData.substring(0, originalLen);

			// sm4解密
			return SM4.decrypt(key, encryptData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 请求报文-对称算法解密.
	 *
	 * @param key    the key
	 * @param cipher 16进制密文数据
	 * @return byte [ ]
	 */
	public static String varietySm4Decrypt(String key, String cipher) {
		byte[] data = HexUtil.hexStr2Bytes(cipher);
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

		byte[] plain = varietySm4Decrypt(keyBytes, data);
		return new String(plain, StandardCharsets.UTF_8);
	}

	/**
	 * 请求报文-对称算法解密.
	 *
	 * @param key  the key
	 * @param data the data
	 * @return byte [ ]
	 */
	public static byte[] varietySm4Decrypt(byte[] key, byte[] data) {
		byte[] out = SM4.decrypt(key, data);
		return unMix(out);
	}

	/**
	 * 去除混淆、填充.
	 *
	 * @param data the data
	 * @return byte [ ]
	 */
	public static byte[] unMix(byte[] data) {
		int length = data.length;
		int lenM1 = length - SIZE_16;
		byte[] m3 = new byte[lenM1];
		byte[] m2 = new byte[SIZE_16];
		System.arraycopy(data, lenM1, m2, 0, SIZE_16);
		System.arraycopy(data, 0, m3, 0, lenM1);
		byte[] m1 = xor(m3, m2);

		int trueLength = 0;
		for (int i = SIZE_16; i > 0; i--) {
			int flag = m1[lenM1 - i];
			if (flag == 29) {
				trueLength = lenM1 - i;
				break;
			}
		}
		byte[] codes = new byte[trueLength];
		System.arraycopy(m1, 0, codes, 0, trueLength);
		return codes;
	}

	/**
	 * M1.
	 *
	 * @param datas the datas
	 * @return the byte[]
	 */
	private static byte[] m1(byte[] datas) {
		int length = datas.length;
		int size = length % SIZE_16;
		size = SIZE_16 - size;
		byte[] tmp = new byte[size];
		tmp[0] = 29;
		Random random = new Random();
		for (int i = 1; i < size; i++) {
			tmp[i] = (byte) (random.nextInt(150) % 150 + 30);
		}
		byte[] out = new byte[size + length];
		System.arraycopy(datas, 0, out, 0, length);
		System.arraycopy(tmp, 0, out, length, size);
		return out;
	}

	/**
	 * M2.
	 *
	 * @return the byte[]
	 */
	private static byte[] m2() {
		Random random = new Random();
		byte[] tmp = new byte[SIZE_16];
		for (int i = 0; i < SIZE_16; i++) {
			tmp[i] = (byte) (random.nextInt(255) % 255);
		}
		return tmp;
	}

	/**
	 * M3.
	 *
	 * @param m1 the m 1
	 * @param m2 the m 2
	 * @return the byte[]
	 */
	private static byte[] m3(byte[] m1, byte[] m2) {
		int lenM1 = m1.length;
		int lenM2 = m2.length;
		byte[] out = new byte[lenM1 + lenM2];
		byte[] m3 = xor(m1, m2);
		System.arraycopy(m3, 0, out, 0, lenM1);
		System.arraycopy(m2, 0, out, lenM1, lenM2);
		return out;
	}

	/**
	 * Xor.
	 *
	 * @param m1 the m 1
	 * @param m2 the m 2
	 * @return the byte[]
	 */
	private static byte[] xor(byte[] m1, byte[] m2) {
		int lenM1 = m1.length;
		byte[] m3 = new byte[lenM1];
		int s1 = lenM1 / SIZE_16;
		for (int a = 0; a < s1; a++) {
			int index = a * SIZE_16;
			for (int i = 0; i < SIZE_16; i++) {
				m3[index + i] = (byte) (m1[index + i] ^ m2[i]);
			}
		}
		return m3;
	}
}
