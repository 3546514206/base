package IO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest02 {
	public static void main(String[] args) throws IOException {
		// 分多少块
		File src = new File("src/com/gqz/io/Copy.java");
		// 总长度
		long len = src.length();
		// 每块大小
		int blockSize = 1024;
		// 块数：多少块
		int size = (int) Math.ceil(len * 1.0 / blockSize);
		//System.out.println(size);

		// 其实位置和实际大小
		int beginPos = 0;
		int actualSize = (int) (blockSize > len ? len : blockSize);

		for (int i = 0; i < size; i++) {
			beginPos = i * blockSize;// 位置
			if (i == size - 1) {// 最后一块
				actualSize = (int) len;
			} else {
				actualSize = blockSize;
				len -= actualSize; // 剩余量
			}
			System.out.println("第" + i + 1 + "块    起始位置-->" + beginPos + "读取大小-->" + actualSize);
			split(i, beginPos, actualSize);
		}

	}

	/**
	 * @param i
	 * @param beginPos   指定读入的位置
	 * @param actualSize 实际大小
	 * @throws IOException
	 * @Title: split
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author ganquanzhong
	 * @date 2019年7月1日 下午2:30:29
	 */
	public static void split(int i, int beginPos, int actualSize) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("src/com/gqz/io/Copy.java"), "r");
		raf.seek(beginPos);
		// 读取
		byte[] datas = new byte[1024];// 缓冲容器
		int len = -1;
		while ((len = raf.read(datas)) != -1) {
			if (actualSize >= len) {
				System.out.println(new String(datas, 0, len));
				actualSize -= len;
			} else {
				System.out.println(new String(datas, 0, actualSize));
				break;
			}
		}
		raf.close();
	}

	// 分块思想：起始、实际大小
	public static void test2() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("src/com/gqz/io/Copy.java"), "r");
		// 指定读入的位置
		int beginPos = 2;
		// 实际大小
		int actualSize = 1026;
		// 随机读
		raf.seek(beginPos);
		// 读取

		byte[] datas = new byte[1024];// 缓冲容器
		int len = -1;
		while ((len = raf.read(datas)) != -1) {
			if (actualSize >= len) {
				System.out.println(new String(datas, 0, len));
				actualSize -= len;
			} else {
				System.out.println(new String(datas, 0, actualSize));
				break;
			}
		}
		raf.close();
	}

	// 指定起始位置、读取剩余所有内容
	public static void test() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("src/com/gqz/io/Copy.java"), "r");

		// 随机读 指定读入的位置
		raf.seek(4);
		// 读取

		byte[] datas = new byte[201];// 缓冲容器
		int len = -1;
		while ((len = raf.read(datas)) != -1) {
			System.out.println(new String(datas, 0, datas.length));
		}
		raf.close();
	}
}
