package ClassLoader;

/**
 * @author ganquanzhong
 * @ClassName: LoaderDemo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月18日 下午5:09:56
 */
public class LoaderDemo {
	static {
		System.out.println("This is LoaderDemo Static");
	}

	public LoaderDemo() {
		System.out.println("This is LoaderDemo Constructor");
	}

	public static void main(String[] args) {
		System.out.println("This is LoaderDemo main method");

		new A();
		//主动引用
		System.out.println(A.MAX);

		System.out.println(A.MAX);

	}
}

class A extends B {
	public static final String MAX = "引用final不会初始化类,直接从常量池中获取！";
	public static String aName = "a";

	static {
		System.out.println("This is A Static");
	}

	String str = "a";

	{
		System.out.println("This is A Block");
	}

	public A() {
		System.out.println("This is A Constructor");
	}
}

class B {
	//类变量和类方法 都作为<clinit> 类的构造方法
	static String bName = "b";

	static {
		System.out.println("This is B Static");
	}

	{
		System.out.println("This is B Block");
	}

	public B() {
		System.out.println("This is B Constructor");
	}

}

