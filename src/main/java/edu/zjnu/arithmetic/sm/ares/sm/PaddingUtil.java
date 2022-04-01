/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:PaddingUtil.java</p>
 * <p>创建时间	:2021-10-19 15:13:27 </p>
 */

package edu.zjnu.arithmetic.sm.ares.sm;

/**
 * 填充工具包.
 *
 * @author  zwb
 */
public class PaddingUtil {

	/**
	 * The Constant BLOCK_SIZE.
	 */
	public static final int BLOCK_SIZE = 16;

	/**
	 * PKCS7填充.
	 *
	 * @param data the data
	 * @return the byte [ ]
	 */
	public static byte[] pkcs7(byte[] data) {
		int dataSize = data.length;
		int mod = BLOCK_SIZE - dataSize % BLOCK_SIZE;
		byte[] out = new byte[dataSize + mod];
		System.arraycopy(data, 0, out, 0, data.length);
		for (int i = dataSize; i < out.length; i++) {
			out[i] = (byte) mod;
		}
		return out;
	}

	/**
	 * PKCS7反填充.
	 *
	 * @param data the data
	 * @return the byte[]
	 */
	public static byte[] unPkcs7(byte[] data) {
		int size = data.length;
		// 获取填充长度
		int p = data[size - 1];

		int outLen = size - p;
		byte[] outdata = new byte[outLen];
		System.arraycopy(data, 0, outdata, 0, outLen);
		return outdata;
	}
}
