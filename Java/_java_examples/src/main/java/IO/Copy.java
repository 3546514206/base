package IO;

import java.io.*;

public class Copy {
	/**
	 * @param srcPath
	 * @param destPath
	 * @Title: copy
	 * @Description: copy文件拷贝  字节方式
	 * @author ganquanzhong
	 * @date 2019年6月27日 下午3:22:09
	 */
	public static void copy(String srcPath, String destPath) {
		// 1、创建源
		File src = new File(srcPath);//源头
		File dest = new File(destPath);//目的地
		// 2、选择流
		InputStream is = null; // 输入流 原文件
		OutputStream os = null; // 输出文件 复制文件
		//3、操作  文件拷贝
		try {
			is = new FileInputStream(src);
			// FileOutputStream构造方法 append true追加到末尾
			os = new FileOutputStream(dest, true);
			// 每次读多大
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
			// 4、释放资源 先打开的后关闭
			try {
				if (null != os) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != is) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void copy2(String srcPath, String destPath) {
		// 1、创建源
		File src = new File(srcPath);//源头
		File dest = new File(destPath);//目的地
		// 2、选择流
		//3、操作  文件拷贝
		try (InputStream is = new FileInputStream(src);// 输入流 原文件
			 OutputStream os = new FileOutputStream(dest);// 输出文件 复制文件
		) {
			// 每次读多大
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
		}
	}

	//测试
	public static void main(String[] args) {
		copy("E:/a.txt", "E:/copy.txt");
	}
}

