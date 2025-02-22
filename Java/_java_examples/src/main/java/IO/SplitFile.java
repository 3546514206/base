package IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SplitFile {

	// 分割文件源头
	private File src;
	// 目标文件
	private String destDir;
	// 所有分割文件的存储路径List
	private List<String> destPaths;
	// 每块大小
	private int blockSize;
	// 块数
	private int size;

	public SplitFile(String src, String destDir, int blockSize) {
		this.src = new File(src);
		this.destDir = destDir;
		this.blockSize = blockSize;
		this.destPaths = new ArrayList<String>();
		init();// 初始化
	}

	public static void main(String[] args) throws IOException {
		SplitFile sf = new SplitFile("src/com/gqz/io/CopyTxt.java", "dest", 1024);
		sf.mergeBySequenceInputStream("abd123.java");
	}

	// 初始化操作
	private void init() {
		// 总长度
		long len = this.src.length();
		// 块数
		this.size = (int) Math.ceil(len * 1.0 / blockSize);
		// 处理路径
		for (int i = 0; i < size; i++) {
			this.destPaths.add(this.destDir + "/" + (i + 1) +
					this.src.getName().substring(this.src.getName().lastIndexOf("."), this.src.getName().length())
			);
		}
	}

	/*
	 * 计算机每块的起始位置和大小
	 */
	public void split() {
		// 其实位置和实际大小
		long len = src.length();
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
			try {
				splitDetail(i, beginPos, actualSize);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	private void splitDetail(int i, int beginPos, int actualSize) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(this.src, "r");
		RandomAccessFile raf2 = new RandomAccessFile(new File(this.destPaths.get(i)), "rw");
		raf.seek(beginPos);
		// 读取
		byte[] datas = new byte[1024];// 缓冲容器
		int len = -1;
		while ((len = raf.read(datas)) != -1) {
			if (actualSize >= len) {
				raf2.write(datas, 0, len);
				actualSize -= len;
			} else {
				raf2.write(datas, 0, actualSize);
				break;
			}
		}
		raf.close();
		raf2.close();
	}

	/*
	 * 合并 merge
	 */
	public void merge(String destPath) throws IOException {
		//输出流
		OutputStream os = new BufferedOutputStream(new FileOutputStream(destPath, true));
		//输入流
		for (int i = 0; i < this.destPaths.size(); i++) {
			InputStream is = new BufferedInputStream(new FileInputStream(this.destPaths.get(i)));
			byte[] datas = new byte[1024];
			int len = -1;
			while ((len = is.read(datas)) != -1) {
				os.write(datas, 0, datas.length);
			}
			os.flush();
			is.close();
		}
		os.close();
	}

	public void mergeBySequenceInputStream(String destPath) throws IOException {
		//输出流
		OutputStream os = new BufferedOutputStream(new FileOutputStream(destPath, true));
		//输入流
		Vector<InputStream> vi = new Vector<InputStream>();
		SequenceInputStream sis = null;
		for (int i = 0; i < this.destPaths.size(); i++) {
			vi.add(new BufferedInputStream(new FileInputStream(this.destPaths.get(i))));
		}
		sis = new SequenceInputStream(vi.elements());
		byte[] datas = new byte[1024];
		int len = -1;
		while ((len = sis.read(datas)) != -1) {
			os.write(datas, 0, datas.length);
		}
		os.flush();
		sis.close();
		os.close();
	}

}
