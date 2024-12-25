package IO;

import java.io.*;

public class CopyTxt {
	/**
	 * @param srcPath
	 * @param destPath
	 * @Title: copy
	 * @Description: copy文件拷贝 字符
	 * @author ganquanzhong
	 * @date 2019年6月27日 下午3:22:09
	 */
	public static void copy(String srcPath, String destPath) {
		// 1、创建源
		File src = new File(srcPath);//源头
		File dest = new File(destPath);//目的地
		// 2、选择流
		BufferedReader br = null; // 输入流 原文件
		BufferedWriter bw = null; // 输出文件 复制文件
		//3、操作  文件拷贝
		try {
			br = new BufferedReader(new FileReader(src));
			bw = new BufferedWriter((new FileWriter(dest)));
			String line = null;
			while ((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();
			}
			// 逐行读取
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 4、释放资源 先打开的后关闭
			try {
				if (null != bw) {
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != br) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	//测试
	public static void main(String[] args) {
		copy("a.txt", "copy1.txt");
	}
}

