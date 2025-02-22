package com.gqz.location;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 模拟网络爬虫
 *
 * @author ganquanzhong
 * @ClassName: SpiderTest01
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月12日 下午3:39:28
 */
public class SpiderTest01 {
	public static void main(String[] args) throws Exception {
		//获取URL
//		URL url =new URL("http://ganquanzhong.top/mynews/Front?op=displayNews&newsId=189");
		URL url = new URL("http://www.dianping.com");
		//下载资源
		URLConnection conn = url.openConnection();
		conn.connect();
		InputStream is = conn.getInputStream();

		//字节转换为字符
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		File file = new File("C:/users/ganquanzhong/desktop/a1.txt");
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
