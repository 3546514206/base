package com.gqz.tcp;

import java.io.*;
import java.net.Socket;

/*
 * 熟悉流程
 * 创建客户端
 * 1、建立面向连接：使用Socket创建客户端 （服务器地址和端口）
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class TCPClient {
	public static void main(String[] args) throws IOException {
		System.out.println("=====client=====");
		//1、建立面向连接：使用Socket创建客户端 （服务器地址和端口） 
		Socket client = new Socket("localhost", 8888);
		//2、操作 ：拷贝
		InputStream is = new BufferedInputStream(new FileInputStream("p.jpg"));
		OutputStream os = new BufferedOutputStream(client.getOutputStream());
		byte[] flush = new byte[1024];
		int len = -1;
		while ((len = is.read(flush)) != -1) {
			os.write(flush, 0, len);
		}
		os.flush();
		//3、释放资源
		os.close();
		is.close();
		client.close();
	}
}

