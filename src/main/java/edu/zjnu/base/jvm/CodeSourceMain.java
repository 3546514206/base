package edu.zjnu.base.jvm;

import java.security.CodeSource;

/**
 * @description: CodeSourceMain
 * @author: 杨海波
 * @date: 2022-06-06 14:30
 **/
public class CodeSourceMain {

    public static void main(String[] args) {
        CodeSource codeSource = CodeSourceMain.class.getProtectionDomain().getCodeSource();
        System.out.println(codeSource.getLocation().getFile());
    }
}
