#include <iostream>

#include <iostream>

using namespace std;

class Box {
public:
    double length;
    double breadth;
    double height;

    double get(void) {
        return length * breadth * height;
    }

    void set(double len, double bre, double hel) {
        this->height = len;
        this->breadth = bre;
        this->height = hel;
    }
};

int main() {
    Box box;
    box.height = 1;
    box.breadth = 2;
    box.length = 3;

    std::cout << "体积=" << box.get() << "\n";

    return 0;
}
