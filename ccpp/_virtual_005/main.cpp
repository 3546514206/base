#include <iostream>

class A {
public:
    int x;

    virtual void f() {}
};


int main() {

    auto *p = new A;
    // std::cout << p << std::endl;
    std::cout << sizeof(p) << std::endl;
    std::cout << sizeof(*p) << std::endl;
    std::cout << sizeof(p->x) << std::endl;
    // std::cout << &(p->x) << std::endl;

    return 0;
}
