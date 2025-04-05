package IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedTest04 {
	/*
	 * 文件字符流 输出流  Writer
	 */

	public static void main(String[] args) {
		//1、创建源 
		File file = new File("b.txt");


		//2、选择流
		BufferedWriter writer = null;

		//3、操作   Writer  字符输出流
		try {
			writer = new BufferedWriter((new FileWriter(file)));
			String msg = "hello stxt  加油，冲刺！";
			writer.append(msg);
			writer.newLine();
			writer.append("甘全中geeg");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//4、关闭流
	}
}
