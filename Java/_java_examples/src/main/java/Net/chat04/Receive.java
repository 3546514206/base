package com.gqz.chat04;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 使用多线程封装：接收端
 * 1、接收消息
 * 2、释放资源
 * 3、重写run
 *
 * @author ganquanzhong
 * @ClassName: Receive
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月16日 下午5:08:34
 */
public class Receive implements Runnable {
    private DataInputStream dis;
    private Socket client;
    private boolean isRunning;

    public Receive(Socket client) {
        this.client = client;
        isRunning = true;
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            System.out.println("====client 接收消息 初始化错误=====");
            release();
            e.printStackTrace();
        }
    }

    // 接收消息
    private String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            System.out.println("---client 接收消息错误----");
            release();
            e.printStackTrace();
        }
        return msg;
    }

    // 释放资源1
    private void release() {
        this.isRunning = false;
        GqzUtils.close(dis, client);
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = receive();
            if (!msg.equals("")) {
                System.out.println(msg);
            }
        }
    }
}