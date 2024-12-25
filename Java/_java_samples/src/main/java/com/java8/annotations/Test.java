package com.java8.annotations;

/**
 * @author landyl
 * @create 5:57 PM 03/02/2018
 * Although we never declared the @Hints annotation on the Person class, it's still readable via getAnnotation(Hints.class).
 * However, the more convenient method is getAnnotationsByType which grants direct access to all annotated @Hint annotations.
 */
public class Test {
    public static void main(String[] args) {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);          // 2
    }
}
