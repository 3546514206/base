package edu.zjnu.base.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

/**
 * @description: ReaderMain
 * @author: 杨海波
 * @date: 2021-10-13
 **/
public class ReaderMain {

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = StreamMain.class.getClassLoader();
        // 可以二进制文本编辑器打开这个文件然后对照着看
        URL resource = classLoader.getResource("relay-bin.000010");
        assert resource != null;
        String path = resource.getPath();

        // 定义源文件
        File file = new File(path);
        Reader reader = new FileReader(file);


        // 定义字符数组，接收读取到的源文件字符内容
        char[] chars = new char[1024];
        while (reader.read(chars) != -1) {
            System.out.println(chars);
        }

        reader.close();

    }
}
