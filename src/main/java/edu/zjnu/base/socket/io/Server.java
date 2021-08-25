package edu.zjnu.base.socket.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: NioServer
 * @author: 杨海波
 * @date: 2021-08-21
 **/
public class Server {

    public static void main(String[] args) {
        try {
            //创建一个ServerSocket监听8081端口
            ServerSocket server = new ServerSocket(8081);
            //等待请求
            Socket socket = server.accept();
            //接收请求到之后使用socket进行通信，创建BufferedReader用于读取客户端的数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //读取请求的报文内容
            String line = reader.readLine();
            //打印客户端请求的报文内容
            System.out.println(line);

            //创建PrintWriter
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //写入数据
            writer.println("客户端你好，这是来自服务端的数据，请接收：data");
            //发送数据
            writer.flush();

            writer.close();
            reader.close();
            socket.close();
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
