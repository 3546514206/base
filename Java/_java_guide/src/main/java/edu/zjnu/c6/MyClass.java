package edu.zjnu.c6;

// Public vs private access.
public class MyClass {
    public int beta;
    int gamma;
    private int alpha;

    int getAlpha() {
        return alpha;
    }

    /**
     * Methods to access alpha. It is OK for a member of a class
     * to access a private member of the same class.
     */
    void setAlpha(int a) {
        alpha = a;
    }
}
