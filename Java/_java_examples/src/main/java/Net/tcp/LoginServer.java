package com.gqz.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 熟悉流程
 * 双向连接
 * 1、指定端口  使用ServerSocket创建服务器
 * 2、阻塞式等待连接accept
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class LoginServer {


    public static void main(String[] args) throws IOException {
        System.out.println("=====server=====");
        //1、指定端口号 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(8888);

        //2、阻塞式等待连接accept
        Socket client = server.accept();//建立连接返回一个socket

        System.out.println("一个客户端建立连接！");
        //3、操作 ：输入输出流操作
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String datas = dis.readUTF();
        //分析数据
        String[] dataArray = datas.split("&");
        System.out.println(dataArray);
        for (String info : dataArray) {
            String[] userInfo = info.split("=");
            System.out.println(userInfo);
            if (userInfo[0].equals("username")) {
                System.out.println("用户名:" + userInfo[1]);
            } else {
                System.out.println("密 码:" + userInfo[1]);
            }
        }


        //4、释放资源
        dis.close();
        client.close();
    }
}

