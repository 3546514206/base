#include<iostream>

using namespace std;

class Figure {
public:
    virtual double getArea() = 0;
};

const double PI = 3.14;

class Circle : public Figure {
private:
    double Radius;

public:
    Circle(double R) {
        Radius = R;
    }

    double getArea() {
        return Radius * Radius * PI;
    }
};

class Rectangle : public Figure {
protected:
    double Height, Width;

public:
    Rectangle(double height, double width) {
        Height = height;
        Width = width;
    }

    double getArea() {
        return Height * Width;
    }
};

int main() {
    Figure *fg1;
    fg1 = new Rectangle(4.0, 5.0);
    cout << fg1->getArea() << endl;
    delete fg1;
    Figure *fg2;
    fg2 = new Circle(4.0);
    cout << fg2->getArea() << endl;
    delete fg2;
    return 0;
}