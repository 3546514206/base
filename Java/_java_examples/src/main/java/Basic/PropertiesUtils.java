package Basic;

import java.io.*;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author ganquanzhong
 * @ClassName: PropertiesUtils
 * @Description: Properties配置类
 * @date 2019年11月11日 上午11:51:34
 */
public class PropertiesUtils {
    private static String basePath = "src/com/gqz/javasys/properties/jdbc.properties";
    private static String path = "info.app.desc";
    private static Properties props;

    /**
     * 一、 使用java.util.Properties类的      load(InputStream in)方法加载properties文件
     * 通过getProperty(key获取属性值
     *
     * @return
     */
    public static Properties initProperties() {

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(new File(basePath)));
            props = new Properties();
            //加载properties文件
            props.load(in);
            path = props.getProperty(path);
        } catch (FileNotFoundException e) {
            System.out.println("properties文件路径书写有误，请检查！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    /**
     * 二、 使用java.util.ResourceBundle类的getBundle()方法  国家化操作
     * 注意：这个getBundle()方法的参数只能写成包路径+properties文件名，否则将抛异常
     *
     * @return
     */
    public static String getPath2() {
        ResourceBundle rb = ResourceBundle.getBundle("zh_CN");
        path = rb.getString("path");
        return path;
    }

    /**
     * 三、 使用java.util.PropertyResourceBundle类的构造函数
     *
     * @return
     */
    public static String getPath3() {
        InputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(basePath));
            ResourceBundle rb = new PropertyResourceBundle(in);
            path = rb.getString("path");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 四、 使用class变量的getResourceAsStream()方法
     * 注意：getResourceAsStream()方法的参数      按格式写到包路径+properties文件名+.后缀
     *
     * @return
     */
    public static String getPath4() {
        InputStream in = PropertiesUtils.class.getResourceAsStream("jdbc.properties");
        Properties p = new Properties();
        try {
            p.load(in);
            path = p.getProperty("path");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 五、
     * 使用class.getClassLoader()所得到的java.lang.ClassLoader的
     * getResourceAsStream()方法
     * getResourceAsStream(name)方法的参数      必须是包路径+文件名+.后缀
     * 否则会报空指针异常
     *
     * @return
     */
    public static String getPath5() {
        InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("/jdbc.properties");
        Properties p = new Properties();
        try {
            p.load(in);
            path = p.getProperty("path");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 六、 使用java.lang.ClassLoader类的getSystemResourceAsStream()静态方法
     * getSystemResourceAsStream()方法的参数格式也是有固定要求的
     *
     * @return
     */
    public static String getPath6() {
        InputStream in = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        Properties p = new Properties();
        try {
            p.load(in);
            path = p.getProperty("path");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return path;
    }

    public static void main(String[] args) {
        System.out.println(PropertiesUtils.initProperties().get("path"));
//        System.out.println(PropertiesUtils.getPath2());
        System.out.println(PropertiesUtils.getPath3());
        System.out.println(PropertiesUtils.getPath4());
//        System.out.println(PropertiesUtils.getPath5());
//        System.out.println(PropertiesUtils.getPath6());

        //获取Properties中键值对  key-value
        Properties properties = new Properties();
        properties = PropertiesUtils.initProperties();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            System.out.println(key + " : " + properties.get(key));
        }
    }
}
