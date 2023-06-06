#include <iostream>

using namespace std;

class base_1 {
public:
    virtual void a() { cout << "base_1 a()" << endl; }

    virtual void b() { cout << "base_1 b()" << endl; }

    virtual void c() { cout << "base_1 c()" << endl; }
};

class derive : public base_1 {
public:
    int x;
    // void my_a() { cout << "derive my_a()" << endl; }
};

int main() {

    typedef void (*func)();

    base_1 *base_1_ptr = new base_1;
    derive *derive_ptr = new derive;
    cout << sizeof(*base_1_ptr) << endl;
    cout << sizeof(*derive_ptr) << endl;

    long *v_derive_ptr = (long *) derive_ptr;
    func my_a_ptr = (func) v_derive_ptr;

    my_a_ptr();
    return 0;
}
