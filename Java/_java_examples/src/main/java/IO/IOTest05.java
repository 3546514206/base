package IO;

import java.io.*;

public class IOTest05 {
	/*
	 * 用字符 读取
	 */
	public static void main(String[] args) {
		//1、创建源 
		File file = new File("a.txt");

		//2、选择流
		Reader reader = null;
		//3、操作
		try {
			reader = new FileReader(file);
			char[] flush = new char[1024];
			int len = -1;
			while ((len = reader.read(flush)) != -1) {
				String str = new String(flush, 0, len);
				System.out.println(str);
			}
			reader.read(flush);

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
