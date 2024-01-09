package IO;

import java.io.*;

public class IOTest02 {
	/*  标准使用 循环
	 * 1、创建源
	 * 2、选择流
	 * 3、操作
	 * 4、释放资源
	 */
	public static void main(String[] args) {
		//1、创建源
		File file = new File("a.txt");
		//图片文件
//		File file = new File("E:/DevFile/ShopFile/upload/0.jpg");

		//2、选择流
		InputStream is = null;
		try {

			is = new FileInputStream(file);

			//3、操作
			int temp;
			while ((temp = is.read()) != -1) {
				//is.read() 返回int类型
				System.out.println(temp);
				//强转为char
				//System.out.println((char)temp);
			}

			//4、释放资源
			is.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
