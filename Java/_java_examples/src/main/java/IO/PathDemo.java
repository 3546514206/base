package IO;

import java.io.File;

public class PathDemo {
	public static void main(String[] args) {
		String pathString = "E:\\BaiduNetdiskDownload";
		System.out.println(File.separator);//名称分隔符
		System.out.println(File.pathSeparator);//路径分隔符
		System.out.println(File.pathSeparatorChar);
		System.out.println(File.separatorChar);
		System.out.println(pathString);
	}
}
