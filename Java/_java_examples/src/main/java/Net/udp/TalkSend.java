package com.gqz.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;


/**
 * 发送端
 *
 * @author ganquanzhong
 * @ClassName: TalkRecevice
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月15日 下午3:19:14
 */
public class TalkSend implements Runnable {
    private DatagramSocket client;
    private String toIp;
    private int toPort;
    private BufferedReader reader;

    public TalkSend(int port, String toIp, int toPort) {
        this.toIp = toIp;
        this.toPort = toPort;
        try {
            client = new DatagramSocket(port);
            reader = new BufferedReader(new InputStreamReader(System.in));//控制台输入
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

            String data;
            try {
                data = reader.readLine();
                byte[] datas = data.getBytes(); //字符串转成字节数组
                //  3、封装成DatagramPacket包裹，需要指定目的地本机
                DatagramPacket packet = new DatagramPacket(datas, datas.length, new InetSocketAddress(this.toIp, this.toPort));
                //  4、发送包裹send (DatagramPacket p)
                try {
                    client.send(packet);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (data.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //  5、释放资源
        client.close();
    }
}
