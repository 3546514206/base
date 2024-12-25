package IO;

import java.io.File;

/**
 * @author ganquanzhong
 * @ClassName: DirCount
 * @Description: 统计文件夹的大小
 * @date 2019年6月27日 上午9:20:42
 */
public class DirCount {
	private String path;//文件路径
	private File src;//文件源
	private Long len = 0L;//文件大小

	private String currentPath;

	private int fileSize = -1;
	private int dirSize = -1;

	public DirCount(String path) {
		this.path = path;
		this.src = new File(path);
		count(this.src);
	}

	public static void main(String[] args) {
		DirCount dir = new DirCount("D:\\java_workspace\\IO_study");
		System.out.println("文件大小" + dir.getLen());
		System.out.println("文件个数" + dir.getFileSize());
		System.out.println("文件夹个数" + dir.getDirSize());

	}

	//统计大小
	private void count(File src) {
		if (null != src && src.exists()) {
			if (src.isFile()) {//是文件
				len += src.length();
				this.fileSize++;
				this.currentPath = src.getAbsolutePath();
			} else {
				//是文件夹
				this.dirSize++;
				for (File s : src.listFiles()) {
					count(s);
				}
			}
		}
	}

	public Long getLen() {
		return len;
	}

	public int getFileSize() {
		return fileSize;
	}

	public int getDirSize() {
		return dirSize;
	}

}
