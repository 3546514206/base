import org.reflections.Reflections;

import java.util.Set;
import java.util.UUID;

/**
 * todo
 *
 * @Date 2024-08-19 15:30
 * @Author 杨海波
 **/
public class Main {

    public static void main(String[] args) {
        // 指定要扫描的包名
        String packageName = "com.example.myapp";

        // 创建Reflections对象
        Reflections reflections = new Reflections(packageName);

        // // 获取所有被@MyAnnotation注解标记的类
        // Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(MyAnnotation.class);
        //
        // // 打印被注解标记的类
        // for (Class<?> clazz : annotatedClasses) {
        //     System.out.println("Found annotated class: " + clazz.getName());
        // }
    }
}
