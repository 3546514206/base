/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm.benchmark</p>
 * <p>文件名称	:SM2BenchmarkTest.java</p>
 * <p>创建时间	:2021-10-19 15:37:00 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import edu.zjnu.arithmetic.sm.ares.sm.SM2;


/**
 * The Class SM2BenchmarkTest.
 */
public class SM2BenchmarkTest {

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encryptTest() throws InterruptedException {
		// 使用默认公私钥对创建实例
		SM2 sm2 = SM2.build();
		// 加密原文
		String source = "我是中国人abc123";
		// 加密数据
		sm2.encrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decryptTest() throws InterruptedException {
		// 使用默认公私钥对解密数据
		SM2 sm2 = SM2.build();
		// 加密数据
		String cipherData = "96F798BB20275B7A59A9EE97A7BA3DF96B7205EBE7083715B72FA23EEA1C85B3ADD60B8E49B6422A870EA35C22B20E3FBD4E3C81EE97F3809F313863FD70FE2F21226FEBE451C7C2DBBCABA5FE7FB931B76112013A2FB4C1E70F9761B6C7C18C26F27E921BC6E890502C3B36E016F096CBFD2E3CB3";
		// 解密数据
		sm2.decrypt(cipherData);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void signTest() {
		// 使用默认私钥签名数据
		SM2 sm2 = SM2.build();
		// 待签名数据
		String source = "我是中国人abc123";
		// 数据签名
		sm2.sign(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 60000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void verifyTest() {
		// 使用默认公钥验证签名
		SM2 sm2 = SM2.build();
		// 原文数据
		String source = "我是中国人abc123";
		// 签名数据
		String sign = "875F54BA4591B87C02DECAB7ABF876EABA43C0DEA40CC3777D067658F8693E0F28A836838032365F155B5C6D17C5C54D15847C0AA1E08B1459614FC2CEE63FE7";
		// 验证结果
		sm2.verify(source, sign);
	}
}
