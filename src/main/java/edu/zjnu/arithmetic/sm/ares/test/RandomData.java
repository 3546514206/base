/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm.benchmark</p>
 * <p>文件名称	:RandomData.java</p>
 * <p>创建时间	:2021-10-19 15:36:53 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test;


import edu.zjnu.arithmetic.sm.ares.sm.StringUtil;

/**
 * The type Random data.
 */
public class RandomData {

    /**
     * The constant string128.
     */
    public static String string128 = StringUtil.randomString16(128);
    /**
     * The constant string256.
     */
    public static String string256 = StringUtil.randomString16(256);
    /**
     * The constant string512.
     */
    public static String string512 = StringUtil.randomString16(512);
    /**
     * The constant string1024.
     */
    public static String string1024 = StringUtil.randomString16(1024);

    /**
     * The constant string2k.
     */
    public static String string2k = StringUtil.randomString16(1024 * 2);
    
    /**
     * The constant string5k.
     */
    public static String string5k = StringUtil.randomString16(1024 * 5);
    /**
     * The constant string10k.
     */
    public static String string10k = StringUtil.randomString16(1024 * 10);
    /**
     * The constant string20k.
     */
    public static String string20k = StringUtil.randomString16(1024 * 20);
    /**
     * The constant string50k.
     */
    public static String string50k = StringUtil.randomString16(1024 * 50);
    /**
     * The constant string100k.
     */
    public static String string100k = StringUtil.randomString16(1024 * 100);
    /**
     * The constant string200k.
     */
    public static String string200k = StringUtil.randomString16(1024 * 200);
    /**
     * The constant string500k.
     */
    public static String string500k = StringUtil.randomString16(1024 * 500);
    /**
     * The constant string1024k.
     */
    public static String string1024k = StringUtil.randomString16(1024 * 1024);
}
