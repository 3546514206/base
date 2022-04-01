/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm.benchmark</p>
 * <p>文件名称	:SM3BenchmarkTest.java</p>
 * <p>创建时间	:2021-10-19 15:37:09 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test;


import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import edu.zjnu.arithmetic.sm.ares.sm.SM3;
import org.junit.jupiter.api.DisplayName;

/**
 * The Class SM3BenchmarkTest.
 */
public class SM3BenchmarkTest {

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	@DisplayName("128字节测试")
	public void hash_128_Test() {
		// 原文
		String source = RandomData.string128;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_256_Test() {
		// 原文
		String source = RandomData.string256;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_512_Test() {
		// 原文
		String source = RandomData.string512;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_1024_Test() {
		// 原文
		String source = RandomData.string1024;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_2k_Test() {
		// 原文
		String source = RandomData.string2k;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_5k_Test() {
		// 原文
		String source = RandomData.string5k;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_10k_Test() {
		// 原文
		String source = RandomData.string10k;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_20k_Test() {
		// 原文
		String source = RandomData.string20k;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_50k_Test() {
		// 原文
		String source = RandomData.string50k;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_100k_Test() {
		// 原文
		String source = RandomData.string100k;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_200k_Test() {
		// 原文
		String source = RandomData.string200k;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_500k_Test() {
		// 原文
		String source = RandomData.string500k;
		// 生成摘要
		SM3.hash(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms.
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void hash_1024k_Test() {
		// 原文
		String source = RandomData.string1024k;
		// 生成摘要
		SM3.hash(source);
	}
}
