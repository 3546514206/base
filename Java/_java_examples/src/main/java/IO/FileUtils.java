package IO;

import java.io.*;

/*
 *  封装拷贝
 *  封装释放资源
 */
public class FileUtils {
	public static void main(String[] args) {

		// 文件到文件拷贝
		try {
			InputStream is = new FileInputStream("a.txt");
			OutputStream os = new FileOutputStream("d.txt");
			copy(is, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] data = null;
		// 文件到字节数组
		try {
			InputStream is = new FileInputStream("p.jpg");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			copy(is, os);
			data = os.toByteArray();
			System.out.println(data.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 字节数组到文件
		try {
			InputStream is = new ByteArrayInputStream(data);
			OutputStream os = new FileOutputStream("p-copy.png");
			copy(is, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param srcPath
	 * @param destPath
	 * @Title: copy
	 * @Description: 输入输出流对接
	 * @author ganquanzhong
	 * @date 2019年6月28日 上午9:37:19
	 */
	public static void copy(InputStream is, OutputStream os) {
		// 3、操作 文件拷贝
		try {
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
			close(is, os);
		}
	}

	/**
	 * @param is
	 * @param os
	 * @Title: close
	 * @Description:封装关闭流
	 * @author ganquanzhong
	 * @date 2019年6月28日 上午11:35:25
	 */
	public static void close(InputStream is, OutputStream os) {
		System.out.println("释放资源--close(InputStream is, OutputStream os)");
		//释放资源 先打开的后关闭
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

	/*
	 * 释放资源
	 */
	public static void close(Closeable... ios) {
		System.out.println("close(Closeable...ios) ");
		for (Closeable io : ios) {
			try {
				if (null != io) {
					io.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
