package IO;

import java.io.*;
import java.util.Date;

/*
 * 数据流
 * 1、ObjectInputStream
 * 2、ObjectOutputStream
 */
public class ObjectTest02 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//写出 --> 序列化
		ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream("obj.ser")));
		//操作数据类型 + 数据
		oos.writeUTF("编码辛酸泪");
		oos.writeInt(18);
		oos.writeBoolean(true);
		oos.writeChar('A');

		//对象
		oos.writeObject("谁解其中味");
		oos.writeObject(new Date());
		Employee emp = new Employee("马云", 400);
		oos.writeObject(emp);
		oos.flush();
		oos.close();

		//读取 --> 反序列化
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("obj.ser")));
		String msg = ois.readUTF();
		int age = ois.readInt();
		boolean flag = ois.readBoolean();
		char ch = ois.readChar();

		//对象数据的还原
		Object str = ois.readObject();
		Object date = ois.readObject();
		Object employee = ois.readObject();
		ois.close();

		if (str instanceof String) {
			String strObj = (String) str;
			System.out.println(strObj);
		}
		if (date instanceof Date) {
			Date dateObj = (Date) date;
			System.out.println(dateObj);
		}
		if (employee instanceof Employee) {
			Employee empObj = (Employee) employee;
			System.out.println(empObj.getName() + "---->" + empObj.getSalay());
		}

		System.out.println(msg);
		System.out.println(age);
		System.out.println(flag);
		System.out.println(ch);
	}
}
