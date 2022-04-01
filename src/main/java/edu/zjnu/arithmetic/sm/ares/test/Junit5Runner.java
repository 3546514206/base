/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm.benchmark</p>
 * <p>文件名称	:Junit5Runner.java</p>
 * <p>创建时间	:2021-10-19 15:36:28 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

/**
 * The Class Junit5Runner.
 */
public class Junit5Runner {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(selectClass(SM2BenchmarkTest.class), selectClass(SM3BenchmarkTest.class),
						selectClass(SM4BenchmarkTest.class), selectClass(PncCryptoBenchmarkTest.class))
				.selectors(selectClass(PncCryptoBenchmarkTest.class))
				.build();

		try (LauncherSession session = LauncherFactory.openSession()) {
			Launcher launcher = session.getLauncher();
			launcher.execute(request);
		}
	}

}
