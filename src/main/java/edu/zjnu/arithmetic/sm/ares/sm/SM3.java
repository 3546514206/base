/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:SM3.java</p>
 * <p>创建时间	:2021-10-19 15:14:02 </p>
 */

package edu.zjnu.arithmetic.sm.ares.sm;

import java.nio.charset.StandardCharsets;
import java.security.Security;

import org.bouncycastle.crypto.digests.SM3Digest;

/**
 * 国密SM3摘要算法.
 *
 * @author zwb
 */
public class SM3 {

    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        }
    }

    /**
     * hash摘要.
     *
     * @param source 原文字节数组
     * @return 16进制字符串 string
     */
    public static String hash(byte[] source) {
        return hash(source, null);
    }

    /**
     * hash摘要，盐值拼接在末尾计算.
     *
     * @param source 原文字节数组
     * @param slat   盐值字节数组
     * @return 16进制字符串 string
     */
    public static String hash(byte[] source, byte[] slat) {
        if (source == null || source.length == 0) {
            return "";
        }
        SM3Digest sm3 = new SM3Digest();
        sm3.update(source, 0, source.length);
        if (slat != null && source.length > 0) {
            sm3.update(slat, 0, slat.length);
        }
        byte[] result = new byte[sm3.getDigestSize()];
        sm3.doFinal(result, 0);
        return HexUtil.byte2HexStr(result);
    }

    /**
     * hash摘要，盐值拼接在末尾计算.
     *
     * @param source 原文字符串，使用UTF-8字符集转换为字节数字
     * @return 16进制字符串 string
     */
    public static String hash(String source) {
        return hash(source, null);
    }

    /**
     * 添加盐值并生成摘要.
     *
     * @param source 原文字符串，使用UTF-8字符集转换为字节数字
     * @param slat   盐值字符串，使用UTF-8字符集转换为字节数字
     * @return 16进制字符串 string
     */
    public static String hash(String source, String slat) {
        if (source == null || source.length() == 0) {
            return "";
        }
        byte[] data = source.getBytes(StandardCharsets.UTF_8);
        if (slat != null && slat.length() > 0) {
            byte[] slatBytes = slat.getBytes(StandardCharsets.UTF_8);
            return hash(data, slatBytes);
        }
        return hash(data);
    }
}
