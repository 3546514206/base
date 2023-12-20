package edu.zjnu.c7;

// A multilevel hierarchy.
public class TwoDShape7 {
    private double width;
    private double height;

    // A default constructor.
    TwoDShape7() {
        width = height = 0.0;
    }

    // Parameterized constructor.
    TwoDShape7(double w, double h) {
        width = w;
        height = h;
    }

    // Construct object with equal with and height
    TwoDShape7(double x) {
        width = height = x;
    }

    // Construct an object from an object.
    TwoDShape7(TwoDShape7 ob) {
        width = ob.width;
        height = ob.height;
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
