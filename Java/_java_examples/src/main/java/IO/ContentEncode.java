package IO;

import java.io.UnsupportedEncodingException;

/*
 * 解码：字节数组-->字符串
 */
public class ContentEncode {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String msg = "甘全中AZaz";
		//编码：字节数组
		byte[] datas = msg.getBytes();
		System.out.println("字符串到字节数组    编码");
		System.out.println("UTF字节数" + datas.length);
//		for (int i = 0; i < datas.length; i++) {
//			System.out.println("第"+(i+1)+"个字节："+datas[i]);
//		}

		System.out.println("字节数组到字符串    解码");
//		String(byte[] bytes, int offset, int length, Charset charset) 
//		构造一个新的String通过使用指定的指定字节子阵列解码charset 。 
		String msg1 = new String(datas, 0, datas.length, "utf-8");
		System.out.println(msg1);
		System.out.println(msg1.length());


	}
}
