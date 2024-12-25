package Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 使用反射读取注解的信息，模拟处理注解信息的流程
 *
 * @author ganquanzhong
 * @ClassName: AnalysisTableAnnotation
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月17日 下午1:50:14
 */

@SuppressWarnings(value = "all")
public class AnalysisTableAnnotation {
	public static void main(String[] args) {

		try {
			Class clazz = Class.forName("Annotation.Student");

			//使用isAnnotationPresent方法判断一个类是否使用指定的注解
			System.out.println("使用isAnnotationPresent方法判断一个类是否使用指定的注解");
			boolean isAnnotation = clazz.isAnnotationPresent(TestAnnotation01.class);
			System.out.println(isAnnotation);

			//返回注解到这个元素上的所有注解
			System.out.println("getAnnotation返回注解到这个元素上的所有注解");
			Annotation[] annotations = clazz.getAnnotations();
			for (Annotation a : annotations) {
				System.out.println(a);
			}

			//返回指定类型的注解
			System.out.println("getAnnotation返回指定类型的注解");
			Table table = (Table) clazz.getAnnotation(Table.class);
			System.out.println(table.value());


			//获得类的属性注解信息
			System.out.println("getDeclaredField获得类的属性注解信息");
			Field f = clazz.getDeclaredField("id");
			TableField field = f.getAnnotation(TableField.class);
			System.out.println(field.type() + "------>" + field.columnName() + "----->" + field.length());

			f = clazz.getDeclaredField("name");
			field = f.getAnnotation(TableField.class);
			System.out.println(field.type() + "------>" + field.columnName() + "----->" + field.length());

			f = clazz.getDeclaredField("age");
			field = f.getAnnotation(TableField.class);
			System.out.println(field.type() + "------>" + field.columnName() + "----->" + field.length());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
