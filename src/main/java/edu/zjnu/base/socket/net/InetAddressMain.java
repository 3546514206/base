package edu.zjnu.base.socket.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: InetAddressMain
 * @author: 杨海波
 * @date: 2021-10-05
 **/
public class InetAddressMain {

    public static void main(String[] args) {
        try {
            //File file=new File("hello.txt");
            InetAddress inet1 = InetAddress.getByName("192.168.10.14");

            System.out.println(inet1);

            InetAddress inet2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inet2);

            InetAddress inet3 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet3);

            //获取本地ip
            InetAddress inet4 = InetAddress.getLocalHost();
            System.out.println(inet4);

            //getHostName()
            System.out.println(inet2.getHostName());
            //getHostAddress()
            System.out.println(inet2.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
