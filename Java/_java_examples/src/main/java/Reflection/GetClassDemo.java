package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("all")
public class GetClassDemo {
	public static void main(String[] args) {

		String path = "com.gqz.reflect.bean.User";

		try {
			Class clazz = Class.forName(path);

			System.out.println(clazz);
			System.out.println(clazz.getName());//包名+类名
			System.out.println(clazz.getSimpleName());
			Field[] field = clazz.getDeclaredFields();
			for (Field f : field) {
				System.out.println(f);
			}

			//获取方法信息
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				System.out.println(method);
			}
			Method method1 = clazz.getDeclaredMethod("setName", String.class);
			System.out.println(method1);
			Method method2 = clazz.getDeclaredMethod("getName", null);
			System.out.println(method2);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
