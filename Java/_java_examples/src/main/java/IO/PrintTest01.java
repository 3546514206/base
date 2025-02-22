package IO;

import java.io.*;

public class PrintTest01 {
	/*
	 * PrintStream 打印流
	 */

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream ps = System.out;
		ps.println("人生苦短");
		ps.println(true);
		ps.println('A');

		ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("print.txt")), true);

		ps.println('B');
		ps.println("我用python");

		//重定向输出端
		System.setOut(ps);
		System.out.println("change output");

		//重定回控制端 console
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
		System.out.println("i am backing....");

	}
}
