/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm.benchmark</p>
 * <p>文件名称	:PncCryptoBenchmarkTest.java</p>
 * <p>创建时间	:2021-10-19 15:36:45 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test;

import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import edu.zjnu.arithmetic.sm.ares.sm.PncCrypto;

/**
 * The type Pnc crypto benchmark test.
 */
public class PncCryptoBenchmarkTest {

	/**
	 * 此公私钥对用于加解密 sm4对称密钥.
	 */
	String aPrivateKey = "849f592733edcac574855c25c01ea2ee71c173bc773647c23d4d195a78ea5a61";
	/**
	 * The A public key x.
	 */
	String aPublicKeyX = "0d5fd700cce099087ea9e8b0887b0a5c3e08ec36a532cdc7ec4270c7d2f50ca1";
	/**
	 * The A public key y.
	 */
	String aPublicKeyY = "62f3c6577eac0baaad2fb65f09029d6e2c067bdf5f266c6de4c9bd1c16d5d035";

	/**
	 * 此公私钥对用于 明文数据签名验签
	 */
	String bPrivateKey = "C97F7901E6F3C3A033C1392DD01A5D7A6D46DC61EE5BBE87B7DFCA606EC51E34";
	/**
	 * The B public key x.
	 */
	String bPublicKeyX = "3E3A5AF830DE527BB16877077413D3576586C9B8E86CD3AA9447E0BBB90F4767";
	/**
	 * The B public key y.
	 */
	String bPublicKeyY = "886CBA348AAA79F57C1722DB085858EEA86004887F3CCF6051833AAD5CADE904";

	/**
	 * 生成加密实例对象
	 */
	PncCrypto clientInstance = PncCrypto.buildClientInstance(aPublicKeyX, aPublicKeyY, bPrivateKey);

	/**
	 * 生成解密实例对象
	 */
	PncCrypto serverInstance = PncCrypto.buildServerInstance(aPrivateKey, bPublicKeyX, bPublicKeyY);

	/**
	 * The Encrypt 128.
	 */
	String encrypt128 = clientInstance.reqEncrypt(RandomData.string128);
	/**
	 * The Encrypt 256.
	 */
	String encrypt256 = clientInstance.reqEncrypt(RandomData.string256);
	/**
	 * The Encrypt 512.
	 */
	String encrypt512 = clientInstance.reqEncrypt(RandomData.string512);
	/**
	 * The Encrypt 1024.
	 */
	String encrypt1024 = clientInstance.reqEncrypt(RandomData.string1024);
	/**
	 * The Encrypt 2 k.
	 */
	String encrypt2k = clientInstance.reqEncrypt(RandomData.string2k);
	/**
	 * The Encrypt 5 k.
	 */
	String encrypt5k = clientInstance.reqEncrypt(RandomData.string5k);
	/**
	 * The Encrypt 10 k.
	 */
	String encrypt10k = clientInstance.reqEncrypt(RandomData.string10k);
	/**
	 * The Encrypt 20 k.
	 */
	String encrypt20k = clientInstance.reqEncrypt(RandomData.string20k);
	/**
	 * The Encrypt 50 k.
	 */
	String encrypt50k = clientInstance.reqEncrypt(RandomData.string50k);
	/**
	 * The Encrypt 100 k.
	 */
	String encrypt100k = clientInstance.reqEncrypt(RandomData.string100k);
	/**
	 * The Encrypt 200 k.
	 */
	String encrypt200k = clientInstance.reqEncrypt(RandomData.string200k);
	/**
	 * The Encrypt 500 k.
	 */
	String encrypt500k = clientInstance.reqEncrypt(RandomData.string500k);
	/**
	 * The Encrypt 1024 k.
	 */
	String encrypt1024k = clientInstance.reqEncrypt(RandomData.string1024k);

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_128_Test() {
		// 原文
		String source = RandomData.string128;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_256_Test() {
		// 原文
		String source = RandomData.string256;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_512_Test() {
		// 原文
		String source = RandomData.string512;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_1024_Test() {
		// 原文
		String source = RandomData.string1024;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_2k_Test() {
		// 原文
		String source = RandomData.string2k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_5k_Test() {
		// 原文
		String source = RandomData.string5k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_10k_Test() {
		// 原文
		String source = RandomData.string10k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_20k_Test() {
		// 原文
		String source = RandomData.string20k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_50k_Test() {
		// 原文
		String source = RandomData.string50k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 60000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_100k_Test() {
		// 原文
		String source = RandomData.string100k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_200k_Test() {
		// 原文
		String source = RandomData.string200k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_500k_Test() {
		// 原文
		String source = RandomData.string500k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void encrypt_1024k_Test() {
		// 原文
		String source = RandomData.string1024k;
		clientInstance.reqEncrypt(source);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_128_Test() {
		serverInstance.reqDecrypt(encrypt128);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_256_Test() {
		serverInstance.reqDecrypt(encrypt256);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_512_Test() {
		serverInstance.reqDecrypt(encrypt512);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_1024_Test() {
		serverInstance.reqDecrypt(encrypt1024);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_2k_Test() {
		serverInstance.reqDecrypt(encrypt2k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_5k_Test() {
		serverInstance.reqDecrypt(encrypt5k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_10k_Test() {
		serverInstance.reqDecrypt(encrypt10k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_20k_Test() {
		serverInstance.reqDecrypt(encrypt20k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_50k_Test() {
		serverInstance.reqDecrypt(encrypt50k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 60000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_100k_Test() {
		serverInstance.reqDecrypt(encrypt100k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_200k_Test() {
		serverInstance.reqDecrypt(encrypt200k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_500k_Test() {
		serverInstance.reqDecrypt(encrypt500k);
	}

	/**
	 * 10个线程运行。 准备时间：1000ms 运行时间: 20000ms
	 */
	@JunitPerfConfig(threads = 10, warmUp = 1000, duration = 20000)
	public void decrypt_1024k_Test() {
		serverInstance.reqDecrypt(encrypt1024k);
	}
}
