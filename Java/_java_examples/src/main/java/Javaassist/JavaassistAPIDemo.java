package Javaassist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/*
 * 测试javaassist的API使用
 */
@SuppressWarnings("all")
public class JavaassistAPIDemo {
	public static void test01() throws NotFoundException, IOException, CannotCompileException {

		// 获得类池
		ClassPool pool = ClassPool.getDefault();
		// 获取指定的编译时类
		CtClass cc = pool.get("Javaassist.Emp");

		byte[] bytes = cc.toBytecode();
		System.out.println(Arrays.toString(bytes));
		System.out.println(cc.getName());
		System.out.println(cc.getSimpleName());
		System.out.println(cc.getMethods());
		System.out.println(cc.getSuperclass());
		System.out.println(cc.getInterfaces());
	}

	/*
	 * 测试产生新的方法
	 */
	public static void test02() throws NotFoundException, IOException, CannotCompileException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {

		// 获得类池
		ClassPool pool = ClassPool.getDefault();
		// 获取指定的编译时类
		CtClass cc = pool.get("Javaassist.Emp");

//		CtMethod method = CtMethod.make("public void add(int a,int b){ return a+b;}", cc);
		CtMethod method = new CtMethod(CtClass.intType, "add", new CtClass[]{CtClass.intType, CtClass.intType}, cc);
		method.setModifiers(Modifier.PUBLIC);
		method.setBody("{System.out.println(\"hello this is use javacomplier\");return $1+$2;}");
		cc.addMethod(method);

		// 通过反射调用新生成的方法
		// 将CtClass转为Class对象
		Class clazz = cc.toClass();
		Object obj = clazz.newInstance();// 调用无参构造器,创建Emp对象
		Method method2 = clazz.getDeclaredMethod("add", int.class, int.class);
		Object result = method2.invoke(obj, 200, 456);
		System.out.println(result);
	}

	public static void test03() throws Exception {

		// 获得类池
		ClassPool pool = ClassPool.getDefault();
		// 获取指定的编译时类
		CtClass cc = pool.get("Javaassist.Emp");
		CtMethod ctMethod = cc.getDeclaredMethod("sayHello", new CtClass[]{CtClass.intType});
		ctMethod.insertBefore("System.out.println(\"before  start\"+$1);");
		ctMethod.insertAfter("System.out.println(\"after end\"+$1);");
//		ctMethod.insertAt(lineNum, src); //指定到某一行
		Class clazz = cc.toClass();
		Object obj = clazz.newInstance();// 调用无参构造器,创建Emp对象
		Method method2 = clazz.getDeclaredMethod("sayHello", int.class);
		Object result = method2.invoke(obj, 456);
	}


	public static void test04() throws Exception {

		// 获得类池
		ClassPool pool = ClassPool.getDefault();
		// 获取指定的编译时类
		CtClass cc = pool.get("Javaassist.Emp");
	}

	public static void main(String[] args) throws Exception {
		test03();
	}
}
