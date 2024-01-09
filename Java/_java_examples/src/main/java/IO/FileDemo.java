package IO;

import java.io.File;
import java.io.IOException;

/**
 * @author ganquanzhong
 * @ClassName: FileDemo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年6月26日 下午2:29:38
 */
public class FileDemo {
	public static void main(String[] args) throws IOException {
		String pathString = "E:/DevFile/a.txt";
		//构建File对象  
		//mkdir 创建文件夹    createNewFile() 创建文件
		File file = new File(pathString);
		boolean flag = file.createNewFile();
		System.out.println("是否创建成功===" + flag);

//		boolean delete = file.delete();
//		System.out.println("删除:"+delete);

		System.out.println("长度" + file.length());
		System.out.println(file.getName());
		System.out.println(file.getPath());
		System.out.println(file.getParent());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.exists());
		System.out.println("是否是文件" + file.isFile());
		System.out.println("是否是文件夹" + file.isDirectory());

		System.out.println(System.getProperty("user.dir"));
//		System.out.println(pathString);
	}
}
