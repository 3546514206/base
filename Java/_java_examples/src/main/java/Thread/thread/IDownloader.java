package com.gqz.thread;

/**
 * 多线程，尽量使用接口实现
 *
 * @author ganquanzhong
 * @ClassName: IDownloader
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 上午10:50:45
 */
public class IDownloader implements Runnable {
    private String url;
    private String name;

    public IDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
        IDownloader td1 = new IDownloader("https://img-blog.csdnimg.cn/20190624141811747.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70", "1.jpg");
        IDownloader td2 = new IDownloader("https://img-blog.csdnimg.cn/20190624141951760.png", "2.jpg");
        IDownloader td3 = new IDownloader("https://img-blog.csdnimg.cn/20190624144413917.png", "3.jpg");

        new Thread(td1).start();
        new Thread(td2).start();
        new Thread(td3).start();
    }

    @Override
    public void run() {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
    }
}
