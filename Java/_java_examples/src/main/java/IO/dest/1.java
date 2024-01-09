package com.gqz.io;

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
