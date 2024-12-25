package edu.zjnu.c7;

// A multilevel hierarchy.
public class TwoDShape6 {
    private double width;
    private double height;

    // A default constructor.
    TwoDShape6() {
        width = height = 0.0;
    }

    // Parameterized constructor.
    TwoDShape6(double w, double h) {
        width = w;
        height = h;
    }

    // Construct object with equal with and height
    TwoDShape6(double x) {
        width = height = x;
    }

    // Accessor methods for with and height.
    double getWidth() {
        return width;
    }

    void setWidth(double w) {
        width = w;
    }

    double getHeight() {
        return height;
    }

    void setHeight(double h) {
        height = h;
    }

    void showDim() {
        System.out.println("Width and height are " + width + " and " + height);
    }
}
