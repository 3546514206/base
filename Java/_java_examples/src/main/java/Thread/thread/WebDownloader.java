package com.gqz.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author ganquanzhong
 * @ClassName: WebDownloader
 * @Description: 网络文件下载
 * @date 2019年7月8日 上午10:27:25
 */
public class WebDownloader {

	public void download(String url, String name) {
		try {
			FileUtils.copyURLToFile(new URL(url), new File(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("不合法的URL");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("下载失败！");
		}
	}
}
