package IO;

import java.io.UnsupportedEncodingException;

/*
 * 编码：字符串->字节
 */
public class ContentDecode {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String msg = "甘全中 AZaz";
		//编码：字节数组
		byte[] datas = msg.getBytes();
		System.out.println("UTF字节数" + datas.length);
//		for (int i = 0; i < datas.length; i++) {
//			System.out.println("第"+(i+1)+"个字节："+datas[i]);
//		}

		byte[] datas1 = msg.getBytes("UTF-16LE");
		System.out.println("UTF-16LE字节数" + datas1.length);


		byte[] datas2 = msg.getBytes("GBK");
		System.out.println("GBK字节数" + datas2.length);
	}
}
