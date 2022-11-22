#include <iostream>

class base {
public:
    void func(int x) {
        std::cout << "int x= " << x << std::endl;
    }

    // 方法重载(overload)
    void func(double x) {
        std::cout << "double x=" << x << std::endl;
    }

    // 会被重写的方法
    virtual void test_override() {
        std::cout << "Base::test_override(void)" << std::endl;
    }
};

//重写是指派生类函数重写基类函数，是C++的多态的表现，特征是：
//（1）不同的范围（分别位于派生类与基类）；
//（2）函数名字相同；
//（3）参数相同；
//（4）基类函数必须有virtual关键字。

// public 继承
class derived : public base {
public:
    virtual void test_override() {
        std::cout << "Derived::g(void)" << std::endl;
    }
};


int main() {
    derived  d;
    base *pb = &d;
    pb->func(42);        // Base::f(int) 42
    pb->func(3.14f);     // Base::f(float) 3.14
    pb->test_override();          // Derived::g(void)

    return 0;
}
