package com.java8.constructor;

/**
 * @author landyl
 * @create 10:58 AM 03/01/2018
 */
public class LambdaConstructorTest {
    public static void main(String[] args) {
        // Method and Constructor References ×××××××××××××××××××××××××××××××××××××××


        // We create a reference to the Person constructor via Person::new.
        // The Java compiler automatically chooses the right constructor by matching the signature of PersonFactory.create.
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

        System.out.println(person.firstName);

        // Method and Constructor References ×××××××××××××××××××××××××××××××××××××××
    }
}
