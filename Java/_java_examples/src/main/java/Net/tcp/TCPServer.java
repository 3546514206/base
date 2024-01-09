package com.gqz.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 熟悉流程
 * 创建服务器
 * 1、指定端口  使用ServerSocket创建服务器
 * 2、阻塞式等待连接accept
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class TCPServer {
	public static void main(String[] args) throws IOException {
		System.out.println("=====server=====");
		// 1、指定端口号 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);

		// 2、阻塞式等待连接accept
		Socket client = server.accept();// 建立连接返回一个socket

		System.out.println("一个客户端建立连接！");
		// 3、操作 ：文件存储
		InputStream is = new BufferedInputStream(client.getInputStream());
		OutputStream os = new BufferedOutputStream(new FileOutputStream("p12.jpg"));
		byte[] flush = new byte[1024];
		int len = -1;
		while ((len = is.read(flush)) != -1) {
			os.write(flush, 0, len);
		}
		os.flush();
		// 4、释放资源
		os.close();
		is.close();
		client.close();
	}
}
