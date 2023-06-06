#include <iostream>

using namespace std;

class Base {
public:
    virtual void a() { cout << "Base a()" << endl; }

    virtual void b() { cout << "Base b()" << endl; }

    virtual void c() { cout << "Base c()" << endl; }
};

class Derive : public Base {
public:
    virtual void b() { cout << "Derive b()" << endl; }
};

int main() {
    cout << "-----------Base------------" << endl;
    Base *base_ptr = new Base;
    long *tmp_base_ptr = (long *) base_ptr;
    long *v_base_ptr = (long *) (*tmp_base_ptr);
    for (int i = 0; i < 3; i++) {
        printf("v_base_ptr[%d] : %p\n", i, v_base_ptr[i]);
    }

    Derive *derive_ptr = new Derive;
    long *tmp_derive_ptr = (long *) derive_ptr;
    long *v_derive_ptr = (long *) (*tmp_derive_ptr);
    cout << "---------Derive------------" << endl;
    for (int i = 0; i < 3; i++) {
        printf("v_derive_ptr[%d] : %p\n", i, v_derive_ptr[i]);
    }
    return 0;
}
