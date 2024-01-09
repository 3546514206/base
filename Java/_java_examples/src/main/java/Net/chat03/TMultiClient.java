package com.gqz.chat03;

import java.io.IOException;
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
public class TMultiClient {
    public static void main(String[] args) throws UnknownHostException, IOException {

        System.out.println("=====client=====");
        // 1、建立面向连接：使用Socket创建客户端 （服务器地址和端口）
        Socket client = new Socket("localhost", 9999);

        //客户端发送消息
        new Thread(new Send(client)).start();

        //客户端接收消息
        new Thread(new Receive(client)).start();
    }
}
