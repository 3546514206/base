package Javaassist;

import javassist.*;

import java.io.IOException;

/*
 * 测试  javaassist 生成一个新的类   操作级别 源码
 */
public class Demo01 {
	public static void main(String[] args) {

		ClassPool pool = ClassPool.getDefault();
		//源码级别的操作

		//创建类
		CtClass cc = pool.makeClass("com.gqz.javaassist.bean.Emp");

		//创建属性
		try {
			CtField field1 = CtField.make("private int id;", cc);
			CtField field2 = CtField.make("private String name;", cc);
			cc.addField(field1);
			cc.addField(field2);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//创建方法
		try {
			CtMethod method2 = CtMethod.make("public void setId(int id){this.id = id;}", cc);
			CtMethod method1 = CtMethod.make("public int getId(){return id;}", cc);

			CtMethod method3 = CtMethod.make("public void setName(String name){this.name = name;}", cc);
			CtMethod method4 = CtMethod.make("public String getName(){return name;}", cc);

			CtMethod method5 = CtMethod.make("public static void main(String[] args){System.out.println(\"this is a 动态编译程序！\");}", cc);

			cc.addMethod(method1);
			cc.addMethod(method2);
			cc.addMethod(method3);
			cc.addMethod(method4);
			cc.addMethod(method5);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建构造器
		try {
			CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("ClassLoader.String")}, cc);
			constructor.setBody("{this.id= id; this.name =name;}");
			cc.addConstructor(constructor);
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//写入到指定的位置
		try {
			cc.writeFile("c:/users/ganquanzhong/desktop");
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("编译成功！");
	}
}
