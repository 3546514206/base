package IO;

import java.io.*;

/**
 * 1、图片读取到字节数组 2、字节数组写出到文件
 *
 * @author ganquanzhong
 * @ClassName: IOTest09
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月15日 下午1:24:10
 */
public class IOTest09 {
	public static void main(String[] args) {
		//图片转成字节数组
		byte[] datas = fileToByteArray("p.jpg");
		System.out.println(datas.length);

		byteArrayToFile(datas, "filePath");
	}

	/*
	 * 1、图片读取到字节数组 图片到程序 FileInputStream 程序到字节数组 ByteArrayOutputStream
	 */
	public static byte[] fileToByteArray(String filePath) {
		// 1、创建源与目的地
		File file = new File(filePath);
		// 2、选择流
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			is = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			// 3、操作
			byte[] flush = new byte[1024 * 10];// 缓冲容器
			int len = -1;// 接受长度
			while ((len = is.read(flush)) != -1) {
				// 写到字节数组中
				baos.write(flush, 0, len);
			}
			baos.flush();
			return baos.toByteArray();//返回数据
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//4.释放资源
			try {
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * 1、字节数组写出到图片
	 * 字节数组到程序 ByteArrayInputStream
	 * 程序到文件 FileOutputStream
	 */
	public static void byteArrayToFile(byte[] src, String filePath) {
		//1、创建源
		File dest = new File(filePath);

		//2、选择流
		ByteArrayInputStream bais = null;
		OutputStream os = null;

		//3、操作
		try {
			bais = new ByteArrayInputStream(src);
			os = new FileOutputStream(dest);
			//分段读取
			byte[] flush = new byte[5];
			int len = -1;
			while ((len = bais.read(flush)) != -1) {
				os.write(flush, 0, len);
			}
			os.flush();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			//4、释放资源
			try {
				if (null != os) {
					os.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
