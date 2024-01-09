package com.gqz.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 单向连接
 * 创建客户端
 * 1、建立面向连接：使用Socket创建客户端 （服务器地址和端口）
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class LoginMultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("=====client=====");
		//使用Socket创建客户端 （服务器地址和端口） 
		Socket client = new Socket("localhost", 8888);
		//请求服务器 request
		new Request(client).send();
		//服务器响应
		new Response(client).recevie();
		//3、释放资源
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author ganquanzhong
	 * @ClassName: Request
	 * @Description: 请求
	 * @date 2019年7月16日 上午10:56:12
	 */
	static class Request {
		private Socket client;
		private BufferedReader console;
		private DataOutputStream dos;
		private String msg;

		public Request(Socket client) {
			console = new BufferedReader(new InputStreamReader(System.in));
			this.msg = init();
			this.client = client;
			try {
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private String init() {
			try {
				System.out.print("用户名：");
				String username = console.readLine();
				System.out.print("密码：");
				String password = console.readLine();
				return "username=" + username + "&" + "password=" + password;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}

		private void send() {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	/**
	 * @author ganquanzhong
	 * @ClassName: Response
	 * @Description: 响应
	 * @date 2019年7月16日 上午10:56:27
	 */
	static class Response {
		private Socket client;
		private DataInputStream dis;

		public Response(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void recevie() {
			String result;
			try {
				result = dis.readUTF();
				System.out.println(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

