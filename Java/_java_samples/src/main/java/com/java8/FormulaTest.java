package com.java8;

/**
 * @author landyl
 * @create 9:27 AM 03/01/2018
 */
public class FormulaTest {
    public static void main(String[] args) {
        //The formula is implemented as an anonymous object.
        //The code is quite verbose: 6 lines of code for such a simple calucation of sqrt(a * 100).
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(formula.calculate(100));     // 100.0
        System.out.println(formula.sqrt(16));           // 4.0
    }
}
