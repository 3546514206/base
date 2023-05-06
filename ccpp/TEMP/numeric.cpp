#include <iostream>
#include <cmath>

int main()
{
    short s = 10;
    int i = -10000;
    long l = 100000;
    float f = 230.47;
    double d = 200.45;

    // 数学运算
    std::cout << "sin(d):" << sin(s) << std::endl;
    std::cout << "abs(i):" << abs(i) << std::endl;
    std::cout << "floor(d):" << floor(d) << std::endl;
    std::cout << "sqrt(f):" << sqrt(f) << std::endl; 
    std::cout << "pow(d,2):" << pow(d, 2) << std::endl;

    return 0;
}