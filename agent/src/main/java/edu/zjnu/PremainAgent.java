package edu.zjnu;

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
        System.out.println("agentArgs : " + agentArgs);
        inst.addTransformer(new CustomClassTransformer(), true);
    }

    static class CustomClassTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader,
                                String className,
                                Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain,
                                byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain load class !!!");
            return classfileBuffer;
        }
    }
}
