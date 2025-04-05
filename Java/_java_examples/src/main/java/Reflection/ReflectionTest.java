package Reflection;

import com.gqz.reflect.bean.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {

	public static void main(String[] args) {
		//获取Student的Class对象
		Class<?> clazz;
		try {
			clazz = Class.forName("com.gqz.Student");
			//获取该类中所有的属性
			Field[] fields = clazz.getDeclaredFields();

			//遍历所有的属性
			for (Field field : fields) {
				//打印属性信息，包括访问控制修饰符，类型及属性名
				System.out.println(field);
				System.out.println("修饰符：" + Modifier.toString(field.getModifiers()));
				System.out.println("类型：" + field.getType());
				System.out.println("属性名：" + field.getName());
			}

			//获取该类中的所有方法
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				//打印方法签名
				System.out.println(method);
				System.out.println("修饰符：" + Modifier.toString(method.getModifiers()));
				System.out.println("方法名：" + method.getName());
				System.out.println("返回类型：" + method.getReturnType());
				//获取方法的参数对象
				Class<?>[] clazzes = method.getParameterTypes();
				for (Class<?> class1 : clazzes) {
					System.out.println("参数类型：" + class1);
				}
			}

			//通过Class对象创建实例
			Student student = (Student) clazz.newInstance();

			//获取属性名为studentName的字段(Field)对象，以便下边重新设置它的值
			Field field = clazz.getDeclaredField("studentName");
			field.setAccessible(true);
			field.set(student, "张三");
			System.out.println("-----------");
//			Field studentName = clazz.getField("studentName");
//			
//			//设置studentName的值为"张三"
//			studentName.set(student, "张三");


			//通过Class对象获取名为"finishTask"，参数类型为String的方法(Method)对象
			Method finishTask = clazz.getDeclaredMethod("finishTask", String.class);
//			Method finishTask = clazz.getMethod("finishTask", String.class);
			//setAccessible
			finishTask.setAccessible(true);
			//调用finishTask方法
			finishTask.invoke(student, "数学");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
