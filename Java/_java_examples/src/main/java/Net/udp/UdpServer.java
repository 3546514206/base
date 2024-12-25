package com.gqz.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 基本流程：接收端
 * 1、使用DatagramSocket  指定端口 创建接受端
 * 2、准备容器    封装成DatagramPacket 包裹
 * 3、阻塞式接收包裹  recevice  (DatagramPacket p)
 * 4、分析数据
 * byte[]	getData()
 * getLength()
 * 5、释放资源
 *
 * @author ganquanzhong
 * @ClassName: UdpServer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月12日 下午5:29:02
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        System.out.println("server接收  这里是服务端，接收数据启动中..........");
        // 1、使用DatagramSocket  指定端口 创建接受端
        DatagramSocket server = new DatagramSocket(9999);
        // 2、准备容器    封装成DatagramPacket 包裹
        byte[] container = new byte[1024 * 60];//最大60K
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        // 3、阻塞式接收包裹  recevice  (DatagramPacket p)
        server.receive(packet); //阻塞式
        // 4、分析数据
        // 	byte[]	getData()
        // 		getLength()
        byte[] datas = packet.getData();
        System.out.println(packet.getLength());
        System.out.println(packet.getOffset());
        System.out.println(packet.getPort());
        System.out.println(packet.getAddress());
        System.out.println(packet.getSocketAddress());

        System.out.println(new String(datas));
        // 5、释放资源
        server.close();
    }
}
