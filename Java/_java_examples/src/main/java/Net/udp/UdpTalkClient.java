package com.gqz.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 多次交流：发送端
 * 1、使用DatagramSocket  指定端口 创建接受端
 * 2、准备数据，一定要转成字节数组
 * 3、封装成DatagramPacket包裹，需要指定目的地
 * 4、发送包裹send (DatagramPacket p)
 * byte[]	getData()
 * getLength()
 * 5、释放资源
 *
 * @author ganquanzhong
 * @ClassName: UdpClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月12日 下午5:29:02
 */
public class UdpTalkClient {
    public static void main(String[] args) throws Exception {
        System.out.println("client  发送方启动中......");
        //  1、使用DatagramSocket  指定端口 创建接受端
        DatagramSocket client = new DatagramSocket(8888);
        //  2、准备数据，一定要转成字节数组
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String data = reader.readLine();
            byte[] datas = data.getBytes();   //字符串转成字节数组
            //  3、封装成DatagramPacket包裹，需要指定目的地本机
            DatagramPacket packet = new DatagramPacket(datas, datas.length, new InetSocketAddress("localhost", 9999));
            //  4、发送包裹send (DatagramPacket p)
            client.send(packet);
            if (data.equals("bye")) {
                break;
            }
        }
        //  5、释放资源
        client.close();
    }
}
