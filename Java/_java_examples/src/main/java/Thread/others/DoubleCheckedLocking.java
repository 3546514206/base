package com.gqz.others;

/*
 * 单例模式：懒汉式套路 在多线程环境下  对外存在一个对象
 * 1、构造器私有化 --> 避免外部 new构造器
 * 2、提供私有的属性  --> 存储对象的地址
 * 3、提供公共的静态方法--->获取属性
 */
public class DoubleCheckedLocking {
	//2、提供私有地属性
	//直接new对象了是饿汉式  没有new的事懒汉式
	//volatile其他线程可能访问一个没有初始化的对象
	private static volatile DoubleCheckedLocking instance;

	//1、构造器私有化
	private DoubleCheckedLocking() {

	}

	//3、提供公共的静态方法-->获取属性
	public static DoubleCheckedLocking getInstance() {
		//再次检测
		if (null != instance) { //避免不必要的同步，如果已经存在对象
			return instance;
		}
		//防止多个线程 需要加上锁 synchronized
		synchronized (DoubleCheckedLocking.class) {
			if (null == instance) {
				instance = new DoubleCheckedLocking();
				/** //创建对象  可能存在指令重排
				 * 1、开辟空间 2、初始化对象信息 3、返回对象的地址给引用
				 */
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println(DoubleCheckedLocking.getInstance());
		});
		thread.start();
		System.out.println(DoubleCheckedLocking.getInstance());
	}
}
