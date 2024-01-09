package IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class IOTest06 {
	/*
	 * 文件字符流 输出流  Writer
	 */

	public static void main(String[] args) {
		//1、创建源 
		File file = new File("b.txt");


		//2、选择流
		Writer writer = null;

		//3、操作   Writer  字符输出流
		try {
			writer = new FileWriter(file);
			String msg = "hello stxt  加油，冲刺！";
			char[] data = msg.toCharArray(); //字符串--》字符数组
			writer.write(data, 0, data.length);
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
