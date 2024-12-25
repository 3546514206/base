package edu.zjnu.c7;

// Add constructors to the class.
public class TwoDShape4 {
    private double width;
    private double height;

    TwoDShape4(double w, double h) {
        width = w;
        height = h;
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