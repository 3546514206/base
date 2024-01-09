package com.gqz.thread;

import java.util.concurrent.*;

/**
 * 多线程，尽量使用接口实现
 *
 * @author ganquanzhong
 * @ClassName: IDownloader
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 上午10:50:45
 */
public class CDownloader implements Callable<Boolean> {
	private String url;
	private String name;

	public CDownloader(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CDownloader cd1 = new CDownloader("https://img-blog.csdnimg.cn/20190624141811747.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70", "1.jpg");
		CDownloader cd2 = new CDownloader("https://img-blog.csdnimg.cn/20190624141951760.png", "2.jpg");
		CDownloader cd3 = new CDownloader("https://img-blog.csdnimg.cn/20190624144413917.png", "3.jpg");

		//创建执行服务 线程池
		ExecutorService ser = Executors.newFixedThreadPool(3);

		//执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
		//FutureTask<Boolean> result1 = new FutureTask<Boolean>(td1);

		//提交执行：
		Future<Boolean> result1 = ser.submit(cd1);
		Future<Boolean> result2 = ser.submit(cd2);
		Future<Boolean> result3 = ser.submit(cd3);

		//获取结果
		Boolean r1 = result1.get();
		Boolean r2 = result2.get();
		Boolean r3 = result2.get();

		System.out.println(r3);

		//关闭服务
		ser.shutdown();
	}

	public Boolean call() throws Exception {
		WebDownloader wd = new WebDownloader();
		wd.download(url, name);
		System.out.println(name);
		return true;
	}
}
