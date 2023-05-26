#include <iostream>

class Base {
public:
    virtual void foo() { std::cout << "Base::foo()" << std::endl; }
};

class Derived : public Base {
public:
    void foo() override { std::cout << "Derived::foo()" << std::endl; }
};

int main() {
    Derived obj;
    Base *ptr = &obj;
    ptr->foo();
    return 0;
}
