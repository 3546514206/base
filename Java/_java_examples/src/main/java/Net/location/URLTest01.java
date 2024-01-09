package com.gqz.location;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL:统一资源定位器 ，互联网三大基石之一 （http，html）区分资源
 * 1、协议
 * 2、域名，计算机
 * 3、端口：80
 * 4、请求资源
 *
 * @author ganquanzhong
 * @ClassName: URLTest01
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月12日 下午3:23:31
 */
public class URLTest01 {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://ganquanzhong.top:80/mynews/Front?op=displayNews&newsId=189#title");
        //获取四个值
        System.out.println("协议：" + url.getProtocol());
        System.out.println("域名/ip：" + url.getHost());
        System.out.println("端口：" + url.getPort());
        System.out.println("请求资源：" + url.getFile());
        System.out.println("请求路径：" + url.getPath());

        //参数
        System.out.println("参数:" + url.getQuery());
        //锚点
        System.out.println("锚点:" + url.getRef());
    }


}
