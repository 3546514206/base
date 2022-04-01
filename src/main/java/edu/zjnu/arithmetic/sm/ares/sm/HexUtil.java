/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:HexUtil.java</p>
 * <p>创建时间	:2021-10-19 15:10:01 </p>
 */

package edu.zjnu.arithmetic.sm.ares.sm;

import org.bouncycastle.util.encoders.Hex;

/**
 * 16进制转换工具.
 *
 * @author zwb
 */
public class HexUtil {

	/**
	 * bytes转换成十六进制字符串.
	 *
	 * @param b the b
	 * @return the string
	 */
	public static String byte2HexStr(byte[] b) {
		return new String(Hex.encode(b)).toUpperCase();
	}

	/**
	 * bytes转换成十六进制字符串.
	 *
	 * @param src the src
	 * @return the byte[]
	 */
	public static byte[] hexStr2Bytes(String src) {
		return Hex.decode(src);
	}
}
