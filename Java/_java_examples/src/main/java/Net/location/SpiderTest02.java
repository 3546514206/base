package com.gqz.location;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 模拟网络爬虫
 *
 * @author ganquanzhong
 * @ClassName: SpiderTest01
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月12日 下午3:39:28
 */
public class SpiderTest02 {
	public static void main(String[] args) throws Exception {
		//获取URL
//		URL url =new URL("http://ganquanzhong.top/mynews/Front?op=displayNews&newsId=189");
		URL url = new URL("https://www.dianping.com");
		//下载资源
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
		InputStream is = conn.getInputStream();

		//字节转换为字符
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		File file = new File("aaaa.txt");
//		new File("C:/users/ganquanzhong/desktop/a.html").mkdirs();
//		new File("C:/users/ganquanzhong/desktop/a.txt").createNewFile();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		String msg = null;
		while ((msg = br.readLine()) != null) {
			bw.write(msg);
		}
		bw.close();
		br.close();
		//处理数据。。。
	}
}
