package com.gqz.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 熟悉流程
 * 单向连接
 * 1、指定端口  使用ServerSocket创建服务器
 * 2、阻塞式等待连接accept
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class LoginTwoWayServer {
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
        String username = "";
        String password = "";
        String[] dataArray = datas.split("&");
        for (String info : dataArray) {
            String[] userInfo = info.split("=");
            if (userInfo[0].equals("username")) {
                username = userInfo[1];
            } else {
                password = userInfo[1];
            }
        }

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        //判断
        if (username.equals("gqz") && password.equals("admin")) {
            dos.writeUTF("登录成功，欢迎进入系统！！");
        } else {
            dos.writeUTF("用户名或密码错误！！");
        }
        dos.flush();
        //4、释放资源
        dis.close();
        dos.close();
        client.close();

    }
}

