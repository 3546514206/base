package Reflection;

import com.gqz.reflect.bean.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("all")
public class Demo01 {
	public static void main(String[] args) {
		String path = "com.gqz.reflect.bean.User";

		try {
			Class<User> clazz = (Class<User>) Class.forName(path);

			System.out.println(clazz.hashCode());

			//通过反射API调用构造方法，构造对象
			System.out.println("直接使用newInstance()调用无参构造方法实例");
			User user = clazz.newInstance();//调用无参构造器
			System.out.println(user);

			System.out.println("调用指定构造方法实例");
			Constructor<User> constructor = clazz.getDeclaredConstructor(int.class, String.class, int.class);//获取指定的构造方法
			User user2 = constructor.newInstance(12, "甘全中", 32);
			System.out.println(user2 + "   name:" + user2.getName());


			//通过反射API调用普通方法
			User user3 = clazz.newInstance();
			Method method = clazz.getDeclaredMethod("setName", String.class);
			method.invoke(user3, "ganquanzhong"); //使用invoke
			System.out.println(user3.getName());

			//通过反射API操作属性
			User user4 = clazz.newInstance();
			Field fieldName = clazz.getDeclaredField("name");//获取属性name

			fieldName.setAccessible(true);//不能访问私有属性 通过setAccessible设置
			fieldName.set(user4, "chenrong");//通过反射直接写属性
			System.out.println(fieldName.get(user4));//通过反射直接获取属性

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
