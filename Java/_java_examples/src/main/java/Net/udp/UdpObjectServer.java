package com.gqz.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

/**
 * 基本数据类型：接收端
 * 1、使用DatagramSocket  指定端口 创建接受端
 * 2、准备容器    封装成DatagramPacket 包裹
 * 3、阻塞式接收包裹  recevice  (DatagramPacket p)
 * 4、分析数据 将字节数组还原为对应的数据
 * byte[]	getData()
 * getLength()
 * 5、释放资源
 *
 * @author ganquanzhong
 * @ClassName: UdpServer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月12日 下午5:29:02
 */
public class UdpObjectServer {
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
        int len = datas.length;
        //读取 --> 反序列化
        ByteArrayInputStream bais = new ByteArrayInputStream(datas);
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bais));
        //读取基本数据类型  读的顺序与的顺序一致
        String msg = ois.readUTF();
        int age = ois.readInt();
        boolean flag = ois.readBoolean();
        char ch = ois.readChar();

        System.out.println(msg);
        System.out.println(age);
        System.out.println(flag);
        System.out.println(ch);

        //对象数据的还原
        Object str = ois.readObject();
        Object date = ois.readObject();
        Object employee = ois.readObject();

        if (str instanceof String) {
            String strObj = (String) str;
            System.out.println(strObj);
        }
        if (date instanceof Date) {
            Date dateObj = (Date) date;
            System.out.println(dateObj);
        }
        if (employee instanceof Employee) {
            Employee empObj = (Employee) employee;
            System.out.println(empObj.getName() + "---->" + empObj.getSalay()); //name字段透明！
        }

        // 5、释放资源
        server.close();
    }
}
