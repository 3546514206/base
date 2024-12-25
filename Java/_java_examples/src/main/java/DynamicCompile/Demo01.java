package DynamicCompile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Demo01 {
	public static void main(String[] args) throws IOException {
		// 通过IO流操作，将字符串存储成一个临时文件（HelloWorld.java）,然后调用动态编译
		String str = "public class HelloWorld{public static void main(String[] args) {System.out.println(\"HelloWorld\");}}";
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:/users/ganquanzhong/desktop/HelloWorld.java")));
		bw.write(str);
		bw.close();
		System.out.println("程序代码写入成功！");
		// 获取编译工具
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 编译成功返回0
		int result = compiler.run(null, null, null, "c:/users/ganquanzhong/desktop/HelloWorld.java");
		System.out.println(result == 0 ? "编译成功" : "编译失败");

		// 通过Runtime.getRuntime()运行启动新的进程
		Runtime run = Runtime.getRuntime();

		Process process = run.exec("java -cp  c:/users/ganquanzhong/desktop  HelloWorld");
		System.out.println(process);
		InputStream in = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String info = "";
		while ((info = reader.readLine()) != null) {
			System.out.println(info);
		}

		// 通过反射运行编译好的类 ClassLoader
		try {
			//指定文件目录
			URL[] urls = new URL[]{new URL("file:/" + "c:/users/ganquanzhong/desktop/")};
			//创建类加载器
			URLClassLoader loader = new URLClassLoader(urls);
			//加载指定的类
			Class clazz = loader.loadClass("HelloWorld");
			Method method = clazz.getMethod("main", String[].class);
			//invoke的第一个参数为对象。由于main方法是静态方法，所以为null。后面是参数
			method.invoke(null, (Object) new String[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
