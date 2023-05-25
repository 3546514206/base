#include <iostream>
using namespace std;

#define DECLARE_VAR(type, name) type name; type name ## _copy;

int main() {
    DECLARE_VAR(int, x);
    x = 10;
    x_copy = x;
    cout << "x = " << x << endl;
    cout << "x_copy = " << x_copy << endl;
    return 0;
}
