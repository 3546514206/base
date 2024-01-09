package com.gqz.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Date;


/**
 * 引用数据类型：发送端 1、使用DatagramSocket 指定端口 创建接受端 2、将基本数据类型，一定要转成字节数组
 * 3、封装成DatagramPacket包裹，需要指定目的地 4、发送包裹send (DatagramPacket p) byte[] getData()
 * getLength() 5、释放资源
 *
 * @author ganquanzhong
 * @ClassName: UdpClient
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月12日 下午5:29:02
 */
public class UdpObjectClient {
    public static void main(String[] args) throws Exception {
        System.out.println("client  发送方启动中......");
        // 1、使用DatagramSocket 指定端口 创建接受端
        DatagramSocket client = new DatagramSocket(8888);

        // 2、将基本数据类型，一定要转成字节数组
        // 写出 --> 序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos));

        // 操作基本数据类型 + 数据
        oos.writeUTF("不负天地，不负自己");
        oos.writeInt(9696);
        oos.writeBoolean(true);
        oos.writeChar('A');
        // 对象
        oos.writeObject("Struggle");
        oos.writeObject(new Date());
        // 写一个对象
        Employee emp = new Employee("马云", 400);
        oos.writeObject(emp);
        oos.flush();
        byte[] datas = baos.toByteArray();

        // 3、封装成DatagramPacket包裹，需要指定目的地本机
        DatagramPacket packet = new DatagramPacket(datas, datas.length, new InetSocketAddress("localhost", 9999));
        // 4、发送包裹send (DatagramPacket p)
        client.send(packet);
        // 5、释放资源
        client.close();
    }
}
