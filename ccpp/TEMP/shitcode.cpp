#include <iostream>
#define CALL_WITH_MAX(a, b) ((a) > (b) ? (a) : (b))

int main()
{

    int a1 = 5, b1 = 0;
    int a2 = 5, b2 = 0;

    std::cout << CALL_WITH_MAX(++a1, b1) << std::endl;
    std::cout << a1 + 1 << std::endl;
    std::cout << CALL_WITH_MAX(++a2, b1 + 10) << std::endl;
    std::cout << a2 << std::endl;
}