package edu.zjnu.base.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-10-13
 **/
public class StreamMain {

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = StreamMain.class.getClassLoader();
        // 可以二进制文本编辑器打开这个文件然后对照着看
        URL resource = classLoader.getResource("AddTwoNumbers");
        assert resource != null;
        String path = resource.getPath();
        InputStream inputStream = new FileInputStream(new File(path));
        int value;
        while ((value = inputStream.read()) != -1) {
            System.out.println(Integer.toHexString(value));
        }
    }
}
