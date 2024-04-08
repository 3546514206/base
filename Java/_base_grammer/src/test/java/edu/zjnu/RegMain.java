package edu.zjnu;

/*
 * @Author 杨海波
 *
 * @Date 2024-04-08 下午4:59
 * @Description TODO
 **/
public class RegMain {

    public static void main(String[] args) {
        String input = "This is a \n test\r with spaces";

        // 使用正则表达式替换 \n、\r 和空格
        String output = input.replaceAll("[\\n\\r\\s]", "");

        System.out.println("原始字符串：" + input);
        System.out.println("替换后的字符串：" + output);

    }
}
