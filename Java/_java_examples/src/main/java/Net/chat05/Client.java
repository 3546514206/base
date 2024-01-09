package com.gqz.chat05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 在线聊天
 * 封装 实现多用户聊天
 *
 * @author ganquanzhong
 * @ClassName: Client
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月16日 下午3:01:07
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\t进入群聊系统   输入您的用户名称：");
        String name = br.readLine();
        System.out.println("=====client=" + name + "====");
        // 1、建立面向连接：使用Socket创建客户端 （服务器地址和端口）
        Socket client = new Socket("localhost", 9999);

        //客户端发送消息
        new Thread(new Send(client, name)).start();

        //客户端接收消息
        new Thread(new Receive(client)).start();
    }
}
