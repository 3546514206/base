package edu.zjnu.datastructure.string;

public class StringEqual {

    /**
     * 字符串比较
     */
    public static void main(String[] args) {
        // s1和s2被分配到永久区(方法区)的运行时常量池的相同的内存
        String s1 = "abc";
        String s2 = "abc";

        // s3和s4被分配到堆中的两个不同对象
        String s3 = "abc";
        String s4 = "abc";

        System.out.println(s1 == s2);
//		String重写了Object的equals方法
//		若是String类型的引用变量，即使两者引用不同，只要值相同，依旧认为相同
        System.out.println(s1.equals(s2));

        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));

        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));
    }

}
