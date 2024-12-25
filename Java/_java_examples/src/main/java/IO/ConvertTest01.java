package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ConvertTest01 {
	/**
	 * 使用InputStreamReader 和 OutputStreamWriter 进行以字符流的方式处理字节流
	 */

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			String msg = "";
			while (!msg.equals("exit")) {
				msg = reader.readLine();
				writer.write(msg);
				writer.newLine();
				writer.flush();
			}
		} catch (Exception e) {
			System.out.println("转换异常");
		}

	}
}
