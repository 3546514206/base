package com.gqz.thread;

public class TDownloader extends Thread {
    private String url;
    private String name;

    public TDownloader(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
        TDownloader td1 = new TDownloader("https://img-blog.csdnimg.cn/20190624141811747.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2dhbnF1YW56aG9uZw==,size_16,color_FFFFFF,t_70", "1.jpg");
        TDownloader td2 = new TDownloader("https://img-blog.csdnimg.cn/20190624141951760.png", "2.jpg");
        TDownloader td3 = new TDownloader("https://img-blog.csdnimg.cn/20190624144413917.png", "3.jpg");

        td1.start();
        td2.start();
        td3.start();
    }

    @Override
    public void run() {
        WebDownloader wd = new WebDownloader();
        wd.download(url, name);
        System.out.println(name);
    }
}
