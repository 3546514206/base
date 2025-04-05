package com.gqz.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 基本数据类型：发送端
 * 1、使用DatagramSocket  指定端口 创建接受端
 * 2、将基本数据类型，一定要转成字节数组
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
public class UdpTypeClient {
    public static void main(String[] args) throws Exception {
        System.out.println("client  发送方启动中......");
        //  1、使用DatagramSocket  指定端口 创建接受端
        DatagramSocket client = new DatagramSocket(8888);
        //  2、将基本数据类型，一定要转成字节数组
//		String data = "模拟UPD发送数据，请求登录（username ,password）";
//		byte[] datas = data.getBytes();   //字符串转成字节数组

        //基本数据类型转为字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
        //操作数据类型+ 数据
        dos.writeUTF("编码辛酸泪！！");
        dos.writeBoolean(true);
        dos.writeChar('A');
        dos.writeInt(8888);
        dos.flush();
        byte[] datas = baos.toByteArray();//创建一个新分配的字节数组。


        //  3、封装成DatagramPacket包裹，需要指定目的地本机
        DatagramPacket packet = new DatagramPacket(datas, datas.length,
                new InetSocketAddress("localhost", 9999));
        //  4、发送包裹send (DatagramPacket p)
        client.send(packet);
        //  	byte[]	getData()
        //  		getLength()
        //  5、释放资源
        client.close();
    }
}
