package edu.zjnu.c7;

// Use accessor methods to set and get private members.

// A class for two-dimensional objects.
public class TwoDShape2 {
    private double width;
    private double height;

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
