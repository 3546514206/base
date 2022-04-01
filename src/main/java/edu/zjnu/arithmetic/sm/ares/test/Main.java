/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 *
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm.benchmark</p>
 * <p>文件名称	:Main.java</p>
 * <p>创建时间	:2021-10-19 15:36:35 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test;


import edu.zjnu.arithmetic.sm.ares.sm.SM2;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Class Main.
 * @author SetsunaYang
 */
public class Main {

	/**
	 * 异步线程池，核心线程大小
	 */
	private static final int POOL_CORE_SIZE = 10;

	/**
	 * The pool max size. 异步线程池，最大线程
	 */
	private static final int POOL_MAX_SIZE = 100;

	/**
	 * The pool keep alive time 线程空闲多少秒后自动结束
	 */
	private static final int poolKeepAliveTime = 60;

	/**
	 * The pool 等待队列大小
	 */
	private static final int poolQueueSize = 100;

	static boolean isBreak;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(poolQueueSize);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				POOL_CORE_SIZE,
				POOL_MAX_SIZE,
				poolKeepAliveTime,
				TimeUnit.SECONDS,
				workQueue);

		final int count = 10000;
		final CountDownLatch latch = new CountDownLatch(POOL_MAX_SIZE);

		final AtomicInteger resultCount = new AtomicInteger();
		long start = System.currentTimeMillis();
		for (int i = 0; i < POOL_MAX_SIZE; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < count; j++) {
						if (isBreak) {
							break;
						}
						// 使用默认公钥验证签名
						SM2 sm2 = SM2.build();
						// 原文数据
						String source = "我是中国人abc123";
						// 签名数据
						String sign = "875F54BA4591B87C02DECAB7ABF876EABA43C0DEA40CC3777D067658F8693E0F28A836838032365F155B5C6D17C5C54D15847C0AA1E08B1459614FC2CEE63FE7";
						// 验证结果
						sm2.verify(source, sign);
						resultCount.incrementAndGet();
					}
					latch.countDown();
				}
			});
		}

		try {
			latch.await(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			isBreak = true;
		}
		long end = System.currentTimeMillis();
		long diff = end - start;
		double tps = (resultCount.get()) * 1.0 / (diff / 1000);
		System.out.println("tps:" + tps + " time:" + diff);
		System.exit(0);
	}
}
