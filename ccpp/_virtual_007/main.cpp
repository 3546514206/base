#include <iostream>
#include <stdio.h>

using namespace std;

class base {
public:
    virtual void a() { cout << "base a()" << endl; }

    virtual void b() { cout << "base b()" << endl; }

    virtual void c() { cout << "base c()" << endl; }
};

class derive : public base {
public:
    virtual void b() { cout << "derive b()" << endl; }
};

int main() {
    typedef void (*func)();
    cout << "-----------base------------" << endl;
    base *base_ptr = new base;
    long *tmp_base_ptr = (long *) base_ptr;
    long *v_base_ptr = (long *) (*tmp_base_ptr);
    for (int i = 0; i < 3; i++) {
        printf("v_base_ptr[%d] : %p\n", i, v_base_ptr[i]);
    }
    func a = (func) v_base_ptr[0];
    func b = (func) v_base_ptr[1];
    func c = (func) v_base_ptr[2];
    a();
    b();
    c();

    derive *derive_ptr = new derive;
    long *tmp_derive_ptr = (long *) derive_ptr;
    long *v_derive_ptr = (long *) (*tmp_derive_ptr);
    cout << "---------derive------------" << endl;
    for (int i = 0; i < 3; i++) {
        printf("v_derive_ptr[%d] : %p\n", i, v_derive_ptr[i]);
    }
    func d = (func) v_derive_ptr[0];
    func e = (func) v_derive_ptr[1];
    func f = (func) v_derive_ptr[2];
    d();
    e();
    f();


    return 0;
}
