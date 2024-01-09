package IO;

import java.io.*;

/*
 * 数据流
 * 1、写出后读取
 * 2、读出的顺序与写出保持一致
 */
public class DataTest {
	public static void main(String[] args) throws IOException {
		//写出
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
		dos.writeUTF("编码辛酸泪");
		dos.writeInt(18);
		dos.writeBoolean(true);
		dos.writeChar('A');
		dos.flush();
		byte[] datas = baos.toByteArray();
		//读取
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		String msg = dis.readUTF();
		int age = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		System.out.println(msg);
		System.out.println(age);
		System.out.println(flag);
		System.out.println(ch);
	}
}
