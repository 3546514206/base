package IO;

import java.io.*;
import java.util.Date;

/*
 * 数据流
 * 1、ObjectInputStream
 * 2、ObjectOutputStream
 */
public class ObjectTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//写出 --> 序列化
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos));

		//操作基本数据类型 + 数据
		oos.writeUTF("不负天地，不负自己");
		oos.writeInt(9696);
		oos.writeBoolean(true);
		oos.writeChar('A');
		//对象
		oos.writeObject("Struggle");
		oos.writeObject(new Date());
		//写一个对象
		Employee emp = new Employee("马云", 400);
		oos.writeObject(emp);
		oos.flush();
		byte[] datas = baos.toByteArray();

		//读取 --> 反序列化
		ByteArrayInputStream bais = new ByteArrayInputStream(datas);
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bais));
		//读取基本数据类型  读的顺序与的顺序一致
		String msg = ois.readUTF();
		int age = ois.readInt();
		boolean flag = ois.readBoolean();
		char ch = ois.readChar();

		System.out.println(msg);
		System.out.println(age);
		System.out.println(flag);
		System.out.println(ch);

		//对象数据的还原
		Object str = ois.readObject();
		Object date = ois.readObject();
		Object employee = ois.readObject();

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
			System.out.println(empObj.getName() + "---->" + empObj.getSalay()); //name字段透明！
		}


	}
}

//javabean 封装数据
class Employee implements java.io.Serializable {
	private transient String name;   //该数据不需要序列化
	private double salay;

	public Employee() {
	}

	public Employee(String name, double salay) {
		super();
		this.name = name;
		this.salay = salay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalay() {
		return salay;
	}

	public void setSalay(double salay) {
		this.salay = salay;
	}
}
