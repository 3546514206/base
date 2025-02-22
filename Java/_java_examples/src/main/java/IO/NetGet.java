package IO;

import java.io.*;
import java.net.URL;

public class NetGet {

	public static void main(String[] args) {
		getURLRecourse("http://www.ganquanzhong.top", "C:/users/ganquanzhong/Desktop/1.html");
	}

	public static void getURLRecourse(String urlPath, String destPath) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new URL(urlPath).openStream()));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destPath)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
