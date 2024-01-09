package com.gqz.location;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress ：多个静态方法
 * 1、getLocalHost：本地
 * 2、getByName:根据域名DNS|IP地址  --> IP
 * 两个成员方法
 * 1、getHostAddress ：返回地址
 * 2、getHostName ：返回计算机名
 *
 * @author ganquanzhong
 * @ClassName: IPTest
 * @Description: ip定位计算机
 * @date 2019年7月12日 下午2:19:32
 */
public class IPTest {
    public static void main(String[] args) throws UnknownHostException {
        // 使用getLocalHost方法创建InetAddresss对象
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());

        // 根据域名得到InetAddresss对象
        addr = InetAddress.getByName("www.ganquanzhong.top");
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());

        // 根据域名得到InetAddresss对象
        addr = InetAddress.getByName("169.254.129.79");
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());
    }
}
