package IO;

import java.io.*;

public class BufferedTest03 {
	/*
	 * 用字符 读取
	 */
	public static void main(String[] args) {
		//1、创建源 
		File file = new File("a.txt");

		//2、选择流
		BufferedReader reader = null;
		//3、操作
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;//使用BufferedReader的readLine方法一行一行的读取
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//4、关闭流，释放资源
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}
}
