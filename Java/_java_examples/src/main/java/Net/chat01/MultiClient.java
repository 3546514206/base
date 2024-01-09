package com.gqz.chat01;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 在线聊天
 *
 * @author ganquanzhong
 * @ClassName: Client
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月16日 下午3:01:07
 */
public class MultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException {

		System.out.println("=====client=====");
		// 1、建立面向连接：使用Socket创建客户端 （服务器地址和端口）
		Socket client = new Socket("localhost", 9999);

		// 2、操作

		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());
		boolean isRunning = true;
		while (isRunning) {
			//发消息
			String msg = console.readLine();
			dos.writeUTF(msg);
			dos.flush();

			//接收消息
			String read = dis.readUTF();
			System.out.println(read);
		}
		//3、释放资源
		dos.close();
		client.close();

	}
}
