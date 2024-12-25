package com.gqz.location;

import java.net.InetSocketAddress;

/**
 * port 端口
 * 1、区分软件
 * 2、2个字节0-65535
 * 3、同一个协议端口号不能冲突
 * 4、定义端口号越大越好
 * <p>
 * InetSocketAddress
 *
 * @author ganquanzhong
 * @ClassName: PortTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月12日 下午2:46:50
 */
public class PortTest {
    public static void main(String[] args) {

        InetSocketAddress inetSocketAddress = new InetSocketAddress("ganquanzhong.top", 3306);
        System.out.println(inetSocketAddress.getHostName() + "   " + inetSocketAddress.getAddress());
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress("127.0.0.1", 80);
        System.out.println(inetSocketAddress2.getHostName() + "   " + inetSocketAddress2.getAddress());
    }
}
