package IO;

import java.io.File;

public class DirDemo02 {
	public static Long len = 0L;

	public static void main(String[] args) {
		File src = new File("D:/java_workspace/IO_study");
		//printName(src,0);
		count(src);
		System.out.println(len);
	}

	//递归输出文件
	private static void printName(File src, int deep) {
		System.out.println(src.getName());
		for (int i = 0; i < deep; i++) {
			System.out.print("--");
		}
		if (null == src || !src.exists()) {
			return;
		} else if (src.isDirectory()) {
			for (File s : src.listFiles()) {
				printName(s, deep + 1);
			}
		}
	}

	//递归统计文件夹大小
	private static void count(File src) {
		if (null != src && src.exists()) {
			if (src.isFile()) {
				len += src.length();
			} else {
				//子孙级
				for (File s : src.listFiles()) {
					//System.out.println("文件路径"+src.getPath()+"  文件大小"+src.length());
					count(s);
				}
			}
		}
	}
}
