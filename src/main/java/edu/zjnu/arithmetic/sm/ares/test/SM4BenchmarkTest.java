/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm.benchmark</p>
 * <p>文件名称	:SM4BenchmarkTest.java</p>
 * <p>创建时间	:2021-10-19 15:37:15 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import edu.zjnu.arithmetic.sm.ares.sm.SM4;

/**
 * The type Sm 4 benchmark test.
 */
public class SM4BenchmarkTest {
	/**
	 * The Key.
	 */
	String key = "1234567890abcdef";
	/**
	 * The Encrypt 128.
	 */
	String encrypt128 = SM4.encrypt(key, RandomData.string128);
	/**
	 * The Encrypt 256.
	 */
	String encrypt256 = SM4.encrypt(key, RandomData.string256);
	/**
	 * The Encrypt 512.
	 */
	String encrypt512 = SM4.encrypt(key, RandomData.string512);
	/**
	 * The Encrypt 1024.
	 */
	String encrypt1024 = SM4.encrypt(key, RandomData.string1024);

	/**
	 * The Encrypt 2 k.
	 */
	String encrypt2k = SM4.encrypt(key, RandomData.string2k);

	/**
	 * The Encrypt 5 k.
	 */
	String encrypt5k = SM4.encrypt(key, RandomData.string5k);
	/**
	 * The Encrypt 10 k.
	 */
	String encrypt10k = SM4.encrypt(key, RandomData.string10k);
	/**
	 * The Encrypt 20 k.
	 */
	String encrypt20k = SM4.encrypt(key, RandomData.string20k);
	/**
	 * The Encrypt 50 k.
	 */
	String encrypt50k = SM4.encrypt(key, RandomData.string50k);
	/**
	 * The Encrypt 100 k.
	 */
	String encrypt100k = SM4.encrypt(key, RandomData.string100k);
	/**
	 * The Encrypt 200 k.
	 */
	String encrypt200k = SM4.encrypt(key, RandomData.string200k);
	/**
	 * The Encrypt 500 k.
	 */
	String encrypt500k = SM4.encrypt(key, RandomData.string500k);
	/**
	 * The Encrypt 1024 k.
	 */
	String encrypt1024k = SM4.encrypt(key, RandomData.string1024k);

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_128_Test() {
		// 原文
		String source = RandomData.string128;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_256_Test() {
		// 原文
		String source = RandomData.string256;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_512_Test() {
		// 原文
		String source = RandomData.string512;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_1024_Test() {
		// 原文
		String source = RandomData.string1024;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_2k_Test() {
		// 原文
		String source = RandomData.string2k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_5k_Test() {
		// 原文
		String source = RandomData.string5k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_10k_Test() {
		// 原文
		String source = RandomData.string10k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_20k_Test() {
		// 原文
		String source = RandomData.string20k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_50k_Test() {
		// 原文
		String source = RandomData.string50k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 60000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_100k_Test() {
		// 原文
		String source = RandomData.string100k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_200k_Test() {
		// 原文
		String source = RandomData.string200k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_500k_Test() {
		// 原文
		String source = RandomData.string500k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_1024k_Test() {
		// 原文
		String source = RandomData.string1024k;
		String key = "1234567890abcdef";
		SM4.encrypt(key, source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_128_Test() {
		SM4.decrypt(key, encrypt128);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_256_Test() {
		SM4.decrypt(key, encrypt256);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_512_Test() {
		SM4.decrypt(key, encrypt512);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_1024_Test() {
		SM4.decrypt(key, encrypt1024);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_2k_Test() {
		SM4.decrypt(key, encrypt2k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_5k_Test() {
		SM4.decrypt(key, encrypt5k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_10k_Test() {
		SM4.decrypt(key, encrypt10k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_20k_Test() {
		SM4.decrypt(key, encrypt20k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_50k_Test() {
		SM4.decrypt(key, encrypt50k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 60000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_100k_Test() {
		SM4.decrypt(key, encrypt100k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_200k_Test() {
		SM4.decrypt(key, encrypt200k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_500k_Test() {
		SM4.decrypt(key, encrypt500k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 *
	 * @throws InterruptedException if any
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_1024k_Test() {
		SM4.decrypt(key, encrypt1024k);
	}
}
