package com.gqz.chat03;

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
            new Thread(new Channel(client)).start();
        }
    }


    static class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        private boolean isRunning;

        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                isRunning = true;
            } catch (IOException e) {
                System.out.println("---初始化错误----");
                release();
                e.printStackTrace();
            }
        }

        //接收消息
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                System.out.println("---接收消息错误----");
                release();
                e.printStackTrace();
            }
            return msg;
        }

        //发送消息
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                System.out.println("---发送消息错误----");
                release();
                e.printStackTrace();
            }
        }

        //释放资源
        private void release() {
            this.isRunning = false;
            GqzUtils.close(dis, dos, client);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equals("")) {
                    send(msg);
                }
            }
        }
    }
}
