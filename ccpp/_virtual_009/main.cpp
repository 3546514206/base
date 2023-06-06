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

class Base3 {
public:
    virtual void F() { cout << "Base3 F()" << endl; }
};

class Derive : public Base1, public Base2, public Base3 {
public:
    virtual void A() { cout << "Derive A()" << endl; }           // 覆盖Base1::A()
    virtual void D() { cout << "Derive D()" << endl; }           // 覆盖Base2::D()
    virtual void MyA() { cout << "Derive MyA()" << endl; }
};

int main() {
    Derive *derive_ptr = new Derive;
    // 输出 24，多重继承会维护多个虚函数表
    cout << sizeof(*derive_ptr) << endl;
    derive_ptr->F();
    return 0;
}


