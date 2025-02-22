package ClassLoader;

/*
 * 类加载器   原理 及其用法
 */
public class ClassLoaderDemo {
	public static void main(String[] args) {
		System.out.println("类加载器的关系");
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader().getParent());
		System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

		System.out.println(System.getProperty("java.class.path"));

		String a = "gqz";
		System.out.println(a.getClass().getClassLoader());//是由jre/lib/rt.jar包加载。
		System.out.println(a);

	}
}
