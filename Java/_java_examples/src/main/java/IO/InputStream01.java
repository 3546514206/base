package IO;

import java.io.*;

public class InputStream01 {

	public static void main(String[] args) {
		/**
		 * 1、创建源
		 * 2、选择流
		 * 3、操作
		 * 4、关闭流 释放资源
		 *
		 */

		//1、创建源
		String path = "E:/DevFile/ShopFile/配置shop项目的虚拟路径.txt";
		File file = new File(path);

		//2、选择流
		InputStream is = null;

		try {
			is = new FileInputStream(file);

			//3、操作
			int temp;
			while ((temp = is.read()) != -1) {
				System.out.print((char) temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
