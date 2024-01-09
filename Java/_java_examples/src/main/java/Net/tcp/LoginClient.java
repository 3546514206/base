package com.gqz.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 * 双向连接
 * 创建客户端
 * 1、建立面向连接：使用Socket创建客户端 （服务器地址和端口）
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class LoginClient {
    public static void main(String[] args) throws IOException {
        System.out.println("=====client=====");
        //1、建立面向连接：使用Socket创建客户端 （服务器地址和端口）
        Socket client = new Socket("localhost", 8888);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("用户名：");
        String username = br.readLine();
        System.out.print("密码：");
        String password = br.readLine();

        //2、操作 ：输入输出流操作
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        String msg = "username=" + username + "&" + "password=" + password;
        dos.writeUTF(msg);
        dos.flush();
        //3、释放资源
        dos.close();
        client.close();
    }
}

