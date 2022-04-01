/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 *
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:SM3Test.java</p>
 * <p>创建时间	:2021-10-19 15:56:40 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test.java.cn.com.yitong.util.sm;

import edu.zjnu.arithmetic.sm.ares.sm.SM3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 国密SM3摘要算法单元测试.
 *
 * @author zwb
 */
public class SM3Test {

    /**
     * hash测试.
     */
    @Test
    public void testHash() {
        // 原文
        String source = "我是中国人abc123";
        // 生成摘要
        String actual = SM3.hash(source);
        // 期待值
        String expected = "DB1303FA9AFF763919324C48C8152C5F4648995ED431920A0DDD6B8C65D73885";
        Assertions.assertEquals(expected, actual);
    }

    /**
     * 加盐hash测试.
     */
    @Test
    public void testHashWithSlat() {
        // 原文
        String source = "我是中国人abc123";
        // 盐值
        String slat = "HNAN7Y";
        // 添加盐值并生成摘要
        String expected = "2F01508C1C4E093202342797786FD6A4122AB56C2A8304069612242BBA9B44A9";
        Assertions.assertEquals(expected, SM3.hash(source, slat));

        // 以下2种方式结果相同
        Assertions.assertEquals(expected, SM3.hash(source, slat));
        Assertions.assertEquals(SM3.hash(source + slat), SM3.hash(source, slat));
    }
}