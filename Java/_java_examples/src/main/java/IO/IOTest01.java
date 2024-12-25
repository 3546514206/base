package IO;

import java.io.*;

public class IOTest01 {
	/*
	 * 1、创建源
	 * 2、选择流
	 * 3、操作
	 * 4、释放资源
	 */
	public static void main(String[] args) {
		//1、创建源
		File file = new File("a.txt");

		//2、选择流
		try {
			InputStream is = new FileInputStream(file);

			//3、操作
			int read1 = is.read();
			int read2 = is.read();
			int read3 = is.read();
			int read4 = is.read();
			System.out.println((char) read1);
			System.out.println((char) read2);
			System.out.println((char) read3);
			System.out.println((char) read4);

			//4、释放资源
			is.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
