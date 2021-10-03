package edu.zjnu.base.jvm.classloader;


import com.sun.xml.internal.ws.util.ByteArrayBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureClassLoader;

/**
 * @description: SalaryClassLoader
 * @author: 杨海波
 * @date: 2021-10-03
 **/
public class SalaryClassLoader extends SecureClassLoader {

    private String classPath;

    public SalaryClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public SalaryClassLoader(ClassLoader parent, String classPath) {
        super(parent);
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        FileInputStream fileInputStream = null;
        ByteArrayBuffer byteArray = new ByteArrayBuffer();
        byte[] bytes;
        int code;
        try {
            fileInputStream = new FileInputStream(new File(classPath));
            while ((code = fileInputStream.read()) != -1) {
                byteArray.write(code);
            }

            bytes = byteArray.toByteArray();

            return this.defineClass(name, bytes, 0, bytes.length);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
