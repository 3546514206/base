package edu.zjnu.c6;

// Use a static variable.
public class StaticDemo {
    static int y; // a static variable
    int x; // a normal instance variable

    // Return the sum of the instance variable x
    // and the static variable y.
    int sum() {
        return x + y;
    }
}
