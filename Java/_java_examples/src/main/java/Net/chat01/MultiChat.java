package com.gqz.chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端，消息转发
 *
 * @author ganquanzhong
 * @ClassName: Chat
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月16日 下午3:01:34
 */
public class MultiChat {
    public static void main(String[] args) throws IOException {
        System.out.println("=====Server=====");
        //1、指定端口号 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(9999);
        //2、阻塞式等待连接accept
        Socket client = server.accept();
        System.out.println("建立一个连接");

        //3、数据处理
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        boolean isRunning = true;
        while (isRunning) {
            //接收请求
            String msg = dis.readUTF();
            //响应
            dos.writeUTF(msg);
            dos.flush();
        }

        //4、释放资源
        dos.close();
        dis.close();
        client.close();
        server.close();
    }
}
