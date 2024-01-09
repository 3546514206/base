package com.gqz.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 接收端：使用面向对象封装
 *
 * @author ganquanzhong
 * @ClassName: TalkRecevice
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月15日 下午3:19:14
 */
public class TalkRecevice implements Runnable {
    private DatagramSocket server;
    private String from;

    public TalkRecevice(int port, String from) {
        this.from = from;
        try {
            server = new DatagramSocket(port);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

            // 2、准备容器    封装成DatagramPacket 包裹
            byte[] container = new byte[1024 * 60];//最大60K
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            // 3、阻塞式接收包裹  recevice  (DatagramPacket p)
            try {
                server.receive(packet);//阻塞式
                // 4、分析数据
                byte[] datas = packet.getData();
                int len = datas.length;
                String data = new String(datas, 0, len);
                System.out.println("--" + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS").format(new Date()) + "--");
                System.out.println(from + ": " + data);
                if (data.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 5、释放资源
        server.close();
    }
}
