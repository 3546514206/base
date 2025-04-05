package com.gqz.chat05;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 使用多线程封装：发送端
 * 1、发送消息
 * 2、从控制台获取消息
 * 3、释放资源
 * 4、重写run
 *
 * @author ganquanzhong
 * @ClassName: Send
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月16日 下午5:09:16
 */
public class Send implements Runnable {
    private BufferedReader console;
    private DataOutputStream dos;
    private Socket client;
    private boolean isRunning;
    private String name;

    public Send(Socket client, String name) {
        this.client = client;
        isRunning = true;
        this.name = name;
        console = new BufferedReader(new InputStreamReader(System.in));
        try {
            dos = new DataOutputStream(client.getOutputStream());
            //发送名称
            send(name);
        } catch (IOException e) {
            System.out.println("====初始化错误====");
            this.release();
            e.printStackTrace();
        }
    }

    // 发送消息
    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            System.out.println("--client-发送消息错误----");
            release();
            e.printStackTrace();
        }
    }

    // 从控制台获取消息
    private String getFromConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
            System.out.println("=====console error=====");
            e.printStackTrace();
        }
        return "";
    }

    // 释放资源1
    private void release() {
        this.isRunning = false;
        Utils.close(dos, client);
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = getFromConsole();
            if (!msg.equals("")) {
                send(msg);
            }
        }
    }
}
