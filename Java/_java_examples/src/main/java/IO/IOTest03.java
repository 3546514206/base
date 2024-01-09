package IO;

import java.io.*;

public class IOTest03 {
	/*  标准使用 循环
	 * 1、创建源
	 * 2、选择流
	 * 3、操作
	 * 4、释放资源
	 */
	public static void main(String[] args) {
		//1、创建源
		File file = new File("E:/DevFile/ShopFile/配置shop项目的虚拟路径.txt");

		//2、选择流
		InputStream is = null;
		try {

			is = new FileInputStream(file);

			//3、操作
			//分段读取
			byte[] flush = new byte[3];//缓冲器
			int len = -1;//实际接受长度
			/**
			 *  从输入流读取一些字节数，并将它们存储到缓冲区b 。 
			 实际读取的字节数作为整数返回。 该方法阻塞直到输入数据可用，
			 检测到文件结束或抛出异常。
			 *
			 *
			 */
			while ((len = is.read(flush)) != -1) {
				String str = new String(flush, 0, len);
				System.out.println("此次读取" + len + "个字节" + " 字节为：" + str);
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
