#include <iostream>

class Base {
public:
    virtual ~Base() {}
};

class Derived : public Base {
};

int main() {
    Base* ptr = new Derived();

    if (typeid(*ptr) == typeid(Derived)) {
        std::cout << "ptr is an instance of Derived class." << std::endl;
    } else {
        std::cout << "ptr is not an instance of Derived class." << std::endl;
    }

    delete ptr;

    return 0;
}
