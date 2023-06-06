#include <iostream>

using namespace std;

class Base1 {
public:
    virtual void A() { cout << "Base1 A()" << endl; }

    virtual void B() { cout << "Base1 B()" << endl; }

    virtual void C() { cout << "Base1 C()" << endl; }
};

class Base2 {
public:
    virtual void D() { cout << "Base2 D()" << endl; }

    virtual void E() { cout << "Base2 E()" << endl; }
};

class Derive : public Base1, public Base2 {
public:
    virtual void A() { cout << "Derive A()" << endl; }           // 覆盖Base1::A()
    virtual void D() { cout << "Derive D()" << endl; }           // 覆盖Base2::D()
    virtual void MyA() { cout << "Derive MyA()" << endl; }
};

int main() {
    typedef void (*Func)();
    Derive d;
    Base1 &b1 = d;
    Base2 &b2 = d;
    cout << "Derive对象所占的内存大小为：" << sizeof(d) << endl;

    cout << "\n---------第一个虚函数表-------------" << endl;
    long *tmp1 = (long *) &d;              // 获取第一个虚函数表的指针
    long *vptr1 = (long *) (*tmp1);         // 获取虚函数表

    Func x1 = (Func) vptr1[0];
    Func x2 = (Func) vptr1[1];
    Func x3 = (Func) vptr1[2];
    Func x4 = (Func) vptr1[3];
    x1();
    x2();
    x3();
    x4();

    cout << "\n---------第二个虚函数表-------------" << endl;
    long *tmp2 = tmp1 + 1;               // 获取第二个虚函数表指针 相当于跳过4个字节
    long *vptr2 = (long *) (*tmp2);

    Func y1 = (Func) vptr2[0];
    Func y2 = (Func) vptr2[1];
    y1();
    y2();

    return 0;
}