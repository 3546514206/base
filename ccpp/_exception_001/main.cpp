#include <iostream>

using namespace std;

int divide(int x, int y) {
    if (y == 0) {
        throw "divided by zero";
    }

    return x / y;
}

int main() {

    int x = 10, y = 0, z = 0;

    try {
        z = divide(x, y);
        cout << "Result: " << z << endl;
    }
    catch (const char *msg) {
        cerr << "Error: " << msg << endl;
    }

    return 0;
}
