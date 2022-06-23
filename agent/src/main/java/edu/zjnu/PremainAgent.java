package edu.zjnu;

import java.io.UnsupportedEncodingException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @description: PremainAgent
 * @author: 杨海波   -javaagent:/Users/SetsunaYang/Documents/learning/learning/agent/target/agent-0.0.1-SNAPSHOT.jar
 * @date: 2022-06-23 09:30
 **/
public class PremainAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new CustomClassTransformer(), true);
        System.out.println("静态agent已加载");
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new CustomClassTransformer(), true);
        System.out.println("动态agent已加载");
    }


    static class CustomClassTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader,
                                String className,
                                Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain,
                                byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("加载类型：" + className);
            System.out.println("类加载器：" + loader);
            try {
                System.out.println("字节码文件：" + new String(classfileBuffer,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return classfileBuffer;
        }
    }
}
