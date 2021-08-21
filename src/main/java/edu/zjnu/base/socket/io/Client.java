package edu.zjnu.base.socket.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @description: Client
 * @author: 杨海波
 * @date: 2021-08-21
 **/
public class Client {

    public static void main(String[] args) {
        try {
            //创建一个Socket
            Socket socket = new Socket("localhost", 8081);
            //使用Socket创建PrintWriter来进行数据的写
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //使用Socket来创建PrintWriter进行数据的读
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //写入数据
            writer.println("服务端你好，我需要请求数据");
            //发送数据
            writer.flush();

            //接收数据
            String line = reader.readLine();
            //打印数据
            System.out.println(line);

            //关闭资源
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
