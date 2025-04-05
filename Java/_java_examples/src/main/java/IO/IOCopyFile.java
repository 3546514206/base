package IO;

import java.io.*;

/*
 * 文件字节输出流
 * 1、创建源
 * 2、选择流
 * 3、操作（写出内容）
 * 4、释放资源
 */
public class IOCopyFile {
	public static void main(String[] args) {
		//1、创建源
		File file = new File("E:/登记照.jpg");

		File src = new File("E:/dext.jpg");

		//2、选择流
		InputStream is = null; //输入流  原文件
		OutputStream os = null; //输出文件 复制文件
		try {

			is = new FileInputStream(file);

			//FileOutputStream构造方法  append true追加到末尾
			os = new FileOutputStream(src, true);

			//每次读多大
			byte[] flush = new byte[1024 * 5];
			int len = -1;
			while ((len = is.read(flush)) != -1) {
				os.write(flush, 0, flush.length);
				os.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//4、释放资源   先打开的后关闭
				if (null != os) {
					os.close();
				}
			} catch (Exception e) {
			}
			try {
				//4、释放资源   先打开的后关闭
				if (null != is) {
					is.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
