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
public class LoginMultiServer {
    public static void main(String[] args) throws IOException {
        boolean isRunning = true;
        System.out.println("=====server=====");
        //1、指定端口号 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(8888);

        //2、阻塞式等待连接accept
        while (isRunning) {
            Socket client = server.accept();//建立连接返回一个socket
            System.out.println("一个客户端建立连接！");
            new Thread(new Channel(client)).start();
        }
        server.close();

    }


    //一个channel就是一个管道！
    static class Channel implements Runnable {
        private Socket client;
        //输入流
        private DataInputStream dis;
        private DataOutputStream dos;

        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        //接收数据
        private String recevie() {
            String datas = "";
            try {
                datas = dis.readUTF();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return datas;
        }

        //发送数据
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //释放资源
        private void release() {
            try {
                if (null != dos) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != dis) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != client) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            //3、操作 ：输入输出流操作
            //分析数据
            String username = "";
            String password = "";
            String[] dataArray = recevie().split("&");
            for (String info : dataArray) {
                String[] userInfo = info.split("=");
                if (userInfo[0].equals("username")) {
                    username = userInfo[1];
                } else {
                    password = userInfo[1];
                }
            }
            //判断
            if (username.equals("gqz") && password.equals("admin")) {
                send("登录成功，欢迎进入ForFuture系统！！");
            } else {
                send("用户名或密码错误！！");
            }
            //4、释放资源
            release();
        }


    }
}

