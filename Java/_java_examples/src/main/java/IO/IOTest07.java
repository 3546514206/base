package IO;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class IOTest07 {
	/*
	 * 字节数组  ByteArrayInputStream
	 */
	public static void main(String[] args) {
		//1、创建源 
		byte[] src = "talk is cheap show me the code".getBytes();

		//2、选择流
		InputStream is = null;
		//3、操作
		try {
			is = new ByteArrayInputStream(src);
			byte[] flush = new byte[1024];
			int len = -1;
			while ((len = is.read(flush)) != -1) {
				String str = new String(flush, 0, len);
				System.out.println(str);
			}
			is.read(flush);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//4、关闭流，释放资源
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}
}
