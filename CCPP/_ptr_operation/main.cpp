#include <iostream>

int main() {

    int a[3] = {10, 200, 3000};

    int *ptr = a;

    for (int i = 0; i < sizeof(a) / sizeof(a[0]); ++i) {
        std::cout << *ptr << std::endl;
        ptr++;
    }

    return 0;
}
