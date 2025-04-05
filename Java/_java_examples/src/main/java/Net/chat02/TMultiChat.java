package com.gqz.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端，消息转发
 * 多个客户可以正常收发消息
 *
 * @author ganquanzhong
 * @ClassName: Chat
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月16日 下午3:01:34
 */
public class TMultiChat {
    public static void main(String[] args) throws IOException {
        System.out.println("=====Server=====");
        //1、指定端口号 使用ServerSocket创建服务器   监听端口9999
        ServerSocket server = new ServerSocket(9999);

        //2、阻塞式等待连接accept
        while (true) {
            Socket client = server.accept();
            System.out.println("建立一个连接");
            new Thread(() -> {
                DataInputStream dis = null;
                DataOutputStream dos = null;
                //3、数据处理
                try {
                    dis = new DataInputStream(client.getInputStream());
                    dos = new DataOutputStream(client.getOutputStream());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                boolean isRunning = true, flag = true;
                while (isRunning && flag) {
                    flag = client.isConnected();
                    System.out.println(flag);
                    //接收请求
                    String msg;
                    try {
                        msg = dis.readUTF();
                        //响应
                        dos.writeUTF(msg);
                        dos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        isRunning = false;
                    }
                }
                //4、释放资源
                try {
                    if (null == dis) {
                        dis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (null == dos) {
                        dos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (null == client) {
                        client.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();

        }
    }
}
