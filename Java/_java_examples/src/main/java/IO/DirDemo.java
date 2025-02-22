package IO;

import java.io.File;

public class DirDemo {
	public static void main(String[] args) {
		File dir = new File("D:/java_workspace/IO_study");
//		boolean flag = src.mkdir();
//		System.out.println(flag);
		/*
		 * list() 列出下级对象
		 * listFiles() 列出下级File
		 */

		String[] subNames = dir.list();
		for (String s : subNames) {
			System.out.println(s);
		}

		File[] subFiles = dir.listFiles();
		for (File file : subFiles) {
			System.out.println(file.getName());
		}

		//列出盘符
		File[] roots = dir.listRoots();
		for (File s : roots) {
			System.out.println(s.getAbsolutePath());
		}
	}
}
